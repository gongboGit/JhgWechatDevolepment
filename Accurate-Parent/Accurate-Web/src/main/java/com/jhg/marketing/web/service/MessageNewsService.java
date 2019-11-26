package com.jhg.marketing.web.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jhg.marketing.dao.domin.material.MessageBase;
import com.jhg.marketing.dao.domin.material.MessageNews;
import com.jhg.marketing.dao.domin.material.MessageRelation;
import com.jhg.marketing.dao.mapper.MessageBaseMapper;
import com.jhg.marketing.dao.mapper.MessageNewsMapper;
import com.jhg.marketing.dao.mapper.MessageRelationMapper;
import com.jhg.marketing.web.util.AccessTokenUtil;
import com.jhg.marketing.web.util.ApplicationUtil;
import com.jhg.marketing.web.util.FileUploadUtil;
import com.jhg.marketing.web.util.WeChatUtil;
import com.jhg.marketing.web.util.material.MaterialUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lxl
 * @since 5/5/2019 9:49 AM
 */
@Service
public class MessageNewsService {

    private static Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");

    @Value("${user.default.uploadpath}")
    private String uploadfilepath;
    @Value("${user.default.visitpath}")
    private String visitpath;
    @Value("${user.domain}")
    private String domain;

    @Autowired
    private MessageNewsMapper messageNewsMapper;
    @Autowired
    private MessageBaseMapper messageBaseMapper;
    @Autowired
    private MessageRelationMapper messageRelationMapper;
    @Autowired
    private FileUploadUtil fileUploadUtil;
    @Autowired
    private AccessTokenUtil accessTokenUtil;
    @Autowired
    private MaterialUtil materialUtil;

    public List<MessageNews> listMessageNews() {
        return messageNewsMapper.listMessageNews();
    }

    public MessageNews getMessageNewsInfo(int id) {
        return messageNewsMapper.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean insertMessageNews(MessageNews messageNews, MultipartFile file) {
        if (file == null) {
            return false;
        }
        String content = messageNews.getContent();
        //上传封面
        String accessToken = accessTokenUtil.getAccessTokenByDataBase();
        String thumbFileUrl = fileUploadUtil.upload(file, "images");
        JSONObject thumb = null;
        try {
            thumb = materialUtil.addMaterial("thumb", uploadfilepath + thumbFileUrl, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //新增图文消息
        messageNews.setThumbUrl(visitpath + thumbFileUrl);
        messageNews.setThumb_media_id(thumb.getString("media_id"));
        messageNewsMapper.insert(messageNews);

        //新增图文永久素材
        String description = content.replaceAll("'", "\"");
        // 去多个img的src值
        StringBuilder subFilePath = new StringBuilder();
        StringBuilder subOldFilePath = new StringBuilder();
        if (description.contains("img")) {
            Matcher m = p.matcher(description);
            while (m.find()) {
                String imgSrc = m.group(1);
                subOldFilePath.append(imgSrc).append(",");
                String[] split = imgSrc.split("/");
                if (imgSrc.contains("/face/")) {
                    int k = imgSrc.indexOf(split[3]);
                    String subImgSrc = imgSrc.substring(k);
                    subFilePath.append(ApplicationUtil.getWebRealPath()).append("WEB-INF/classes/static/").append(subImgSrc).append(",");
                } else {
                    int k = imgSrc.indexOf(split[split.length - 2]);
                    String subImgSrc = imgSrc.substring(k);
                    subFilePath.append(uploadfilepath).append("/").append(subImgSrc).append(",");
                }
            }
        }
        if (StringUtils.isNotBlank(subFilePath.toString())) {

            subFilePath = new StringBuilder(subFilePath.substring(0, subFilePath.length() - 1));
            subOldFilePath = new StringBuilder(subOldFilePath.substring(0, subOldFilePath.length() - 1));

            // 本地图片地址
            String[] imgPathArr = subFilePath.toString().split(",");
            String[] imgOldPathArr = subOldFilePath.toString().split(",");

            String[] newPathArr = new String[imgPathArr.length];
            for (int i = 0; i < imgPathArr.length; i++) {
                String newFilePath = imgPathArr[i];
                // 将图片上传到微信，返回url
                JSONObject imgResultObj = null;
                try {
//                    imgResultObj = WeChatUtil.addMaterial(WeChatUtil.getMaterialImgUrl(accessToken), newFilePath);
                    imgResultObj = materialUtil.uploadNewsImg(newFilePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // 上传图片的id
                String contentContentUrl = "";
                if (imgResultObj != null && imgResultObj.containsKey("url")) {
                    // 图片url
                    contentContentUrl = imgResultObj.getString("url");
                }
                newPathArr[i] = contentContentUrl;
            }
            //替换图片路径
            for (int i = 0; i < imgPathArr.length; i++) {
                description = description.replace(imgOldPathArr[i], newPathArr[i]);
            }
        }
        messageNews.setContent(description);
        //上传永久图文素材
        Map<String, List<MessageNews>> map = new HashMap<>();
        List<MessageNews> articles = new ArrayList<>();
        articles.add(messageNews);
        map.put("articles", articles);
        JSONObject news = WeChatUtil.httpsRequest(WeChatUtil.getNewsMaterialUrl(accessToken), HttpMethod.POST.toString(), JSON.toJSONString(map, SerializerFeature.WriteNullStringAsEmpty));

        //新增基础消息记录
        MessageBase messageBase = new MessageBase();
        messageBase.setMediaId(news.getString("media_id"));
        messageBase.setMessageType("News");
        messageBase.setType(1);
        messageBase.setEnabled(true);
        messageBaseMapper.insert(messageBase);
        //新增基础消息和图文消息的关系
        MessageRelation messageRelation = new MessageRelation();
        messageRelation.setBaseId(messageBase.getId());
        messageRelation.setMessageId(messageNews.getId());
        messageRelation.setSort(1);
        messageRelationMapper.insert(messageRelation);

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateMessageNews(MessageNews messageNews, MultipartFile file) {
        Integer messageNewsId = messageNews.getId();
        String content = messageNews.getContent();
        String accessToken = accessTokenUtil.getAccessTokenByDataBase();
        if (file != null) {
            //上传封面
            String thumbFileUrl = fileUploadUtil.upload(file, "images");
            JSONObject thumb = null;
            try {
                thumb = materialUtil.addMaterial("thumb", uploadfilepath + thumbFileUrl, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //新增图文消息
            messageNews.setThumbUrl(domain + visitpath + thumbFileUrl);
            messageNews.setThumb_media_id(thumb.getString("media_id"));
        }
        //更新图文消息
        messageNewsMapper.updateByPrimaryKeySelective(messageNews);

        //更新图文永久素材
        String description = content.replaceAll("'", "\"");
        // 去多个img的src值
        StringBuilder subFilePath = new StringBuilder();
        StringBuilder subOldFilePath = new StringBuilder();
        if (description.contains("img")) {
            Matcher m = p.matcher(description);
            while (m.find()) {
                String imgSrc = m.group(1);
                subOldFilePath.append(imgSrc).append(",");
                String[] split = imgSrc.split("/");
                if (imgSrc.contains("/face/")) {
                    int k = imgSrc.indexOf(split[3]);
                    String subImgSrc = imgSrc.substring(k);
                    subFilePath.append(ApplicationUtil.getWebRealPath()).append("WEB-INF/classes/static/").append(subImgSrc).append(",");
                } else {
                    int k = imgSrc.indexOf(split[split.length - 2]);
                    String subImgSrc = imgSrc.substring(k);
                    subFilePath.append(uploadfilepath).append("/").append(subImgSrc).append(",");
                }
            }
        }
        if (StringUtils.isNotBlank(subFilePath.toString())) {

            subFilePath = new StringBuilder(subFilePath.substring(0, subFilePath.length() - 1));
            subOldFilePath = new StringBuilder(subOldFilePath.substring(0, subOldFilePath.length() - 1));

            // 本地图片地址
            String[] imgPathArr = subFilePath.toString().split(",");
            String[] imgOldPathArr = subOldFilePath.toString().split(",");

            String[] newPathArr = new String[imgPathArr.length];
            for (int i = 0; i < imgPathArr.length; i++) {
                String newFilePath = imgPathArr[i];
                // 将图片上传到微信，返回url
                JSONObject imgResultObj = null;
                try {
//                    imgResultObj = WeChatUtil.addMaterial(WeChatUtil.getMaterialImgUrl(accessToken), newFilePath);
                    imgResultObj = materialUtil.uploadNewsImg(newFilePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // 上传图片的id
                String contentContentUrl = "";
                if (imgResultObj != null && imgResultObj.containsKey("url")) {
                    // 图片url
                    contentContentUrl = imgResultObj.getString("url");
                }
                newPathArr[i] = contentContentUrl;
            }
            //替换图片路径
            for (int i = 0; i < imgPathArr.length; i++) {
                description = description.replace(imgOldPathArr[i], newPathArr[i]);
            }
        }
        messageNews.setContent(description);
        //更新永久图文素材
        MessageBase messageBase = messageBaseMapper.getMediaId(messageNewsId);
        Map<String, Object> map = new HashMap<>();
        map.put("articles", messageNews);
        map.put("media_id", messageBase.getMediaId());
        map.put("index", 0);
        JSONObject news = WeChatUtil.httpsRequest(WeChatUtil.getUpdateNewsMaterialUrl(accessToken), HttpMethod.POST.toString(), JSON.toJSONString(map, SerializerFeature.WriteNullStringAsEmpty));

        //新增基础消息记录
//        MessageBase messageBaseParam = new MessageBase();
//        messageBaseParam.setMediaId(news.getString("media_id"));
//        messageBaseParam.setId(messageBase.getId());
//        messageBaseMapper.updateByPrimaryKeySelective(messageBaseParam);

        return true;
    }

    public boolean updateHospitalIntroduction(Integer id, Boolean status) {
        //修改当前状态
        messageBaseMapper.updateEnabled(id, status);
        return true;
    }
}

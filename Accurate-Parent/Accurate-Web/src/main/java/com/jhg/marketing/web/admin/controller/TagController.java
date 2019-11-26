package com.jhg.marketing.web.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.jhg.marketing.dao.domin.WeChatFansTag;
import com.jhg.marketing.dao.domin.WeChatTag;
import com.jhg.marketing.dao.mapper.WeChatFansTagMapper;
import com.jhg.marketing.dao.mapper.WeChatTagMapper;
import com.jhg.marketing.web.admin.service.Message;
import com.jhg.marketing.web.util.exception.WxErrorException;
import com.jhg.marketing.web.util.tag.TagUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 素材管理接口
 *
 * @author lxl
 * @since 5/28/2019 4:09 PM
 */
@Slf4j
@RestController
@RequestMapping("admin/tag")
public class TagController {

    @Autowired
    private TagUtil tagUtil;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private WeChatFansTagMapper weChatFansTagMapper;
    @Autowired
    private WeChatTagMapper weChatTagMapper;

    /**
     * 创建标签
     *
     * @param tagName
     * @return
     */
    @RequestMapping("createTag")
    public Message createTag(String tagName) {
        JSONObject jsonObject;
        try {
            jsonObject = tagUtil.createTag(tagName);
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
            return Message.fail("创建失败！");
        }
        //本地新增
        weChatTagMapper.insert(new WeChatTag() {{
            setId(((Integer) ((Map) jsonObject.get("tag")).get("id")));
            setName(tagName);
        }});
        return Message.success("创建成功！");
    }

    /**
     * 同步标签和用户数据
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("syncTag")
    public Message syncTag() {
        //删除数据库数据
        weChatFansTagMapper.truncate();
        weChatTagMapper.truncate();
        JSONObject jsonObject;
        try {
            jsonObject = tagUtil.getTag();
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
            return Message.fail("同步标签失败！");
        }
        List<Map> list = (List<Map>) jsonObject.get("tags");
        weChatTagMapper.batchInsert(list);
        list.forEach(x -> {
            try {
                Integer id = (Integer) x.get("id");
                JSONObject json = tagUtil.getUserListByTag(id, null);
                Map<String, List<String>> map = (Map) json.get("data");
                if (map != null) {
                    List<String> openIdList = map.get("openid");
                    weChatFansTagMapper.batchInsert(openIdList, id);
                }
            } catch (WxErrorException e) {
                log.error(e.getError().getErrorMsg());
            }

        });
        return Message.success("同步成功！");
    }

    /**
     * 更新标签
     *
     * @param id
     * @param name
     * @return
     */
    @RequestMapping("updateTagName")
    public Message updateTagName(Integer id, String name) {
        try {
            tagUtil.updateTag(id, name);
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
            return Message.fail("更新失败！");
        }
        weChatTagMapper.updateByPrimaryKeySelective(new WeChatTag() {{
            setId(id);
            setName(name);
        }});
        return Message.success("更新成功！");
    }

    /**
     * 删除标签
     *
     * @param id
     * @return
     */
    @RequestMapping("deleteTag")
    public Message deleteTag(Integer id) {
        try {
            tagUtil.deleteTag(id);
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
            return Message.fail("删除失败！");
        }
        weChatTagMapper.deleteByPrimaryKey(id);
        return Message.success("删除成功！");
    }

    /**
     * 批量绑定标签
     *
     * @param openIdList
     * @param tagIdList
     * @return
     */
    @RequestMapping("batchTagging")
    public Message batchTagging(@RequestParam("openIds[]") List<String> openIdList, @RequestParam("tagIds[]") Integer[] tagIdList) {
        try {
            for (Integer tagId : tagIdList) {
                tagUtil.batchTagging(tagId, openIdList);
            }
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
            return Message.fail("绑定失败！");
        }
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            WeChatFansTagMapper mapper = sqlSession.getMapper(WeChatFansTagMapper.class);
            openIdList.forEach(x -> {
                for (Integer tagId : tagIdList) {
                    WeChatFansTag weChatFansTag = new WeChatFansTag() {{
                        setOpenId(x);
                        setTagId(tagId);
                    }};
                    List<WeChatFansTag> fansTagList = mapper.select(weChatFansTag);
                    if (fansTagList == null || fansTagList.size() == 0) {
                        mapper.insertWeChatFansTag(weChatFansTag);
                    }
                }
            });
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
//        weChatFansTagMapper.batchInsert(openIdList, id);
        return Message.success("绑定成功！");
    }

    /**
     * 批量取消标签
     *
     * @param openIdList
     * @param id
     * @return
     */
    @RequestMapping("batchUnTagging")
    public Message batchUnTagging(@RequestParam("openIdList[]") List<String> openIdList, Integer id) {
        try {
            tagUtil.batchUnTagging(id, openIdList);
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
            return Message.fail("取消绑定失败！");
        }
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try {
            WeChatFansTagMapper mapper = session.getMapper(WeChatFansTagMapper.class);
            openIdList.forEach(x -> mapper.delete(new WeChatFansTag() {{
                setTagId(id);
                setOpenId(x);
            }}));
            session.commit();
        } finally {
            session.close();
        }
        return Message.success("取消绑定成功！");
    }

    /**
     * 根据openId获取标签列表
     *
     * @param openId
     * @return
     */
    @RequestMapping("getTagIdList")
    public Message getTagIdList(String openId) {
        JSONObject jsonObject;
        try {
            jsonObject = tagUtil.getUserTagList(openId);
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
            return Message.fail("获取失败！");
        }
        return Message.success("获取成功！", jsonObject.get("tagid_list"));
    }

    /**
     * 获取所有标签
     *
     * @return
     */
    @RequestMapping("listTag")
    public Message listTag() {
        return Message.success("获取成功！", weChatTagMapper.selectAll());
    }
}

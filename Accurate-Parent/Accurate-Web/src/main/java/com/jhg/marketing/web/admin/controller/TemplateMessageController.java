package com.jhg.marketing.web.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jhg.marketing.dao.domin.MessageRecord;
import com.jhg.marketing.dao.domin.TemplateMessage;
import com.jhg.marketing.dao.domin.TemplateMessageData;
import com.jhg.marketing.dao.mapper.MessageRecordMapper;
import com.jhg.marketing.dao.mapper.SysUserMapper;
import com.jhg.marketing.dao.mapper.TemplateMessageDataMapper;
import com.jhg.marketing.dao.mapper.TemplateMessageMapper;
import com.jhg.marketing.web.admin.service.Message;
import com.jhg.marketing.web.util.exception.WxErrorException;
import com.jhg.marketing.web.util.message.TemplateMessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 素材管理接口
 *
 * @author lxl
 * @since 5/28/2019 4:09 PM
 */
@Slf4j
@RestController
@RequestMapping("admin/templateMessage")
public class TemplateMessageController {

    @Autowired
    private TemplateMessageUtil templateMessageUtil;
    @Autowired
    private TemplateMessageMapper templateMessageMapper;
    @Autowired
    private TemplateMessageDataMapper templateMessageDataMapper;
    @Autowired
    private MessageRecordMapper messageRecordMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

//    /**
//     * 获取所有模板
//     *
//     * @return
//     */
//    @RequestMapping("listTemplateMessage")
//    public Message listTemplateMessage(){
//        return Message.success("获取成功！", templateMessageMapper.selectAll());
//    }
//
//    /**
//     * 同步模板
//     *
//     * @return
//     */
//    @RequestMapping("syncTemplate")
//    public Message syncTemplate() {
//        JSONObject jsonObject = null;
//        try {
//            jsonObject = templateMessageUtil.getAllPrivateTemplate();
//        } catch (WxErrorException e) {
//            log.error(e.getError().getErrorMsg());
//        }
//        templateMessageMapper.bantchInsert(((List<Map>) jsonObject.get("template_list")));
//        return Message.success("同步成功！");
//    }
//
//    /**
//     * 删除模板
//     *
//     * @param id
//     * @param templateId
//     * @return
//     */
//    @RequestMapping("deleteTemplateMessage")
//    public Message deleteTemplateMessage(int id, String templateId) {
//        templateMessageMapper.deleteByPrimaryKey(id);
//        try {
//            JSONObject jsonObject = templateMessageUtil.delPrivateTemplate(templateId);
//        } catch (WxErrorException e) {
//            log.error(e.getError().getErrorMsg());
//        }
//        return Message.success("删除模板成功！");
//    }

//    /**
//     * 新增私有模板
//     *
//     * @param templateIdShort
//     * @return
//     */
//    @RequestMapping("insertTemplateMessage")
//    public Message insertTemplateMessage(String templateIdShort) {
//        try {
//            JSONObject jsonObject1 = templateMessageUtil.addTemplate(templateIdShort);
//            JSONObject jsonObject2 = templateMessageUtil.getAllPrivateTemplate();
////            jsonObject1.get("template_id")
//            List<Map> list = (List<Map>) jsonObject2.get("template_list");
//            list = list.stream().filter(x -> jsonObject1.get("template_id").equals(x.get("template_id"))).collect(Collectors.toList());
//            templateMessageMapper.bantchInsert(list);
//        } catch (WxErrorException e) {
//            log.error(e.getError().getErrorMsg());
//        }
//
//        return Message.success("新增成功！");
//    }

    /**
     * 获取所有模板
     *
     * @return
     */
    @RequestMapping("listTemplateMessage")
    public Message listTemplateMessage() {
        JSONObject jsonObject = null;
        try {
            jsonObject = templateMessageUtil.getAllPrivateTemplate();
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
        }
        return Message.success("获取成功！", jsonObject);
    }

    /**
     * 删除模板
     *
     * @param templateId
     * @return
     */
    @RequestMapping("deleteTemplateMessage")
    public Message deleteTemplateMessage(String templateId) {
        try {
            JSONObject jsonObject = templateMessageUtil.delPrivateTemplate(templateId);
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
        }
        return Message.success("删除模板成功！");
    }

    /**
     * 新增私有模板
     *
     * @param templateIdShort
     * @return
     */
    @RequestMapping("insertTemplateMessage")
    public Message insertTemplateMessage(String templateIdShort) {
        try {
            JSONObject jsonObject = templateMessageUtil.addTemplate(templateIdShort);
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
        }
        return Message.success("新增成功！");
    }

    /**
     * 获取模板信息
     *
     * @param templateId
     * @return
     */
    @RequestMapping("getTemplateInfo")
    public Message getTemplateInfo(String templateId) {
        List<Map> list = null;
        try {
            JSONObject jsonObject = templateMessageUtil.getAllPrivateTemplate();
            list = (List<Map>) jsonObject.get("template_list");
            list = list.stream().filter(x -> templateId.equals(x.get("template_id"))).collect(Collectors.toList());
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
        }
        Map map = list.get(0);
        HashMap<String, Object> data = new HashMap<>(2);
        String content = ((String) map.get("content"));
        data.put("data", map);
        List<String> templateKey = templateMessageUtil.getTemplateKey(content);
        data.put("keyList", templateKey);
        return Message.success("获取成功！", data);
    }

    /**
     * 新增模板消息
     *
     * @param param
     * @param templateMessage
     * @return
     */
    @RequestMapping("insertMessage")
    @Transactional(rollbackFor = Exception.class)
    public Message insertMessage(@Param("param") String param, TemplateMessage templateMessage) {
        //TODO 模板消息其他参数
        templateMessage.setCreateTime(new Date());
        templateMessageMapper.insert(templateMessage);

//        JSONObject.parseObject()
        Map map = JSON.parseObject(param, Map.class);
        List<TemplateMessageData> paramList = new ArrayList<>();
        Set set = map.keySet();
        for (Object key : set) {
            TemplateMessageData templateMessageData = new TemplateMessageData();
            templateMessageData.setKeyName(key.toString());
            templateMessageData.setKeyValue(map.get(key).toString());
            templateMessageData.setMessageId(templateMessage.getId());
            paramList.add(templateMessageData);
        }
        templateMessageDataMapper.bantchInsert(paramList);
        return Message.success("新增成功！");
    }

    /**
     * 查询所有的模板消息
     *
     * @return
     */
    @RequestMapping("listMessage")
    public Message listMessage(String title) {
//        List<TemplateMessage> templateMessageList = templateMessageMapper.selectAll();
//        List<Map<String, Object>> data = new ArrayList<>();
//        templateMessageList.forEach(x -> {
//            List<TemplateMessageData> dataList = templateMessageDataMapper.select(new TemplateMessageData() {{
//                setMessageId(x.getId());
//            }});
//            HashMap<String, Object> map = new HashMap<>(2);
//            map.put("message", x);
//            map.put("data",dataList);
//            data.add(map);
//        });

        return Message.success("获取成功！", templateMessageMapper.listMessage(title));
    }

    /**
     * 获取详细信息
     *
     * @param id
     * @return
     */
    @RequestMapping("getMessageInfo")
    public Message getMessageInfo(int id) {
        TemplateMessage templateMessage = templateMessageMapper.selectByPrimaryKey(id);
        List<TemplateMessageData> templateMessageDataList = templateMessageDataMapper.select(new TemplateMessageData() {{
            setMessageId(id);
        }});
        HashMap<String, Object> data = new HashMap<>(2);
        data.put("templateMessage", templateMessage);
        data.put("data", templateMessageDataList);
        return Message.success("获取成功！", data);
    }

    /**
     * 更新消息
     *
     * @return
     */
    @RequestMapping("updateMessage")
    public Message updateMessage(TemplateMessage templateMessage, String param) {

        Integer id = templateMessage.getId();
        //删除原有的模板消息和数据
        templateMessageMapper.deleteByPrimaryKey(id);
        templateMessageDataMapper.delete(new TemplateMessageData() {{
            setMessageId(id);
        }});
        //新增
//        TemplateMessage templateMessage = new TemplateMessage() {{
//            setUrl(url);
//            setTemplateId(templateId);
//        }};
        System.out.println("新增前id" + id);
        templateMessageMapper.insert(templateMessage);
        System.out.println("新增后id" + templateMessage.getId());
        Map map = JSON.parseObject(param, Map.class);
        List<TemplateMessageData> paramList = new ArrayList<>();
        Set set = map.keySet();
        for (Object key : set) {
            TemplateMessageData templateMessageData = new TemplateMessageData();
            templateMessageData.setKeyName(key.toString());
            templateMessageData.setKeyValue(map.get(key).toString());
            templateMessageData.setMessageId(templateMessage.getId());
            paramList.add(templateMessageData);
        }
        templateMessageDataMapper.bantchInsert(paramList);
        return Message.success("更新成功!");
    }

    /**
     * 删除消息
     *
     * @return
     */
    @RequestMapping("deleteMessage")
    public Message deleteMessage(int id) {
        templateMessageMapper.deleteByPrimaryKey(id);
        templateMessageDataMapper.delete(new TemplateMessageData() {{
            setMessageId(id);
        }});
        return Message.success("删除成功！");
    }

    /**
     * 发送发消息
     *
     * @param openIds
     * @return
     */
    @RequestMapping("sendMessage")
    public Message sendMessage(@RequestParam("openIds[]") String[] openIds, Integer messageId) {
        TemplateMessage templateMessage = templateMessageMapper.selectByPrimaryKey(messageId);
        Integer id = templateMessage.getId();
        List<TemplateMessageData> templateMessageDataList = templateMessageDataMapper.select(new TemplateMessageData() {{
            setMessageId(messageId);
        }});
        HashMap<String, Object> data = new HashMap<>();
        for (TemplateMessageData templateMessageData : templateMessageDataList) {
            HashMap<String, Object> dataParam = new HashMap<>(2);
            dataParam.put("value", templateMessageData.getKeyValue());
            dataParam.put("color", templateMessageData.getColor());
            data.put(templateMessageData.getKeyName(), dataParam);
        }
        //构造参数
        HashMap<String, Object> param = new HashMap<>(4);
        param.put("template_id", templateMessage.getTemplateId());
        param.put("url", templateMessage.getUrl());
        param.put("data", data);

        for (String openId : openIds) {
            param.put("touser", openId);
            try {
                JSONObject jsonObject = templateMessageUtil.sendTemplateMessage(JSON.toJSONString(param));
                //保存消息记录
                messageRecordMapper.insert(new MessageRecord() {{
                    setCreateTime(new Date());
                    setMessageId(id);
                    setWeChatMessageId(((Long) jsonObject.get("msgid")));
                    setWeChatUserId(sysUserMapper.getWeChatUserId(openId));
                }});
            } catch (WxErrorException e) {
                log.error(e.getError().getErrorMsg());
                return Message.fail(e.getError().getErrorMsg());
            }
        }
        return Message.success("发送成功！");
    }

    /**
     * 获取消息记录
     *
     * @return
     */
    @RequestMapping("listMessageRecord")
    public Message listMessageRecord() {
        return Message.success("获取成功！", messageRecordMapper.listMessageRecord());
    }
}

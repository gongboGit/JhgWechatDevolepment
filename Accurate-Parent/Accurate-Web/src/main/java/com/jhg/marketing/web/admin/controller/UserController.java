package com.jhg.marketing.web.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.jhg.marketing.dao.domin.WeChatFans;
import com.jhg.marketing.dao.mapper.WeChatFansMapper;
import com.jhg.marketing.web.admin.service.Message;
import com.jhg.marketing.web.util.exception.WxErrorException;
import com.jhg.marketing.web.util.user.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 素材管理接口
 *
 * @author lxl
 * @since 5/28/2019 4:09 PM
 */
@Slf4j
@RestController
@RequestMapping("admin/user")
public class UserController {

    @Autowired
    private UserUtil userUtil;
    @Autowired
    private WeChatFansMapper weChatFansMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 获取所有粉丝openId
     *
     * @param nextOpenId
     * @return
     */
    @RequestMapping("ListFansOpenId")
    public Message ListFans(@RequestParam(required = false) String nextOpenId) {
        List<String> openIdList;
        try {
            openIdList = userUtil.getFansList(nextOpenId);
        } catch (WxErrorException e) {
            e.printStackTrace();
            return Message.fail(e.getError().getErrorMsg());
        }
        return Message.success("获取成功！", openIdList);
    }

    /**
     * 根据openId批量获取粉丝信息
     *
     * @param openIdList
     * @return
     */
    @RequestMapping("getBatchFansInfo")
    public Message getBatchFansInfo(List<String> openIdList) {
        JSONObject jsonObject;
        try {
            jsonObject = userUtil.batchGetFansInfo(openIdList);
        } catch (WxErrorException e) {
            e.printStackTrace();
            return Message.fail(e.getError().getErrorMsg());
        }
        return Message.success("获取信息成功！", jsonObject);
    }

    /**
     * 根据openId获取粉丝信息
     *
     * @param openId
     * @return
     */
    @RequestMapping("getFansInfo")
    public Message getFansInfo(String openId) {
        JSONObject jsonObject;
        try {
            jsonObject = userUtil.getFansInfo(openId);
        } catch (WxErrorException e) {
            e.printStackTrace();
            return Message.fail(e.getError().getErrorMsg());
        }
        return Message.success("获取信息成功！", jsonObject);
    }

    /**
     * 同步粉丝信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/synchronizationFans")
    public Message synchronizationFans() {
        //获取数据库中的最后一个openId
        String lastOpenId = weChatFansMapper.getLastOpenId();
//        String lastOpenId = "ojWHx0lbTDhIB7sHlos9OlbgKnXo";
        List<Map> fansList = new ArrayList<>();
        try {
            //获取openIdList
            List<String> openIdList = userUtil.getFansList(lastOpenId);
            if (openIdList == null || openIdList.size() == 0) {
                return Message.success("数据同步成功！");
            }
            int size = openIdList.size();
            int i = 0;
            double ceil = Math.ceil(size / 100.0);
            while (i < ceil) {
                int fromIndex = i * 100;
                int toIndex = i * 100 + 100;
                if (i == ceil - 1) {
                    toIndex = size;
                }
                //根据OpenIdList批量获取用户信息
                JSONObject jsonObject = userUtil.batchGetFansInfo(openIdList.subList(fromIndex, toIndex));
                System.out.println(jsonObject);
                System.out.println(jsonObject.toJSONString());
                fansList.addAll(jsonObject.getObject("user_info_list", List.class));
                i++;
            }

        } catch (WxErrorException e) {
            log.error(e.getMessage());
        }

        //把用户信息批量插入数据库
        if (fansList.size() != 0) {
            fansList = fansList.stream().peek(x -> {
                String nickname = ((String) x.get("nickname"));
                x.put("nickname", nickname.getBytes(StandardCharsets.UTF_8));
            }).collect(Collectors.toList());
            //mybatis<foreach>批量新增会有问题
            SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
            try {
                WeChatFansMapper mapper = session.getMapper(WeChatFansMapper.class);
                fansList.forEach(mapper::insertWeChatFans);
                session.commit();
            } finally {
                session.close();
            }
//            weChatFansMapper.insertWeChatFansList(fansList);
        }
        return Message.success("数据同步成功！");
    }

    /**
     * 查询粉丝
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/listFans")
    public Message listFans(String nickName) {
//    public Message listFans(int pageNum, int pageSize) {
//        PageHelper.startPage(pageNum, pageSize);
//        List<WeChatFans> weChatFansList = weChatFansMapper.selectAll();
//        PageInfo<WeChatFans> weChatFansPageInfo = new PageInfo<>(weChatFansList);
//        long total = weChatFansPageInfo.getTotal();
//        HashMap<Object, Object> map = new HashMap<>(2);
//        map.put("total", total);
//        map.put("data", weChatFansList);

//        return Message.success("获取数据成功！", weChatFansMapper.listFansByCondition(nickName));
//        List<WeChatFans> weChatFansList = weChatFansMapper.selectAll();
        List<WeChatFans> weChatFansList = weChatFansMapper.litFans();
//        weChatFansList = weChatFansList.stream().peek(x -> {
//            String openId = x.getOpenId();
//            List<Integer> tagIdArr = weChatFansTagMapper.getTagArr(openId);
//            Integer[] strings = new Integer[tagIdArr.size()];
//            x.setTagIds(tagIdArr.toArray(strings));
//            x.setTagStr(weChatFansTagMapper.getTagStr(openId));
//        }).collect(Collectors.toList());
        return Message.success("获取数据成功！", weChatFansList);
    }

    /**
     * 更新备注
     *
     * @param id
     * @param remark
     * @return
     */
    @RequestMapping("updateRemark")
    @Transactional(rollbackFor = Exception.class)
    public Message updateRemark(int id, String remark) {
        weChatFansMapper.updateByPrimaryKeySelective(new WeChatFans() {{
            setRemark(remark);
            setId(id);
        }});
        WeChatFans data = weChatFansMapper.selectByPrimaryKey(id);
        try {
            userUtil.updateRemark(data.getOpenId(), remark);
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
            return Message.fail("更新失败");
        }
        return Message.success("更新成功！", data);
    }
}

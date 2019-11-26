package com.jhg.marketing.web.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.jhg.marketing.web.admin.service.Message;
import com.jhg.marketing.web.util.exception.WxErrorException;
import com.jhg.marketing.web.util.material.MaterialUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 素材管理接口
 *
 * @author lxl
 * @since 5/28/2019 4:09 PM
 */
@Slf4j
@RestController
@RequestMapping("admin/material")
public class MaterialController {

    @Autowired
    private MaterialUtil materialUtil;

    /**
     * 获取图文素材列表
     *
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("listNewsMaterial")
    public Message listNewsMaterial(Integer page, Integer pageSize) {
        JSONObject news;
        try {
            news = materialUtil.getBatchMaterial("news", page, pageSize);

        } catch (WxErrorException e) {
            log.error(e.getMessage());
            return Message.fail(e.getError().getErrorMsg());
        }
        return Message.success("获取图文素材成功！", news);
    }

    /**
     * 更新图文素材
     *
     * @param news
     * @return
     */
    @RequestMapping("updateNewsMaterial")
    public Message updateNewsMaterial(String news) {
        try {
            materialUtil.updateNewsMaterial(news);
        } catch (WxErrorException e) {
            e.printStackTrace();
            return Message.fail(e.getError().getErrorMsg());
        }
        return Message.success("更新图文成功！");
    }

    /**
     * 根据mediaId获取图文素材
     *
     * @param mediaId
     * @return
     */
    @RequestMapping("getNewsMaterialInfoByMediaId")
    public Message getNewsMaterialInfoByMediaId(String mediaId) {
        JSONObject jsonObject;
        try {
            jsonObject = materialUtil.getMaterial(mediaId);
        } catch (WxErrorException e) {
            e.printStackTrace();
            return Message.fail(e.getError().getErrorMsg());
        }
        return Message.success("获取图文素材成功！", jsonObject);
    }

    /**
     * 根据mediaId删除图文素材
     *
     * @param mediaId
     * @return
     */
    @RequestMapping("deleteNewsMaterialByMediaId")
    public Message deleteNewsMaterialByMediaId(String mediaId) {
        try {
            materialUtil.delMaterial(mediaId);
        } catch (WxErrorException e) {
            e.printStackTrace();
            return Message.fail(e.getError().getErrorMsg());
        }
        return Message.success("删除图文素材成功！");
    }
}

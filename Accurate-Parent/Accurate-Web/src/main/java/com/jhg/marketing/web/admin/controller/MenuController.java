package com.jhg.marketing.web.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.jhg.marketing.web.admin.service.Message;
import com.jhg.marketing.web.util.exception.WxErrorException;
import com.jhg.marketing.web.util.menu.MenuUtil;
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
@RequestMapping("admin/menu")
public class MenuController {

    @Autowired
    private MenuUtil menuUtil;

    /**
     * 获取菜单（自定义和个性化）
     *
     * @return
     */
    @RequestMapping("getMenuInfo")
    public Message getMenuInfo() {
        JSONObject jsonObject;
        try {
            jsonObject = menuUtil.getMenu();
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
//            throw new WxErrorException(e.getError());
            return Message.fail("获取失败！", e.getError());
        }
        if (jsonObject == null) {
            return Message.success("菜单未创建！");
        }
        return Message.success("获取菜单成功！", jsonObject);
    }

    /**
     * 创建自定义菜单
     *
     * @param menu
     * @return
     */
    @RequestMapping("createMenu")
    public Message createMenu(String menu) {
        try {
            menuUtil.createMenu(menu);
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
            return Message.fail("创建失败！", e.getError());
        }
        return Message.success("创建自定义菜单成功！");
    }

    /**
     * 创建个性化菜单
     *
     * @param menu
     * @return
     */
    @RequestMapping("createConditionalMenu")
    public Message createConditionalMenu(String menu) {
        try {
            menuUtil.createConditionalMenu(menu);
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
            return Message.fail("创建失败！", e.getError());
        }
        return Message.success("创建个性化菜单成功");
    }

    /**
     * 删除个性化菜单
     *
     * @param menuId
     * @return
     */
    @RequestMapping("delConditionalMenu")
    public Message delConditionalMenu(String menuId) {
        try {
            menuUtil.delConditionalMenu(menuId);
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
            return Message.fail("删除失败！", e.getError());
        }
        return Message.success("删除成功！");
    }

    /**
     * 测试个性化菜单匹配结果
     *
     * @param userId 微信号或者openId
     * @return
     */
    @RequestMapping("tryMachConditionalMenu")
    public Message tryMachConditionalMenu(String userId) {
        JSONObject jsonObject;
        try {
            jsonObject = menuUtil.tryMachConditionalMenu(userId);
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
            return Message.fail("测试失败！", e.getError());
        }
        return Message.success("测试成功！", jsonObject);
    }

    /**
     * 获取当前菜单（所有）
     *
     * @return
     */
    @RequestMapping("getCurrentSelfMenuInfo")
    public Message getCurrentSelfMenuInfo() {
        JSONObject jsonObject;
        try {
            jsonObject = menuUtil.getCurrentSelfMenuInfo();
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
            return Message.fail("获取失败！");
        }
        return Message.success("获取成功！", jsonObject);
    }

    @RequestMapping("deleteMenu")
    public Message deleteMenu() {
        try {
            menuUtil.deleteMenu();
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
            return Message.fail("删除失败！", e.getError());
        }
        return Message.success("删除成功！");
    }
}

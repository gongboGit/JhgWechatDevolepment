package com.jhg.marketing.web.admin.controller;

import com.jhg.marketing.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lxl
 * @since 5/23/2019 4:18 PM
 */
@Controller
@RequestMapping("admin/wechat")
public class AdminWeChatController extends BaseController {

    /**
     * 菜单管理页面
     *
     * @return
     */
    @RequestMapping("menu")
    public ModelAndView menu() {
        return view();
    }

    /**
     * 素材管理页面
     *
     * @return
     */
    @RequestMapping("material")
    public ModelAndView material() {
        return view();
    }

    /**
     * 微信关注用户管理
     *
     * @return
     */
    @RequestMapping("weChatUser")
    public ModelAndView weChatUser() {
        return view();
    }

    /**
     * 模板消息管理
     *
     * @return
     */
    @RequestMapping("messageTemplate")
    public ModelAndView messageTemplate() {
        return view();
    }

    /**
     * 模板详情页面
     *
     * @return
     */
    @RequestMapping("messageTemplateDetail")
    public ModelAndView messageTemplateDetail() {
        return view();
    }

    /**
     * 模板消息管理页面
     *
     * @return
     */
    @RequestMapping("templateMessage")
    public ModelAndView templateMessage() {
        return view();
    }

    /**
     * 模板消息页面
     *
     * @return
     */
    @RequestMapping("templateMessageDetail")
    public ModelAndView templateMessageDetail() {
        return view();
    }

    /**
     * 消息记录管理页面
     *
     * @return
     */
    @RequestMapping("messageRecord")
    public ModelAndView messageRecord() {
        return view();
    }

    /**
     * 标签管理页面
     *
     * @return
     */
    @RequestMapping("tag")
    public ModelAndView tag() {
        return view();
    }

    /**
     * 科室管理页面
     *
     * @return
     */
    @RequestMapping("department")
    public ModelAndView department() {
        return view();
    }

    /**
     * 专家信息页面
     *
     * @return
     */
    @RequestMapping("expertInformation")
    public ModelAndView expertInformation() {
        return view();
    }

    /**
     * 专家信息编辑页面
     *
     * @return
     */
    @RequestMapping("expertInformation-edit")
    public ModelAndView expertInformationEdit() {
        return view();
    }

    /**
     * 退款申请页面
     *
     * @return
     */
    @RequestMapping("refundApply")
    public ModelAndView refundApply(){
        return view();
    }

    /**
     * 预约挂号订单页面
     *
     * @return
     */
    @RequestMapping("registerOrder")
    public ModelAndView registerOrder(){
        return view();
    }
}

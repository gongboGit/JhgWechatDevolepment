package com.jhg.marketing.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

    /**
     * 微信绑定页面
     *
     * @return
     */
    @RequestMapping("/register")
    public String toLoginUI() {
        return "/home/login";
    }

    /**
     * 验证成功页面
     *
     * @return
     */
    @RequestMapping("/success")
    public String toSuccessUI() {
        return "/home/success";
    }

    /**
     * 下载PDF页面
     *
     * @return
     */
    @RequestMapping("/downloadPdf")
    public String downloadPdf() {
        return "/home/view/downloadPdf";
    }

}

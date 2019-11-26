package com.jhg.marketing.web.admin.controller;

import com.jhg.marketing.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("admin")
@Controller
public class AdminErrorController extends BaseController {
    @GetMapping("error")
    public ModelAndView error() {
        return view();
    }
}

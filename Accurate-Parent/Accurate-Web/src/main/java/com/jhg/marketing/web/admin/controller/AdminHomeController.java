package com.jhg.marketing.web.admin.controller;

import com.jhg.marketing.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/home")
public class AdminHomeController extends BaseController {
    @GetMapping("index")
    public ModelAndView index() {
        return view();
    }
}

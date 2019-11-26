package com.jhg.marketing.web.controller;

import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

public class BaseController {

    protected ModelAndView view() {

        ModelAndView view = new ModelAndView();
        return view;
    }

    protected ModelAndView view(String view, Map<String, ?> model) {
        return new ModelAndView(view, model);
    }

    protected ModelAndView view(String view) {
        return new ModelAndView(view);
    }
}

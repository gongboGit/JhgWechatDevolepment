package com.jhg.marketing.web.controller;

import com.jhg.marketing.dao.domin.ExpertInformation;
import com.jhg.marketing.dao.mapper.DepartmentMapper;
import com.jhg.marketing.dao.mapper.ExpertInformationMapper;
import com.jhg.marketing.dao.mapper.HospitalIntroductionMapper;
import com.jhg.marketing.web.admin.service.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 与微信对接登陆验证
 *
 * @author lxl
 */
@Slf4j
@Controller
public class HospitalInfoController {

    @Autowired
    private HospitalIntroductionMapper hospitalIntroductionMapper;
    @Autowired
    private ExpertInformationMapper expertInformationMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 医院信息页面
     *
     * @return
     */
    @RequestMapping("/personalCenterTwo")
    public String personalCenterTwo(Model model) {
        //获取医院轮播图
        model.addAttribute("carouselList", hospitalIntroductionMapper.getHospitalCarousel());
        //获取医院介绍
        model.addAttribute("introduction", hospitalIntroductionMapper.getHospitalIntroduction());
        //获取专家信息
        List<ExpertInformation> expertInformationList = expertInformationMapper.select(new ExpertInformation() {{
            setShowIndex(true);
        }});

        model.addAttribute("someExpertInfoList", expertInformationList != null ? (expertInformationList.size() == 0 ? null : expertInformationList) : null);
        return "/home/personalCenterTwo";
    }

    /**
     * 专家信息页面
     *
     * @return
     */
    @RequestMapping("/view/expertList")
    public String viewExpertList() {
        return "/home/view/expertList";
    }

    /**
     * 获取科室信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/listDepartment")
    public Message listDepartment() {
        return Message.success("获取成功！", departmentMapper.listDepartment());
    }

    /**
     * 根据科室名称获取专家信息
     *
     * @param departmentName 科室名称
     * @return
     */
    @ResponseBody
    @RequestMapping("/listExpertInfoByDepartmentName")
    public Message listExpertInfoByDepartmentName(String departmentName) {
        return Message.success("获取数据成功！", expertInformationMapper.listExpertInfoByDepartmentName(departmentName));
    }

    /**
     * 专家详情页面
     *
     * @return
     */
    @RequestMapping("/view/expertInformation")
    public String viewExpertInformation(Integer expertInfoId, Model model) {
        model.addAttribute("expertInfo", expertInformationMapper.selectByPrimaryKey(expertInfoId));
        return "/home/view/expertInformation";
    }

    /**
     * 获取专家详情
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("getExpertInfo")
    public Message getExpertInfo(int id) {
        return Message.success("获取成功！", expertInformationMapper.selectByPrimaryKey(id));
    }
}
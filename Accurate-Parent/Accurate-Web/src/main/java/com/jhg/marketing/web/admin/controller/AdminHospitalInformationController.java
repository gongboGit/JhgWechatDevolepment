package com.jhg.marketing.web.admin.controller;

import com.jhg.marketing.dao.domin.HospitalIntroduction;
import com.jhg.marketing.dao.mapper.HospitalIntroductionMapper;
import com.jhg.marketing.web.admin.service.Message;
import com.jhg.marketing.web.controller.BaseController;
import com.jhg.marketing.web.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * 医院信息管理
 *
 * @author lxl
 */
@Controller
@RequestMapping("admin/hospital-info")
public class AdminHospitalInformationController extends BaseController {

    @Autowired
    private HospitalIntroductionMapper hospitalIntroductionMapper;
    @Autowired
    private FileUploadUtil fileUploadUtil;

    /**
     * 跳转到医院介绍管理页面
     *
     * @return
     */
    @RequestMapping("introduction")
    public ModelAndView introduction() {
        return view();
    }

    /**
     * 编辑医院介绍
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("introduction-edit")
    public Object editIntroduction(@RequestParam(value = "id", defaultValue = "") Integer id, Model model) {
        if (id == null) {
            //新增
            model.addAttribute("introduction", new HospitalIntroduction());
        } else {
            //修改
            model.addAttribute("introduction", hospitalIntroductionMapper.selectByPrimaryKey(id));
        }
        return view();
    }

//    @RequestMapping("introduction-detail")
//    public Object editIntroduction(@RequestParam(value = "id", defaultValue = "") Integer id, Model model) {
//        if (id == null) {
//            //新增
//            model.addAttribute("introduction", new MessageNews());
//        } else {
//            //修改
//            model.addAttribute("introduction", messageNewsService.getMessageNewsInfo(id));
//        }
//        return view();
//    }

    /**
     * 查询所有医院介绍
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("listHospitalIntroduction")
    public Message listHospitalIntroduction() {
        return Message.success("查询成功！", hospitalIntroductionMapper.selectAll());
    }

    /**
     * 删除医院介绍
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteHospitalIntroduction")
    public Message deleteHospitalIntroduction(String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        hospitalIntroductionMapper.deleteByIds(idList);
        return Message.success("删除成功！");
    }

    /**
     * 编辑医院介绍
     *
     * @param hospitalIntroduction
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("editHospitalIntroduction")
    public Message editHospitalIntroduction(HospitalIntroduction hospitalIntroduction, @RequestParam(value = "file", required = false) MultipartFile file) {
        if (file != null) {
            //上传图片
            String carouselUrl = fileUploadUtil.upload(file, "hospitalIntroduction");
            if (carouselUrl == null) {
                return Message.fail("上传轮播图失败！");
            }
            hospitalIntroduction.setContent(carouselUrl);
        }
        Integer id = hospitalIntroduction.getId();
        if (id != null) {
            //修改
            hospitalIntroductionMapper.updateByPrimaryKeySelective(hospitalIntroduction);
        } else {
            //新增
            hospitalIntroductionMapper.insert(hospitalIntroduction);
        }
        return Message.success("编辑成功！");
    }

}

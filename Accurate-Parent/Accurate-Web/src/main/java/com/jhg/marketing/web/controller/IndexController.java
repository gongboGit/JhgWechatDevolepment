package com.jhg.marketing.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jhg.marketing.dao.domin.SysUser;
import com.jhg.marketing.dao.mapper.SysUserMapper;
import com.jhg.marketing.dao.mapper.WeChatUserMapper;
import com.jhg.marketing.web.admin.service.Message;
import com.jhg.marketing.web.util.ApplicationUtil;
import com.jhg.marketing.web.util.DateUtil;
import com.jhg.marketing.web.util.StringUtil;
import com.jhg.marketing.web.util.hisInterface.WeChatInterfaceUtil;
import com.jhg.marketing.web.util.previewRegister.PreviewRegisterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 首页
 *
 * @author lxl
 */
@Slf4j
@Controller
public class IndexController {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private WeChatUserMapper weChatUserMapper;

    /**
     * 首页页面
     *
     * @return
     */
    @RequestMapping("/personalCenterOne")
    public String personalCenterOne(ModelMap map) {
        SysUser user=ApplicationUtil.getSessionUser();
        map.addAttribute("user",user);
        return "/home/personalCenterOne";
    }

    /**
     * 检查报告单页面
     *
     * @return
     */
    @RequestMapping("/view/inspectionList")
    public String viewInspectionList() {
        return "/home/view/inspectionList";
    }

    /**
     * 查询检查项目列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("listInspect")
    public Message listInspect(String startTime, String endTime) {
        String openId = ApplicationUtil.getSessionOpenId();
//        String openId = "o8VLTjsB7Ezjw8l-3VvdSvCc7Yaw";
        String mainTrueName = sysUserMapper.getMainTrueName(openId);
        List<String> data = new ArrayList<>();
        //获取门诊检查
        List<String> list = JSONObject.parseArray(WeChatInterfaceUtil.getSeekMedicalAdviceInfo(openId, mainTrueName).getString("data"), String.class);
        if (list != null) {
            list.stream().filter(y -> mainTrueName.equals(JSON.parseObject(y).getString("XM"))).forEach(x -> {
                String seekMedicalAdviceNumber = JSON.parseObject(x).getString("JZH");
                //检查
                List<String> dataList = JSONObject.parseArray(WeChatInterfaceUtil.listSeekMedicalAdviceInspectInfo(seekMedicalAdviceNumber).getString("data"), String.class);
                if (dataList != null) {
                    data.addAll(dataList);
                }
            });
        }
        //获取住院检查
        List<String> list2 = JSONObject.parseArray(WeChatInterfaceUtil.getHospitalizationInfo(openId).getString("data"), String.class);
        if (list2 != null) {
            list2.stream().filter(y -> mainTrueName.equals(JSON.parseObject(y).getString("XM"))).forEach(x -> {
                String hospitalizationNumber = JSON.parseObject(x).getString("ZYH");
                List<String> dataList2 = JSONObject.parseArray(WeChatInterfaceUtil.listHospitalizationInspectInfo(hospitalizationNumber).getString("data"), String.class);
                if (dataList2 != null) {
                    data.addAll(dataList2);
                }
            });
        }
        //TODO 过滤时间段
//		data = data.stream().filter(x -> {
//			if (JSON.parseObject(x).getString("")) {
//				return x;
//			}
//			return x==x;
//		}).collect(Collectors.toList());
        return Message.success("获取成功！", data);
    }

    /**
     * 检查报告单详情页面
     *
     * @return
     */
    @RequestMapping("/view/inspection")
    public String viewInspection() {
        return "/home/view/inspection";
    }

    /**
     * 获取检查详细信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getInspectInfoByInspectNO")
    public Message getInspectInfo(String inspectNO) {
        String openId = ApplicationUtil.getSessionOpenId();
//        String openId = "o8VLTjsB7Ezjw8l-3VvdSvCc7Yaw";
        String mainTrueName = sysUserMapper.getMainTrueName(openId);
        //查询病人信息(姓名,性别,年龄，住院号，检查时间)
        List<String> data = new ArrayList<>();
        //获取门诊检查
        List<String> list = JSONObject.parseArray(WeChatInterfaceUtil.getSeekMedicalAdviceInfo(openId, mainTrueName).getString("data"), String.class);
        if (list != null) {
            list.forEach(x -> {
                String seekMedicalAdviceNumber = JSON.parseObject(x).getString("JZH");
                //检查
                List<String> dataList = JSONObject.parseArray(WeChatInterfaceUtil.listSeekMedicalAdviceInspectInfo(seekMedicalAdviceNumber).getString("data"), String.class);
                if (dataList != null) {
                    dataList = dataList.stream().filter(z -> inspectNO.equals(JSON.parseObject(z).getString("NO"))).collect(Collectors.toList());
                    if (dataList.size() != 0) {
                        data.addAll(dataList);
                    }
                }
            });
        }
        //获取住院检查
        List<String> list2 = JSONObject.parseArray(WeChatInterfaceUtil.getHospitalizationInfo(openId).getString("data"), String.class);
        if (list2 != null) {
            list2.stream().filter(y -> mainTrueName.equals(JSON.parseObject(y).getString("XM"))).forEach(x -> {
                String hospitalizationNumber = JSON.parseObject(x).getString("ZYH");
                List<String> dataList2 = JSONObject.parseArray(WeChatInterfaceUtil.listHospitalizationInspectInfo(hospitalizationNumber).getString("data"), String.class);
                if (dataList2 != null) {
                    dataList2 = dataList2.stream().filter(z -> inspectNO.equals(JSON.parseObject(z).getString("NO"))).collect(Collectors.toList());
                    if (dataList2.size() != 0) {
                        data.addAll(dataList2);
                    }
                }
            });
        }

        Map<String, Object> map = new HashMap<>();
        map.put("name", mainTrueName);
//			put("hospitalNumber", hospitalizationNumber);
        map.put("data", data);
        return Message.success("获取成功！", map);
    }

    /**
     * 获取检查详细信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getInspectInfoByHospitalizationNumber")
    public Message getInspectInfoByHospitalizationNumber(String inspectNO) {
        String openId = ApplicationUtil.getSessionOpenId();
//        String openId = "o8VLTjsB7Ezjw8l-3VvdSvCc7Yaw";
        String mainTrueName = sysUserMapper.getMainTrueName(openId);
        //查询病人信息(姓名,性别,年龄，住院号，检查时间)
        List<String> data = new ArrayList<>();
        //获取门诊检查
        List<String> list = JSONObject.parseArray(WeChatInterfaceUtil.getSeekMedicalAdviceInfo(openId, mainTrueName).getString("data"), String.class);
        if (list != null) {
            list.forEach(x -> {
                String seekMedicalAdviceNumber = JSON.parseObject(x).getString("JZH");
                //检查
                List<String> dataList = JSONObject.parseArray(WeChatInterfaceUtil.listSeekMedicalAdviceInspectInfo(seekMedicalAdviceNumber).getString("data"), String.class);
                if (dataList != null) {
                    dataList = dataList.stream().filter(z -> inspectNO.equals(JSON.parseObject(z).getString("NO"))).collect(Collectors.toList());
                    if (dataList.size() != 0) {
                        data.addAll(dataList);
                    }
                }
            });
        }
        //获取住院检查
        List<String> list2 = JSONObject.parseArray(WeChatInterfaceUtil.getHospitalizationInfo(openId).getString("data"), String.class);
        if (list2 != null) {
            list2.stream().filter(y -> mainTrueName.equals(JSON.parseObject(y).getString("XM"))).forEach(x -> {
                String hospitalizationNumber = JSON.parseObject(x).getString("ZYH");
                List<String> dataList2 = JSONObject.parseArray(WeChatInterfaceUtil.listHospitalizationInspectInfo(hospitalizationNumber).getString("data"), String.class);
                if (dataList2 != null) {
                    dataList2 = dataList2.stream().filter(z -> inspectNO.equals(JSON.parseObject(z).getString("NO"))).collect(Collectors.toList());
                    if (dataList2.size() != 0) {
                        data.addAll(dataList2);
                    }
                }
            });
        }

        Map<String, Object> map = new HashMap<>();
        map.put("name", mainTrueName);
//			put("hospitalNumber", hospitalizationNumber);
        map.put("data", data);
        return Message.success("获取成功！", map);
    }

    /**
     * 检验列表页面
     *
     * @return
     */
    @RequestMapping(value = "/view/inspectionReportList")
    public String viewInspectionReportList() {
        return "/home/view/inspectionReportList";
    }

    /**
     * 检验列表
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "listTest")
    public Message listTest(String startTime, String endTime) {
//        String openId = ApplicationUtil.getSessionOpenId();
        String openId = "oJDmejh7eYLkskM93CmTHHTyRSP8";
        LinkedList<Object> data = new LinkedList<>();
        //门诊检验
        JSONObject seekMedicalAdviceInfo = WeChatInterfaceUtil.getSeekMedicalAdviceInfo(openId, null);
//        String mainTrueName = sysUserMapper.getMainTrueName(openId);
        String mainTrueName = "戴水生";
        List<String> list = JSONObject.parseArray(seekMedicalAdviceInfo.getString("data"), String.class);
        if (list != null) {
            list.stream().filter(z -> mainTrueName.equals(JSON.parseObject(z).getString("XM"))).forEach(x -> {
                String seekMedicalAdviceNumber = JSON.parseObject(x).getString("JZH");
                //检验
                JSONObject listSeekMedicalAdviceTestInfo = WeChatInterfaceUtil.listSeekMedicalAdviceTestInfo(seekMedicalAdviceNumber);
                List<String> list1 = JSONObject.parseArray(listSeekMedicalAdviceTestInfo.getString("data"), String.class);
                if (list1 != null) {
                    data.addAll(list1);
                }
            });
        }
        //住院检验
        List<String> list2 = JSONObject.parseArray(WeChatInterfaceUtil.getHospitalizationInfo("o8VLTjsB7Ezjw8l-3VvdSvCc7Yaw").getString("data"), String.class);
        if (list2 != null) {
            list2.stream().filter(z -> mainTrueName.equals(JSON.parseObject(z).getString("XM"))).forEach(x -> {
                String hospitalizationNumber = JSON.parseObject(x).getString("ZYH");
                //检验
                List<String> list3 = JSONObject.parseArray(WeChatInterfaceUtil.listHospitalizationTestInfo(hospitalizationNumber).getString("data"), String.class);
                if (list3 != null) {
                    data.addAll(list3);
                }
            });
        }
        //TODO 根据时间过滤且排序
        return Message.success("获取成功！", data);
    }

    /**
     * 检验报告单页面
     *
     * @return
     */
    @RequestMapping("/view/inspectionReport")
    public String viewInspectionReport() {
        return "/home/view/inspectionReport";
    }

    /**
     * 获取检验报告
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getTestInfo")
    public Message getTestInfo(String testNO) {
//        testNO = "S1803410";
        String openId = ApplicationUtil.getSessionOpenId();
//        String openId = "o8VLTjsB7Ezjw8l-3VvdSvCc7Yaw";
        Map<String, Object> map = new HashMap<>();
        //门诊检验
        JSONObject seekMedicalAdviceInfo = WeChatInterfaceUtil.getSeekMedicalAdviceInfo(openId, null);
        String mainTrueName = sysUserMapper.getMainTrueName(openId);
        List<String> list = JSONObject.parseArray(seekMedicalAdviceInfo.getString("data"), String.class);
        if (list != null) {
            list.stream().filter(z -> mainTrueName.equals(JSON.parseObject(z).getString("XM"))).forEach(x -> {
//			list.forEach(x -> {
                String seekMedicalAdviceNumber = JSON.parseObject(x).getString("JZH");
                //检验
                JSONObject listSeekMedicalAdviceTestInfo = WeChatInterfaceUtil.listSeekMedicalAdviceTestInfo(seekMedicalAdviceNumber);
                List<String> list1 = JSONObject.parseArray(listSeekMedicalAdviceTestInfo.getString("data"), String.class);
                if (list1 != null) {
                    list1.stream().filter(k -> testNO.equals(JSON.parseObject(k).getString("NO")))
                            .forEach(y -> {
                                List<String> list2 = JSONObject.parseArray(WeChatInterfaceUtil.getSeekMedicalAdviceTestInfo(testNO).getString("data"), String.class);
                                if (list2 != null) {
                                    map.put(y, list2);
                                }
                            });
                }
            });
        }
        //住院检验
        List<String> list2 = JSONObject.parseArray(WeChatInterfaceUtil.getHospitalizationInfo("o8VLTjsB7Ezjw8l-3VvdSvCc7Yaw").getString("data"), String.class);
        if (list2 != null) {
            list2.stream().filter(z -> mainTrueName.equals(JSON.parseObject(z).getString("XM")))
                    .forEach(x -> {
                        String hospitalizationNumber = JSON.parseObject(x).getString("ZYH");
                        //检验
                        List<String> list3 = JSONObject.parseArray(WeChatInterfaceUtil.listHospitalizationTestInfo(hospitalizationNumber).getString("data"), String.class);
                        if (list3 != null) {
                            list3.stream().filter(k -> testNO.equals(JSON.parseObject(k).getString("NO")))
                                    .forEach(y -> {
                                        List<String> list4 = JSONObject.parseArray(WeChatInterfaceUtil.getHospitalizationTestInfo(testNO).getString("data"), String.class);
                                        if (list4 != null) {
                                            map.put(y, list4);
                                        }
                                    });
                        }
                    });
        }
        Map<String, Object> data = new HashMap<>();
        //TODO 设置检验日期
        data.put("name", mainTrueName);
        data.put("data", map);
        return Message.success("获取成功！", data);
    }

    /**
     * 体检报告列表
     *
     * @return
     */
    @RequestMapping(value = "/view/physicalExaminationList")
    public String viewPhysicalExaminationList() {
        return "/home/view/physicalExaminationList";
    }

    /**
     * 获取体检报告列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("listPhysicalExamination")
    public Message listPhysicalExamination(String startTime, String endTime) {
        String idCard = ApplicationUtil.getSessionUser().getIdCard();
//        String openId = "o8VLTjuzTY_2EnETMuWcXXSJxM_8";
        //体检
        List<String> list = JSONObject.parseArray(WeChatInterfaceUtil.getPhysicalExaminationBillInfo(startTime,endTime,idCard).getString("data"), String.class);
       /* if (list != null) {
            String mainTrueName = sysUserMapper.getMainTrueName(openId);
            //获取当前就诊人的某个时间段的体检
            list = list.stream()
                    .filter(x -> mainTrueName.equals(JSON.parseObject(x).getString("XM")))
                    .filter(y -> {
                        String dateStr = JSON.parseObject(y).getString("JCRQ");
                        if (StringUtil.isBlank(dateStr)) {
                            dateStr = JSON.parseObject(y).getString("TJRQ");
                        }
                        if (StringUtil.isBlank(dateStr)) {
                            return true;
                        }
                        Date date = DateUtil.changeStrToDate3(dateStr, "yyyy/MM/dd HH:mm:ss");
                        Date start = DateUtil.changeStrToDate3(startTime, "yyyy/MM/dd");
                        Date end = DateUtil.changeStrToDate3(endTime, "yyyy/MM/dd");
                        if (date == null) {
                            return true;
                        }
                        boolean filter = true;
                        if (start != null) {
                            filter = start.before(date);
                        }
                        if (end != null) {
                            filter = end.after(date);
                        }
                        return filter;
                    }).collect(Collectors.toList());
        }*/
        System.out.println(list.toString());
        return Message.success("请求成功！", list);
    }


    /**
     * 体检报告单页面
     *
     * @return
     */
    @RequestMapping("/view/physicalExamination")
    public String viewPhysicalExamination() {
        return "/home/view/physicalExamination";
    }

    /**
     * 获取体检报告
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getPhysicalExaminationInfo")
    public Message getPhysicalExaminationInfo(String TJBH) {
//		String openId = ApplicationUtil.getSessionOpenId();
//        String openId = "o8VLTjuzTY_2EnETMuWcXXSJxM_8";
//        Map<String, List<String>> map = new HashMap<>();
//        //体检
//        List<String> list = JSONObject.parseArray(WeChatInterfaceUtil.getPhysicalExaminationBillInfo(openId).getString("data"), String.class);
//        if (list != null) {
//            String mainTrueName = sysUserMapper.getMainTrueName(openId);
//            list.stream()
//                    .filter(x -> mainTrueName.equals(JSON.parseObject(x).getString("XM")) && TJBH.equals(JSON.parseObject(x).getString("TJBH")))
//                    .forEach(z -> {
//                List<String> list1 = JSONObject.parseArray(WeChatInterfaceUtil.listPhysicalExaminationInfo(TJBH).getString("data"), String.class);
//                if (list1 != null) {
//                    list1.forEach(j -> {
//                        String xh = JSON.parseObject(j).getString("XH");
//                        List<String> list2 = JSONObject.parseArray(WeChatInterfaceUtil.getPhysicalExaminationInfo(xh).getString("data"), String.class);
//                        map.put(j, list2);
//                    });
//                }
//            });
//        }
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = JSONObject.parseArray(WeChatInterfaceUtil.listPhysicalExaminationInfo(TJBH).getString("data"), String.class);
        if (list != null) {
            list.forEach(j -> {
                String xh = JSON.parseObject(j).getString("XH");
                List<String> list1 = JSONObject.parseArray(WeChatInterfaceUtil.getPhysicalExaminationInfo(xh).getString("data"), String.class);
                map.put(j, list1);
            });
        }
        return Message.success("请求成功！", map);
    }

    /**
     * 预约挂号页面
     *
     * @return
     */
    @RequestMapping("/view/appointmentRegistration")
    public String viewAppointmentRegistration() {
        return "/home/view/appointmentRegistration";
    }



    @RequestMapping("/view/payDetail")
    public String payDetail(){
        return "/home/view/payDetail";
    }
    /**
     * 获取部门信息
     *
     * @param registerType
     * @return
     */
    @ResponseBody
    @RequestMapping("getDepartmentInfo")
    public Message getDepartmentInfo(String registerType) {
        List<String> list = JSONObject.parseArray(WeChatInterfaceUtil.getDepartmentInfo(registerType).getString("data"), String.class);
       // Map map = (Map) PreviewRegisterUtil.getDepartmentInfo(registerType).get("data");
        return Message.success("获取成功！", list);
    }

    /**
     * 预约挂号详情页面
     *
     * @return
     */
    @RequestMapping("/view/appointmentRegistrationDetail")
    public String viewAppointmentRegistrationDetail() {
        return "/home/view/appointmentRegistrationDetail";
    }

    /**
     * 获取医生信息
     *
     * @param registerType
     * @param date
     * @param departmentId
     * @return
     */
    @ResponseBody
    @RequestMapping("getDoctorInfo")
    public Message getDoctorInfo(String registerType, String date, String departmentId) {
        return Message.success("获取成功！", JSON.parseArray(WeChatInterfaceUtil.getDoctorInfo(registerType, date, departmentId).getString("data"), Map.class));
    }

    /**
     * 挂号
     *
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("submitRegister")
    public Message submitRegister(String param) {
        return Message.success("挂号成功！", PreviewRegisterUtil.submitRegister(((Map) JSON.parseObject(param))));
    }

    /**
     * 预约挂号提示页面
     *
     * @return
     */
    @RequestMapping("/view/appointmentRegistrationTips")
    public String viewAppointmentRegistrationTips() {
        return "/home/view/appointmentRegistrationTips";
    }

    /**
     * 查询就诊人
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("listPatient")
    public Message listPatient() {
        return Message.success("获取成功！", weChatUserMapper.listPatient(ApplicationUtil.getSessionOpenId()));
    }

    /**
     * 微信支付页面
     *
     * @return
     */
    @RequestMapping("/WeChatPay/view")
    public String viewWeChatPay() {
        return "/home/view/weChatPay";
    }
}
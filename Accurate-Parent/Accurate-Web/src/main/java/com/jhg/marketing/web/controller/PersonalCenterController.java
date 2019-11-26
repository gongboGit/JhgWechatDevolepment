package com.jhg.marketing.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jhg.marketing.dao.domin.Permission;
import com.jhg.marketing.dao.domin.SysUser;
import com.jhg.marketing.dao.domin.WeChatUser;
import com.jhg.marketing.dao.mapper.PermissionMapper;
import com.jhg.marketing.dao.mapper.SysUserMapper;
import com.jhg.marketing.dao.mapper.WeChatUserMapper;
import com.jhg.marketing.web.admin.service.Message;
import com.jhg.marketing.web.util.ApplicationUtil;
import com.jhg.marketing.web.util.DateUtil;
import com.jhg.marketing.web.util.StringUtil;
import com.jhg.marketing.web.util.ehcache.EhcacheUtil;
import com.jhg.marketing.web.util.hisInterface.WeChatInterfaceUtil;
import com.jhg.marketing.web.util.shiro.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 *
 * @author lxl
 */
@Slf4j
@Controller
public class PersonalCenterController {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private WeChatUserMapper weChatUserMapper;

    /**
     * 我的页面
     *
     * @return
     */
    @RequestMapping("/personalCenterThree")
    public String toPersonalCenter() {
        return "/home/personalCenterThree";
    }

    /**
     * 缴费列表
     *
     * @return
     */
    @RequestMapping("/userPayList")
    public String toUserPayList() {
        return "/home/view/userPayList";
    }

    /**
     * 获取缴费记录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("listPayInfo")
    public Message listPayInfo() {
        String openId = ApplicationUtil.getSessionOpenId();
//        String openId = "o8VLTjsB7Ezjw8l-3VvdSvCc7Yaw";
        String mainTrueName = sysUserMapper.getMainTrueName(openId);
        JSONObject jsonObject = WeChatInterfaceUtil.listUserPayInfo(openId);
        List<Map> list = (List<Map>) jsonObject.get("data");
        if (list != null && list.size() != 0) {
            list = list.stream().filter(x -> mainTrueName.equals(x.get("XM"))).collect(Collectors.toList());
        }
        if (WeChatInterfaceUtil.judgeResult(jsonObject)) {
            return Message.fail(jsonObject.getString("msg"));
        }
        System.out.println(list.toString());
        return Message.success("获取成功！", list);

    }

    /**
     * 缴费列表
     *
     * @return
     */
    @RequestMapping("/userPayInfo")
    public String toUserPayInfo() {
        return "/home/view/userPayInfo";
    }

    /**
     * 获取缴费详细记录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getPayInfo")
    public Message getPayInfo(String payNumber) {
        JSONObject jsonObject = WeChatInterfaceUtil.getUserPayInfo(payNumber);
        if (WeChatInterfaceUtil.judgeResult(jsonObject)) {
            return Message.fail(jsonObject.getString("msg"));
        }
        return Message.success("获取成功！", jsonObject.get("data"));

    }

    /**
     * 挂号记录页面
     *
     * @return
     */
    @RequestMapping("/view/registerRecord")
    public String viewRegisterRecord() {
        return "/home/view/registerRecord";
    }

    /**
     * 获取已就诊的挂号记录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("listRegisterRecord")
    public Message listRegisterRecord() {
		String openId = ApplicationUtil.getSessionOpenId();
//        String openId = "o8VLTjsB7Ezjw8l-3VvdSvCc7Yaw";
        JSONObject jsonObject = WeChatInterfaceUtil.getUserRegisterInfo(openId, null);
        List<String> list = JSONObject.parseArray(jsonObject.getString("data"), String.class);
        if (list != null) {
            String mainTrueName = sysUserMapper.getMainTrueName(openId);
            list = list.stream().filter(x -> mainTrueName.equals(JSON.parseObject(x).getString("XM"))).collect(Collectors.toList());
        }
        //TODO 就诊和未就诊"ZT"
        return Message.success("获取挂号记录成功！", list);
    }

    /**
     * 就诊卡管理页面
     *
     * @return
     */
    @RequestMapping("/view/managementHospitalCard")
    public String viewManagementHospitalCard() {
        return "/home/view/managementHospitalCard";
    }

    /**
     * 获取就诊卡
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("listHospitalCard")
    public Message listHospitalCard() {
        String openId = ApplicationUtil.getSessionOpenId();
//        String openId = "o8VLTjsB7Ezjw8l-3VvdSvCc7Yaw";
        Map<String, List<SysUser>> map = new HashMap<>();
        List<SysUser> userList = sysUserMapper.listHospitalCard(openId);
        List<SysUser> trueEnableList = userList.stream().filter(x -> !x.getEnable()).collect(Collectors.toList());
        List<SysUser> falseEnableList = userList.stream().filter(SysUser::getEnable).collect(Collectors.toList());
        map.put("trueEnableList", trueEnableList);
        map.put("falseEnableList", falseEnableList);
        return Message.success("获取成功！", map);
    }

    /**
     * 管理就诊卡
     *
     * @param idCard
     * @param enable
     * @return
     */
    @ResponseBody
    @RequestMapping("updateHospitalCardStatus")
    public Message updateHospitalCardStatus(String idCard, Integer enable) {
        weChatUserMapper.updateHospitalCardStatus(idCard, enable);
        return Message.success("编辑成功！");
    }

    /**
     * 修改主就诊卡
     *
     * @param idCard
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateHospitalCardMainFlag")
    @Transactional(rollbackFor = Exception.class)
    public Message updateHospitalCardMainFlag(String idCard) {
        Subject subject = ShiroUtil.getSubject();
        weChatUserMapper.updateHospitalCardMainFlagByIdCard(idCard);
        weChatUserMapper.updateHospitalCardMainFlagByUserId(ApplicationUtil.getSessionUserId(), idCard);
        Session session = subject.getSession();
        //查询完整用户信息，设置到session中
        session.setAttribute("user",sysUserMapper.listUserByOpenId(ApplicationUtil.getSessionOpenId()) );
        return Message.success("更新成功！");
    }

    /**
     * 添加就诊人页面
     *
     * @return
     */
    @RequestMapping("/view/bindPatient")
    public String viewBindPatient() {
        return "/home/view/bindPatient";
    }

    /**
     * 增加就诊卡
     *
     * @param sysUser
     * @return
     */
    @ResponseBody
    @RequestMapping("/addHospitalization")
    public Message addHospitalization(SysUser sysUser) {
        String telephone = sysUser.getTelephone();
        if (StringUtil.isBlank(telephone)) {
            return Message.fail("电话号码不能为空！");
        }
        String idCard = sysUser.getIdCard();
        if (StringUtil.isBlank(idCard)) {
            return Message.fail("身份证号不能为空！");
        }
        String trueName = sysUser.getTrueName();
        if (StringUtil.isBlank(trueName)) {
            return Message.fail("真实姓名不能为空！");
        }
        String captcha = sysUser.getCaptcha();
        if (StringUtil.isBlank(captcha)) {
            return Message.fail("验证码不能为空！");
        }
        String openId = ApplicationUtil.getSessionOpenId();
        EhcacheUtil ehcacheUtil = new EhcacheUtil();
        String code = ehcacheUtil.get(openId + "captcha");
        if (captcha.equals(code)) {
            WeChatUser weChatUser = new WeChatUser();
            weChatUser.setIdCard(idCard);
            weChatUser.setMainFlag(0);
            weChatUser.setUserId(ApplicationUtil.getSessionUserId());
            weChatUser.setTelephone(telephone);
            weChatUser.setTrueName(trueName);
            weChatUser.setEnable(false);
            weChatUserMapper.insert(weChatUser);
            //绑定信息到his
            JSONObject jsonObject = WeChatInterfaceUtil.bindUserInfo(openId, trueName, telephone, idCard);
            if (WeChatInterfaceUtil.judgeResult(jsonObject)) {
                return Message.fail("绑定失败，请重新绑定或刷新页面后重试！");
            }
            return Message.success("绑定成功！");
        } else {
            return Message.fail("验证码错误，请重新输入！");
        }
    }


    /**
     * 个人设置页面
     *
     * @return
     */
    @RequestMapping("/view/personalSettings")
    public String viewPersonalSettings(Model model) {
        model.addAttribute("user", sysUserMapper.getUserInfo(ApplicationUtil.getSessionUserId()));
        return "/home/view/personalSettings";
    }

    /**
     * 更新用户名
     *
     * @param userId
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping("updateUsername")
    public Message updateUsername(Integer userId, String username) {
        SysUser sysUser = new SysUser() {{
            setId(userId);
            setUsername(username);
        }};
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        return Message.success("修改成功！");
    }

    /**
     * 健康须知科室页面
     *
     * @return
     */
    @RequestMapping("healthDepartmentList")
    public String healthDepartmentList() {
        return "home/view/healthDepartmentList";
    }

    /**
     * 获取健康须知科室
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getHealthDepartment")
    public Message getHealthDepartment() {
        return Message.success("获取成功！", JSON.parseArray(WeChatInterfaceUtil.getHealthDepartment()));
    }

    /**
     * 健康须知页面
     *
     * @return
     */
    @RequestMapping("healthInfo")
    public String healthInfo() {
        return "home/view/healthInfo";
    }

    /**
     * 获取健康须知
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getHealthInfo")
    public Message getHealthInfo(String department) {
        return Message.success("获取成功！", JSON.parseArray(WeChatInterfaceUtil.getHealthInfo(department)));
    }

    /**
     * 健康须知内容页面
     *
     * @return
     */
    @RequestMapping("healthContent")
    public String healthContent() {
        return "home/view/healthContent";
    }

    /**
     * 获取健康须知内容
     *
     * @param healthInfoId
     * @return
     */
    @ResponseBody
    @RequestMapping("getHealthContent")
    public Message getHealthContent(String healthInfoId) {
        return Message.success("获取成功！", JSON.parseArray(WeChatInterfaceUtil.getHealthContent(healthInfoId)));
    }

    /**
     * 就诊记录页面
     *
     * @return
     */
    @RequestMapping("seekMedicalAdviceRecordList")
    public String seekMedicalAdviceList() {
        return "home/view/seekMedicalAdviceRecordList";
    }

    /**
     * 获取就诊记录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("listSeekMedicalAdviceRecord")
    public Message listSeekMedicalAdviceRecord() {
        String openId = ApplicationUtil.getSessionOpenId();
//        String openId = "o8VLTjsB7Ezjw8l-3VvdSvCc7Yaw";
        String mainTrueName = sysUserMapper.getMainTrueName(openId);
        List<Map> list = (List<Map>) WeChatInterfaceUtil.getSeekMedicalAdviceInfo(openId, null).get("data");
        if(list != null && list.size() != 0) {
            list = list.stream().filter(x -> x.get("XM").equals(mainTrueName)).collect(Collectors.toList());
        }
        return Message.success("获取成功！", list);
    }

    /**
     * 住院记录
     *
     * @return
     */
    @RequestMapping("hospitalizationRecordList")
    public String hospitalizationRecordList() {
        return "home/view/hospitalizationRecordList";
    }

    /**
     * 获取住院记录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("listHospitalizationRecord")
    public Message listHospitalizationRecord() {
        String openId = ApplicationUtil.getSessionOpenId();
//        String openId = "o8VLTjsB7Ezjw8l-3VvdSvCc7Yaw";
        String mainTrueName = sysUserMapper.getMainTrueName(openId);
        List<Map> list = (List<Map>) WeChatInterfaceUtil.getHospitalizationInfo(openId).get("data");
        if(list != null && list.size() != 0) {
            list = list.stream().filter(x -> x.get("XM").equals(mainTrueName)).collect(Collectors.toList());
        }
        return Message.success("获取成功！", list);
    }

    /**
     * 处方
     *
     * @return
     */
    @RequestMapping("prescription")
    public String prescription() {
        return "home/view/prescription";
    }

    /**
     * 处方信息
     *
     * @param seekMedicalAdviceNumber
     * @return
     */
    @ResponseBody
    @RequestMapping("getPrescriptionInfo")
    public Message getPrescriptionInfo(String seekMedicalAdviceNumber) {
        List<Map> list1 = (List<Map>) WeChatInterfaceUtil.listPrescribe(seekMedicalAdviceNumber).get("data");
        List<Map> list2 = (List<Map>) WeChatInterfaceUtil.getPrescribeInfo(seekMedicalAdviceNumber).get("data");
        HashMap<String, List<Map>> map = new HashMap<String, List<Map>>(2){{
            put("prescription", list1);
            put("prescriptionInfo", list2);
        }};
        return Message.success("获取成功！",map);
    }

    /**
     * 其他信息页面
     *
     * @return
     */
    @RequestMapping("otherContent")
    public String otherContent() {
        return "home/view/otherContent";
    }

    /**
     * 其他项目
     *
     * @param seekMedicalAdviceNumber
     * @return
     */
    @ResponseBody
    @RequestMapping("getOtherInfo")
    public Message getOtherInfo(String seekMedicalAdviceNumber) {
        return Message.success("请求成功！",WeChatInterfaceUtil.getOtherContent(seekMedicalAdviceNumber).get("data"));
    }

    /**
     * 日清单列表页面
     *
     * @return
     */
    @RequestMapping("hospitalizationOneDayDataList")
    public String hospitalizationOneDayDataList(){
        return "home/view/hospitalizationOneDayDataList";
    }

    /**
     * 获取日清单列表
     *
     * @param hospitalizationNumber
     * @return
     */
    @ResponseBody
    @RequestMapping("listHospitalizationOneDayData")
    public Message listHospitalizationOneDayData(String hospitalizationNumber) {
        List<Map> list = (List<Map>) WeChatInterfaceUtil.getHospitalizationOneDayData(hospitalizationNumber).get("data");
        list = list.stream().sorted(Comparator.comparing(x -> Integer.parseInt((x.get("TS")).toString()))).collect(Collectors.toList());
        return Message.success("获取成功！", list);
    }

    /**
     * 日清单详情页面
     *
     * @return
     */
    @RequestMapping("hospitalizationOneDayDataInfo")
    public String hospitalizationOneDayDataInfo(){
        return "home/view/hospitalizationOneDayDataInfo";
    }

    /**
     * 获取日清单详情
     *
     * @param hospitalizationNumber
     * @return
     */
    @ResponseBody
    @RequestMapping("getHospitalizationOneDayDataInfo")
    public Message getHospitalizationOneDayDataInfo(String hospitalizationNumber, String date) {
        return Message.success("获取成功！", WeChatInterfaceUtil.getHospitalizationOneDayDataInfo(hospitalizationNumber,
                DateUtil.changeDateTOStr3(DateUtil.changeStrToDate3(date,"yyyy/MM/dd HH:mm:ss"))).get("data"));
    }

    /**
     * 住院结算列表页面
     *
     * @return
     */
    @RequestMapping("hospitalizationDataList")
    public String hospitalizationDataList(){
        return "home/view/hospitalizationDataList";
    }

    /**
     * 获取住院结算列表
     *
     * @param hospitalizationNumber
     * @return
     */
    @ResponseBody
    @RequestMapping("listHospitalizationData")
    public Message listHospitalizationData(String hospitalizationNumber) {
        return Message.success("获取成功！", WeChatInterfaceUtil.getHospitalizationData(hospitalizationNumber).get("data"));
    }

    /**
     * 住院结算详情页面
     *
     * @return
     */
    @RequestMapping("hospitalizationDataInfo")
    public String hospitalizationDataInfo(){
        return "home/view/hospitalizationDataInfo";
    }

    /**
     * 获取住院结算详情
     *
     * @param billNumber
     * @return
     */
    @ResponseBody
    @RequestMapping("getHospitalizationDataInfo")
    public Message getHospitalizationDataInfo(String billNumber) {
        return Message.success("获取成功！", WeChatInterfaceUtil.getHospitalizationDataInfo(billNumber).get("data"));
    }

    /**
     * 跳转到满意度调查
     *
     * @return
     */
    @RequestMapping("/satisfactionSurvey")
    public String satisfactionSurvey() {
        return "/home/view/satisfactionSurvey";
    }

    /**
     * 获取满意度调查
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getSatisfactionSurvey")
    public Message getSatisfactionSurvey() {
        return Message.success("获取成功！",WeChatInterfaceUtil.getSatisfactionSurvey() );
    }

    /**
     * 新增满意度调查
     *
     * @param arr
     * @return
     */
    @ResponseBody
    @RequestMapping("insertSatisfactionSurveyResult")
    public Message insertSatisfactionSurveyResult(String arr) {
        JSONObject jsonObject = WeChatInterfaceUtil.insertSatisfactionSurveyResult(arr);
        if ("0".equals(jsonObject.get("ret"))) {
            return Message.success("新增成功！");
        }else {
            return Message.fail("新增失败！");
        }
    }

    /**
     * 预交款明细页面
     *
     * @return
     */
    @RequestMapping("prePayMoney")
    public String prePayMoney() {
        return "home/view/prePayMoney";
    }

    /**
     * 获取预交款明细
     *
     * @param hospitalizationNumber
     * @return
     */
    @ResponseBody
    @RequestMapping("getPrePayMoney")
    public Message getPrePayMoney(String hospitalizationNumber) {
        List<Map> list = (List<Map>) WeChatInterfaceUtil.getPrePayMoney(hospitalizationNumber).get("data");
        if (list != null && list.size() != 0) {
            list = list.stream().peek(x -> x.put("MXLST", WeChatInterfaceUtil.getPrePayMoneyInfo(hospitalizationNumber).get("data"))).collect(Collectors.toList());
        }
        return Message.success("获取成功！", list);
    }

    /**
     * 获取手机BI权限
     *
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("getBIPermission")
    public Message getBIPermission(Integer userId) {
        return Message.success("获取成功！", sysUserMapper.getBIPermission(userId));
    }

    /**
     * 预交费页面
     *
     * @return
     */
    @RequestMapping("payPreMoney")
    public String payPreMoney() {
        return "home/view/payPreMoney";
    }
}
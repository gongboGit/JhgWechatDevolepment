package com.jhg.marketing.web.util.hisInterface;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jhg.marketing.web.util.StringUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lxl
 * @since 2019/7/6 14:57
 */
public class WeChatInterfaceUtil {

    private static ICommonWeChatApi iCommonWeChatApi = new ICommonWeChatApi();
    private static ICommonWeChatApiSoap iCommonWeChatApiSoap = iCommonWeChatApi.getICommonWeChatApiSoap();

    /**
     * 调用接口
     *
     * @param typeCode 接口类型
     * @param openId   openId
     * @param map      参数map
     * @return 接口调用信息
     */
    @SafeVarargs
    public static JSONObject getWeChatInterface(String typeCode, String openId, Map<String, String>... map) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", typeCode);
            put("openId", openId);
        }};
        if (map != null && map.length != 0) {
            Arrays.stream(map).forEach(param::putAll);
        }
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 绑定用户信息
     *
     * @param openId   openId
     * @param trueName 用户真实姓名
     * @param phone    电话号码
     * @param idCard   身份证号码
     * @return 用户信息
     */
    public static JSONObject bindUserInfo(String openId, String trueName, String phone, String idCard) {
        Map<String, String> param = new HashMap<String, String>(7) {{
            put("TypeCode", "1001");
            put("openId", openId);
            put("xinMing", trueName);
            put("phone", phone);
            put("idCard", idCard);
            put("sfmrbr", null);
            put("code", null);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取绑定用户信息
     *
     * @param openId openId
     * @return 绑定信息
     */
    public static JSONObject getBindUserInfo(String openId) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "1002");
            put("openId", openId);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取用户挂号信息
     *
     * @param openId         openId
     * @param registerNumber 单据号
     * @return 挂号记录
     */
    public static JSONObject getUserRegisterInfo(String openId, String registerNumber) {
        Map<String, String> param = new HashMap<String, String>(3) {{
            put("TypeCode", "1003");
            put("openId", openId);
            if (registerNumber != null) {
                put("DanJuHao", registerNumber);
            }
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取缴费记录
     *
     * @param openId openId
     * @return 缴费记录
     */
    public static JSONObject listUserPayInfo(String openId) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "1004");
            put("openId", openId);
        }};
        System.out.println(param.toString());
        String str=iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param));
        System.out.println(str);
        return JSONObject.parseObject(str);
    }

    /**
     * 获取缴费详细记录
     *
     * @param payNumber 缴费单号
     * @return 缴费详细记录
     */
    public static JSONObject getUserPayInfo(String payNumber) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "1005");
            put("DanJuHao", payNumber);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取就诊记录
     *
     * @param openId openId
     * @return 就诊记录
     */
    public static JSONObject getSeekMedicalAdviceInfo(String openId, String name) {
        Map<String, String> param = new HashMap<String, String>(3) {{
            put("TypeCode", "1006");
            put("openId", openId);
            if (StringUtil.isNotBlank(name)) {
                put("XM", name);
            }
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取就诊处方
     *
     * @param seekMedicalAdviceNumber 就诊号
     * @return 就诊处方
     */
    public static JSONObject listPrescribe(String seekMedicalAdviceNumber) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "1008");
            put("JiuZhenHao", seekMedicalAdviceNumber);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取就诊处方明细
     *
     * @param seekMedicalAdviceNumber 就诊号
     * @return 就诊处方明细
     */
    public static JSONObject getPrescribeInfo(String seekMedicalAdviceNumber) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "1009");
            put("JiuZhenHao", seekMedicalAdviceNumber);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取住院记录
     *
     * @param openId openId
     * @return 住院记录
     */
    public static JSONObject getHospitalizationInfo(String openId) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "4001");
            put("openId", openId);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取就诊检验记录
     *
     * @param seekMedicalAdviceNumber 就诊号
     * @return 就诊检验记录
     */
    public static JSONObject listSeekMedicalAdviceTestInfo(String seekMedicalAdviceNumber) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "1010");
            put("JiuZhenHao", seekMedicalAdviceNumber);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取住院检验记录
     *
     * @param hospitalizationNumber 住院号
     * @return 住院检验记录
     */
    public static JSONObject listHospitalizationTestInfo(String hospitalizationNumber) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "4006");
            put("ZhuYuanHao", hospitalizationNumber);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取就诊检验记录详情
     *
     * @param testNumber 检验申请单号
     * @return 就诊检验记录详情
     */
    public static JSONObject getSeekMedicalAdviceTestInfo(String testNumber) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "1011");
            put("ShenQingDanHao", testNumber);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取住院检验记录详情
     *
     * @param testNumber 检验单号
     * @return 住院检验记录详情
     */
    public static JSONObject getHospitalizationTestInfo(String testNumber) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "4007");
            put("ShenQingDanHao", testNumber);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取就诊检查报告
     *
     * @param seekMedicalAdviceNumber 就诊号
     * @return 就诊检查报告
     */
    public static JSONObject listSeekMedicalAdviceInspectInfo(String seekMedicalAdviceNumber) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "1012");
            put("JiuZhenHao", seekMedicalAdviceNumber);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取住院检查报告
     *
     * @param hospitalizationNumber 住院号
     * @return 住院检查报告
     */
    public static JSONObject listHospitalizationInspectInfo(String hospitalizationNumber) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "4008");
            put("ZhuYuanHao", hospitalizationNumber);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取体检单据信息
     *
     * @param startTime openId
     * @param endTime openId
     * @param idCard openId
     * @return 体检单据信息
     */
    public static JSONObject getPhysicalExaminationBillInfo(String startTime, String endTime,String idCard) {
        Map<String, String> param = new HashMap<String, String>(4) {{
            put("TypeCode", "4013");
            put("StartTime", startTime);
            put("EndTime", endTime);
            put("idCard", idCard);
        }};
        System.out.println(JSON.toJSONString(param));
        String str=iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param));
        System.out.println(str);
        return JSONObject.parseObject(str);
    }

    /**
     * 获取体检信息
     *
     * @param physicalExaminationNumber 体检单据号
     * @return 体检信息
     */
    public static JSONObject listPhysicalExaminationInfo(String physicalExaminationNumber) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "4014");
            put("DanJuHao", physicalExaminationNumber);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取体检信息
     *
     * @param physicalExaminationNumber 体检单据号
     * @return 体检信息
     */
    public static JSONObject getPhysicalExaminationInfo(String physicalExaminationNumber) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "4015");
            put("DanJuHao", physicalExaminationNumber);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取就诊除药品、检验、检查以外的其它项目
     *
     * @param seekMedicalAdviceNumber 就诊号
     * @return
     */
    public static JSONObject getOtherContent(String seekMedicalAdviceNumber) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "1013");
            put("JiuZhenHao", seekMedicalAdviceNumber);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取住院预交金额
     *
     * @param hospitalizationNumber 住院号
     * @return 住院预交金额
     */
    public static JSONObject getPrePayMoney(String hospitalizationNumber) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "4002");
            put("ZhuYuanHao", hospitalizationNumber);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取住院预交金额详情
     *
     * @param hospitalizationNumber 住院号
     * @return 住院预交金额详情
     */
    public static JSONObject getPrePayMoneyInfo(String hospitalizationNumber) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "4003");
            put("ZhuYuanHao", hospitalizationNumber);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取住院一日清单
     *
     * @param hospitalizationNumber 住院号
     * @return 住院一日清单
     */
    public static JSONObject getHospitalizationOneDayData(String hospitalizationNumber) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "4009");
            put("ZhuYuanHao", hospitalizationNumber);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取住院一日清单明细
     *
     * @param hospitalizationNumber 住院号
     * @return 住院一日清单明细
     */
    public static JSONObject getHospitalizationOneDayDataInfo(String hospitalizationNumber, String date) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "4010");
            put("ZhuYuanHao", hospitalizationNumber);
            put("RiQi", date);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取住院结算清单
     *
     * @param hospitalizationNumber 住院号
     * @return 住院结算清单
     */
    public static JSONObject getHospitalizationData(String hospitalizationNumber) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "4011");
            put("ZhuYuanHao", hospitalizationNumber);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取住院结算清单明细
     *
     * @param billNumber 单据号
     * @return 住院结算清单明细
     */
    public static JSONObject getHospitalizationDataInfo(String billNumber) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "4012");
            put("DanJuHao", billNumber);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取满意度调查
     *
     * @return 调查问卷
     */
    public static String getSatisfactionSurvey() {
        Map<String, String> param = new HashMap<String, String>(1) {{
            put("TypeCode", "4016");
        }};
        String s = iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param));
        return s;
    }

    /**
     * 添加满意度调查结果
     *
     * @param satisfactionSurveyParam 参数
     * @return 是否成功
     */
    public static JSONObject insertSatisfactionSurveyResult(String satisfactionSurveyParam) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "4017");
            put("arr", satisfactionSurveyParam);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 获取健康须知科室
     *
     * @return
     */
    public static String getHealthDepartment() {
        Map<String, String> param = new HashMap<String, String>(1) {{
            put("TypeCode", "4020");
        }};
        return iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param));
    }

    /**
     * 获取健康需知信息
     *
     * @param department 科室
     * @return
     */
    public static String getHealthInfo(String department) {
        Map<String, String> param = new HashMap<String, String>(1) {{
            put("TypeCode", "4021");
            put("ks", department);
        }};
        return iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param));
    }

    /**
     * 获取健康需知信息
     *
     * @param healthInfoId 健康须知内容
     * @return
     */
    public static String getHealthContent(String healthInfoId) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "4022");
            put("ID", healthInfoId);
        }};
        return iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param));
    }
    /**
     * 获取科室信息
     *
     * @param registerType
     * @return
     */
    public static JSONObject getDepartmentInfo(String registerType) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("TypeCode", "2001");
            put("Day", registerType);
        }};
        return JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }
    /**
     * 获取医生信息
     *
     * @param registerType
     * @param date
     * @param departmentId
     * @return
     */
    public static JSONObject getDoctorInfo(String registerType, String date, String departmentId) {
        Map<String, String> param = new HashMap<String, String>(3) {{
            put("TypeCode", "2002");
            put("Day", registerType);
            put("DeptID", departmentId);
        }};
        return  JSONObject.parseObject(iCommonWeChatApiSoap.weChatBusiness(JSON.toJSONString(param)));
    }

    /**
     * 检测返回结果
     *
     * @param jsonObject json字符串
     * @return true
     */
    public static boolean judgeResult(JSONObject jsonObject) {
        return "0".equals(jsonObject.getString("ret"));
    }
}

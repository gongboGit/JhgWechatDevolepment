package com.jhg.marketing.web.util.previewRegister;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lxl
 * @since 2019/7/6 14:57
 */
public class PreviewRegisterUtil {

    private static WebServiceImplService webServiceImplService = new WebServiceImplService();
    private static WebServiceImpl webServiceImpl = webServiceImplService.getWebServiceImplPort();


    /**
     * 获取科室信息
     *
     * @param registerType
     * @return
     */
    public static JSONObject getDepartmentInfo(String registerType) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("typeCode", "0002");
            put("msg", registerType);
        }};
        return JSONObject.parseObject(webServiceImpl.sayHello(JSON.toJSONString(param)));
    }

    /**
     * 获取医生信息
     *
     * @param registerType
     * @param date
     * @param departmentId
     * @return
     */
    public static String getDoctorInfo(String registerType, String date, String departmentId) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("typeCode", "0003");
            put("registerType", registerType);
            put("date", date);
            put("deptID", departmentId);
        }};
        return webServiceImpl.sayHello(JSON.toJSONString(param));
    }

    /**
     * 挂号
     *
     * @param msg
     * @return
     */
    public static JSONObject submitRegister(Map<String, String> msg) {
//        Map<String, String> msg = new HashMap<>(9);
//        msg.put("cardID", "");
//        msg.put("name", "");
//        msg.put("phone", "");
//        msg.put("payAmt", "");
//        msg.put("payNo", "");
//        msg.put("day", "");
//        msg.put("asRowid", "");
//        msg.put("deptID", "");
//        msg.put("examId", "");
        Map<String, String> param = new HashMap<String, String>(1) {{
            put("typeCode", "4005");
        }};
        param.putAll(msg);
        return JSONObject.parseObject(webServiceImpl.sayHello(JSON.toJSONString(param)));
    }

    /**
     * 获取缴费明细
     *
     * @param patientId
     * @return
     */
    public static JSONObject getPayDetailInfo(String patientId) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("typeCode", "5001");
            put("msg", patientId);
        }};
        return JSONObject.parseObject(webServiceImpl.sayHello(JSON.toJSONString(param)));
    }

    /**
     * 缴费
     *
     * @param patientId
     * @return
     */
    public static JSONObject submitPay(String patientId) {
        Map<String, String> param = new HashMap<String, String>(2) {{
            put("typeCode", "5002");
            put("msg", patientId);
        }};
        return JSONObject.parseObject(webServiceImpl.sayHello(JSON.toJSONString(param)));
    }



}

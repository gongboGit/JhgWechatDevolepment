import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jhg.marketing.dao.domin.material.MessageNews;
import com.jhg.marketing.dao.mapper.PermissionMapper;
import com.jhg.marketing.dao.mapper.SysUserMapper;
import com.jhg.marketing.dao.mapper.WeChatFansMapper;
import com.jhg.marketing.web.AccurateStarter;
import com.jhg.marketing.web.admin.controller.AjaxDataController;
import com.jhg.marketing.web.admin.controller.MaterialController;
import com.jhg.marketing.web.admin.controller.MenuController;
import com.jhg.marketing.web.admin.controller.TagController;
import com.jhg.marketing.web.admin.service.Message;
import com.jhg.marketing.web.controller.IndexController;
import com.jhg.marketing.web.controller.PersonalCenterController;
import com.jhg.marketing.web.service.SysRoleService;
import com.jhg.marketing.web.util.AccessTokenUtil;
import com.jhg.marketing.web.util.ApplicationUtil;
import com.jhg.marketing.web.util.hisInterface.WeChatInterfaceUtil;
import com.jhg.marketing.web.util.previewRegister.PreviewRegisterUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URLEncoder;
import java.util.*;

/**
 * @author lxl
 * @since 2019/4/15 8:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccurateStarter.class)
public class LXLTest {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private AccessTokenUtil accessTokenUtil;
    @Autowired
    private PermissionMapper permissionMapper;

    @Test
    public void testListRole(){
        System.out.println(UUID.randomUUID().toString());
    }

    @Test
    public void testPermission() {
        System.out.println(permissionMapper.listPermissionByRoleId(null));
    }

    @Test
    public void testJson(){
        System.out.println(JSON.parseObject("{\"后台菜单\":1,\"微信菜单\":2}", Map.class));
    }

    @Test
    public void testJson2(){
        Map<Object, Object> map = new HashMap<>();
        List<MessageNews> articles = new ArrayList<>();
        MessageNews messageNews = new MessageNews();
        messageNews.setThumbUrl("123");
        articles.add(messageNews);
        map.put("articles", articles);
        System.out.println(JSON.toJSONString(map, SerializerFeature.WriteNullStringAsEmpty));
    }

    @Test
    public void testPath(){
        System.out.println(ApplicationUtil.getWebRealPath());
    }
    
    @Test
    public void testSplit(){
        String imgSrc = "http://localhost:8090/assets/layui/images/face/49.gif";
        String[] split = imgSrc.split("/");
        int k = imgSrc.indexOf(split[3]);
        System.out.println(imgSrc.substring(k));
    }

    @Autowired
    private MaterialController materialController;

    @Test
    public void testMaterial(){
        Message message = materialController.listNewsMaterial(1, 10);
        System.out.println(JSON.toJSONString(message));
    }

    @Autowired
    private MenuController menuController;

    @Test
    public void testGetMenu() {
//        System.out.println(menuController.getMenuInfo());
    }

    @Test
    public void testJson3(){
        String s = "{\"touser\": [\"OPENID1\",\"OPENID2\"],\"mpnews\": {\"media_id\": \"123dsdajkasd231jhksad\"},\"msgtype\": \"mpnews\",\"send_ignore_reprint\": 0}";
        Map map = JSONObject.parseObject(s, Map.class);
        String msgtype = ((String) map.get("msgtype"));
        //如果是图文消息
        if ("mpnews".equals(msgtype)) {
            String mediaId = ((Map) map.get("mpnews")).get("media_id").toString();
            System.out.println(mediaId);
//            Map map1 = JSONObject.parseObject(mpnews, Map.class);
//            String mediaId = (String)map1.get("media_id");

//            System.out.println(mediaId);

        }
    }

    @Autowired
    private SysUserMapper sysUserMapper;
    @Test
    public void testGetSomeExpertInfoInterface(){
        System.out.println(JSON.toJSONString(sysUserMapper.getSomeExpertInfo()));
    }

    @Test
    public void testParam(){
        Map<String, String> param = new HashMap<String, String>() {{
            put("TypeCode", "1002");
            put("openId", "oDwomv38PGypfe5ssU-4ED3KwUBM");
        }};
        Map<String, String> map = new HashMap<String, String>() {{
            put("test", "test");
        }};
        param.putAll(map);
        System.out.println(JSON.toJSONString(param));
    }

    @Test
    public void testWeChatInterface(){
        //1002 {"ret":"0","msg":"未获取到关联信息,请先操作关联就诊人"}
//        System.out.println(WeChatInterfaceUtil.getWeChatInterface("1002", "oDwomv38PGypfe5ssU-4ED3KwUBM"));
//        System.out.println(WeChatInterfaceUtil.getBindUserInfo("o8VLTjsB7Ezjw8l-3VvdSvCc7Yaw"));
//        System.out.println(WeChatInterfaceUtil.getUserRegisterInfo("o8VLTjsB7Ezjw8l-3VvdSvCc7Yaw", null));
//        System.out.println(WeChatInterfaceUtil.listUserPayInfo("ojWHx0sJ1zV5jhc9AdVwoYfYCnOM"));
//        System.out.println(WeChatInterfaceUtil.getSeekMedicalAdviceInfo("oDwomv38PGypfe5ssU-4ED3KwUBM"));
//        System.out.println(WeChatInterfaceUtil.getHospitalizationInfo("ojWHx0sJ1zV5jhc9AdVwoYfYCnOM").getString("data"));
//        System.out.println(WeChatInterfaceUtil.bindUserInfo("ojWHx0sJ1zV5jhc9AdVwoYfYCnOM","周立斌","15922760646","511303198701263376").getString("data"));
//        System.out.println(WeChatInterfaceUtil.listHospitalizationInspectInfo("900074744"));
//        System.out.println(WeChatInterfaceUtil.listHospitalizationTestInfo("2018033329"));
//        System.out.println(WeChatInterfaceUtil.getPhysicalExaminationBillInfo("ojWHx0sJ1zV5jhc9AdVwoYfYCnOM"));
//        System.out.println(WeChatInterfaceUtil.listUserPayInfo("o8VLTjsB7Ezjw8l-3VvdSvCc7Yaw"));
        System.out.println(WeChatInterfaceUtil.getUserPayInfo("T0115190"));
    }

    @Test
    public void testSeekMedicalAdviceInterface(){
        JSONObject seekMedicalAdviceInfo = WeChatInterfaceUtil.getSeekMedicalAdviceInfo("o8VLTjsB7Ezjw8l-3VvdSvCc7Yaw", null);
        System.out.println(seekMedicalAdviceInfo);
        List<String> list = JSONObject.parseArray(seekMedicalAdviceInfo.getString("data"), String.class);
        if (list != null) {
            list.forEach(x -> {
                String seekMedicalAdviceNumber = JSON.parseObject(x).getString("JZH");
                //检验
                JSONObject listSeekMedicalAdviceTestInfo = WeChatInterfaceUtil.listSeekMedicalAdviceTestInfo(seekMedicalAdviceNumber);
                System.out.println("--"+listSeekMedicalAdviceTestInfo);
                List<String> list1 = JSONObject.parseArray(listSeekMedicalAdviceTestInfo.getString("data"), String.class);
                if (list1 != null) {
                    list1.forEach(y -> {
                        String testApplicationNumber = JSON.parseObject(y).getString("NO");
                    System.out.println("**"+WeChatInterfaceUtil.getSeekMedicalAdviceTestInfo(testApplicationNumber));
                    });
                }
                 //检查
//                System.out.println(WeChatInterfaceUtil.listSeekMedicalAdviceInspectInfo(seekMedicalAdviceNumber));
            });
        }
    }

    @Test
    public void testHospitalizationInterface() {
//        System.out.println(WeChatInterfaceUtil.getHospitalizationInfo("o8VLTjsB7Ezjw8l-3VvdSvCc7Yaw"));
//        List<String> list = JSONObject.parseArray(WeChatInterfaceUtil.getHospitalizationInfo("o8VLTjsB7Ezjw8l-3VvdSvCc7Yaw").getString("data"), String.class);
//        if (list != null) {
//            list.forEach(x -> {
//                String hospitalizationNumber = JSON.parseObject(x).getString("ZYH");
//                //检验
//                System.out.println(WeChatInterfaceUtil.listHospitalizationTestInfo(hospitalizationNumber));
//                List<String> list1 = JSONObject.parseArray(WeChatInterfaceUtil.listHospitalizationTestInfo(hospitalizationNumber).getString("data"), String.class);
//                if (list1 != null) {
//                    list1.forEach(y -> {
//                        String testNumber = JSON.parseObject(y).getString("NO");
//                        System.out.println(WeChatInterfaceUtil.getHospitalizationTestInfo(testNumber));
//                    });
//                }

//                //检查
//                System.out.println(WeChatInterfaceUtil.listHospitalizationInspectInfo(hospitalizationNumber));
//            });
//        }
        //体检
      /*  System.out.println(WeChatInterfaceUtil.getPhysicalExaminationBillInfo("o8VLTjuzTY_2EnETMuWcXXSJxM_8"));
        List<String> list3 = JSONObject.parseArray(WeChatInterfaceUtil.getPhysicalExaminationBillInfo("o8VLTjuzTY_2EnETMuWcXXSJxM_8").getString("data"), String.class);
        if (list3 != null) {
            list3.forEach(z -> {
                String physicalExaminationNumber = JSON.parseObject(z).getString("TJBH");
                System.out.println(WeChatInterfaceUtil.listPhysicalExaminationInfo(physicalExaminationNumber));
                List<String> list4 = JSONObject.parseArray(WeChatInterfaceUtil.listPhysicalExaminationInfo(physicalExaminationNumber).getString("data"), String.class);
                if (list4 != null) {
                    list4.forEach(j -> {
                        String xh = JSON.parseObject(j).getString("XH");
                        System.out.println(WeChatInterfaceUtil.getPhysicalExaminationInfo(xh));
                    });
                }
            });
        }*/

    }

    @Test
    public void testRegisterInterface(){
//        System.out.println(WeChatInterfaceUtil.getUserRegisterInfo("o8VLTjsB7Ezjw8l-3VvdSvCc7Yaw", "S0039715"));
//        System.out.println(WeChatInterfaceUtil.getUserRegisterInfo("oDwomv38PGypfe5ssU-4ED3KwUBM", null));
//        System.out.println(WeChatInterfaceUtil.bindUserInfo());
    }

    @Autowired
    private PersonalCenterController personalCenterController;

    @Test
    public void testListRegisterRecord(){
        System.out.println(personalCenterController.listRegisterRecord());
    }

    @Test
    public void testListHospitalCard(){
        System.out.println(sysUserMapper.listHospitalCard("o8VLTjsB7Ezjw8l-3VvdSvCc7Yaw"));
    }

    @Autowired
    private IndexController indexController;

    @Test
    public void testListInspect(){
        System.out.println(indexController.listInspect(null,null));
    }

    @Test
    public void testGetInspectInfo(){
        System.out.println(indexController.getInspectInfo("23935"));
    }

    @Test
    public void testListPhysicalExamination(){
//        System.out.println(indexController.listPhysicalExamination(null,null));
//        System.out.println(indexController.getPhysicalExaminationInfo("201900062"));
        System.out.println(WeChatInterfaceUtil.getBindUserInfo("ojWHx0pe8hzH0VnvWsxvGBmvBTxE"));
    }

    @Test
    public void testListTest(){
//        System.out.println(indexController.listTest(null, null));
        System.out.println(WeChatInterfaceUtil.listSeekMedicalAdviceTestInfo("T0003546"));
    }

    @Autowired
    private TagController tagController;
    @Test
    public void testTagController(){
//        System.out.println(tagController.createTag("护士"));
//        System.out.println(tagController.syncTag());
//        System.out.println(tagController.updateTagName(100, "医护人员"));
//        System.out.println(tagController.deleteTag(101));
//        System.out.println(tagController.batchTagging(new ArrayList<String>() {{
//            add("ojWHx0sJ1zV5jhc9AdVwoYfYCnOM");
//            add("ojWHx0gfSogMUHh-5DzlO1OQmZaA");
//        }}, 100));
        System.out.println(tagController.batchUnTagging(new ArrayList<String>() {{
            add("ojWHx0gfSogMUHh-5DzlO1OQmZaA");
        }}, 100));
    }

    @Test
    public void testSatisfactionSurvey(){
//        System.out.println(WeChatInterfaceUtil.getSatisfactionSurvey());
        System.out.println(WeChatInterfaceUtil.getSatisfactionSurvey());
    }

    @Autowired
    private WeChatFansMapper weChatFansMapper;
    @Test
    public void testListFans(){
        System.out.println(JSON.toJSONString(weChatFansMapper.litFans()));
    }

    @Autowired
    private AjaxDataController ajaxDataController;
    @Test
    public void testListUser(){
        System.out.println(JSON.toJSONString(ajaxDataController.listSysUser(null)));
    }

    @Test
    public void testPersonalCenter(){
//        System.out.println(personalCenterController.getHealthDepartment());
//        JSONObject hospitalizationInfo = WeChatInterfaceUtil.getHospitalizationInfo("o8VLTjsB7Ezjw8l-3VvdSvCc7Yaw");
//        System.out.println(hospitalizationInfo);
//        System.out.println(personalCenterController.listHospitalizationRecord());
//        System.out.println(WeChatInterfaceUtil.listPrescribe("T0038492"));
//        System.out.println(WeChatInterfaceUtil.getPrescribeInfo("T0038492"));
//        System.out.println(personalCenterController.getPrescriptionInfo("T0038492"));
//        System.out.println(WeChatInterfaceUtil.getHospitalizationInfo("o8VLTjsB7Ezjw8l-3VvdSvCc7Yaw"));
//        System.out.println(sysUserMapper.getBIPermission(4));
//        System.out.println(personalCenterController.getHospitalizationOneDayDataInfo("19080085","2019/8/17 00:00:00"));
//        System.out.println(URLDecoder.decode("%69%20%6C%6F%76%65%20%75"));
        System.out.println(URLEncoder.encode("http://weixin.cqjhgyyxx.com/view/appointmentRegistration"));
//        System.out.println(personalCenterController.getPrescriptionInfo("232278"));
//        System.out.println( WeChatInterfaceUtil.listPrescribe("232278"));
//        System.out.println( WeChatInterfaceUtil.getPrescribeInfo("232278"));
//        System.out.println(PreviewRegisterUtil.getDepartmentInfo("普通"));
//        System.out.println(PreviewRegisterUtil.getDoctorInfo("普通","2019-08-30","81"));
//        String str = "{\"cardID\":\"510202195008162140\",\"name\":\"钱素芳\",\"phone\":\"18875198751\",\"payAmt\":\"3" +
//                ".5\",\"payNo\":1567131038376,\"day\":\"2019-08-30\",\"asRowid\":\"56\",\"deptID\":\"81\"," +
//                "\"examId\":\"8214\"}";
//        System.out.println(PreviewRegisterUtil.submitRegister(((Map) JSON.parse(str))));
    }

}

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.jhg.marketing.dao.domin.AccessToken;
import com.jhg.marketing.dao.mapper.WeChatFansMapper;
import com.jhg.marketing.dao.mapper.WeChatUserMapper;
import com.jhg.marketing.web.AccurateStarter;
import com.jhg.marketing.web.admin.controller.TemplateMessageController;
import com.jhg.marketing.web.admin.controller.UserController;
import com.jhg.marketing.web.admin.service.Message;
import com.jhg.marketing.web.util.AccessTokenUtil;
import com.jhg.marketing.web.util.DateUtil;
import com.jhg.marketing.web.util.exception.WxErrorException;
import com.jhg.marketing.web.util.menu.MenuUtil;
import com.jhg.marketing.web.util.user.UserUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author lxl
 * @since 2019/7/24 14:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccurateStarter.class)
public class AdminTest {

    @Autowired
    private MenuUtil menuUtil;
    @Autowired
    private AccessTokenUtil accessTokenUtil;
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private WeChatFansMapper weChatFansMapper;

    @Test
    public void testFlushAccessToken(){
        accessTokenUtil.getAccessTokenByInterface();
    }

    @Test
    public void testMenu() {
        try {
            menuUtil.createMenu(menuUtil.initMenu());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private UserController userController;

    @Test
    public void testUserController(){
        //System.out.println(userController.ListFans(""));
        ArrayList<String> strings = new ArrayList<>();
        strings.add("ojWHx0oxSwrintVH-7a_p-lx-OJI");
        try {
            JSONObject jsonObject = userUtil.batchGetFansInfo(strings);
            List<Map> userInfoList = jsonObject.getObject("user_info_list", List.class);
//            ArrayList<Map<Object, Object>> maps = new ArrayList<>();
//            for (Object s : userInfoList) {
//                Map s1 = (Map) s;
//
////                Map map = JSON.parseObject((String) s, Map.class);
//                maps.add(s1);
//            }
            System.out.println(userInfoList);
            weChatFansMapper.insertWeChatFansList(userInfoList);
//            List<Map> list = userInfoList.stream().map(x -> JSON.parseObject(x, Map.class)).collect(Collectors.toList());
//            System.out.println(list);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testSynchronizationFans(){
        System.out.println(userController.synchronizationFans());
    }

    @Test
    public void test() throws UnsupportedEncodingException {
//        System.out.println(weChatFansMapper.getLastOpenId());
//        System.out.println(weChatFansMapper.listFansByCondition("安然"));
//        System.out.println(new String(Base64.getEncoder().encode("安1然2淡漠".getBytes()), "utf-8"));
//        System.out.println(new String(Base64.getEncoder().encode("然2".getBytes()), "utf-8"));
        System.out.println(URLEncoder.encode("/BI/index.html"));
    }

    @Autowired
    private TemplateMessageController templateMessageController;
    @Test
    public void testMessageTemplateController(){
        System.out.println(templateMessageController.getTemplateInfo("ZR5RZHdOI9FFPStl2Jhz93-yN_IzrGMIaQpjPW9UuAY"));
    }

    private static HashMap getSendDataTemp(String content) {
        HashMap<String, Object> data = new HashMap<String,Object>();
        Pattern pattern = Pattern.compile("\\{\\s*\\{.*\\.DATA\\s*\\}\\s*\\}");
        Matcher matcher = pattern.matcher(content);
        //循环，字符串中有多少个符合的，就循环多少次
        while(matcher.find()){
            //每一个符合正则的字符串
            String e = matcher.group();
            Pattern p = Pattern.compile("[^(\\{\\s*\\{\\s*)|(\\.DATA\\s*\\}\\s*\\})]+");
            Matcher m = p.matcher(e);
            m.find();
            String key = m.group();
            System.out.println(key+":h:"+key.length());
            HashMap<String, String> t = new HashMap<String,String>();
            t.put("value", key);
            t.put("color", "#173177");
            data.put(key, t);

        }
        return data;
    }

    public static void main(String[] args) {
//        String content = "{{first.DATA}}\n护理项目：{{keyword1.DATA}}\n主治医生：{{keyword2.DATA}}\n{{remark.DATA}}";
//        System.out.println(getSendDataTemp(content));
        System.out.println(DateUtil.changeStrToDate3("2019/07/30", "yyyy/MM/dd"));
//        CopyOnWriteArrayList;
    }
}

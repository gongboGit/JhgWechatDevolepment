package com.jhg.marketing.web.util.menu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jhg.marketing.dao.domin.menu.Button;
import com.jhg.marketing.dao.domin.menu.ClickButton;
import com.jhg.marketing.dao.domin.menu.Menu;
import com.jhg.marketing.dao.domin.menu.ViewButton;
import com.jhg.marketing.web.util.AccessTokenUtil;
import com.jhg.marketing.web.util.WeChatUtil;
import com.jhg.marketing.web.util.exception.WxError;
import com.jhg.marketing.web.util.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lxl
 * @since 2019/4/8 13:26
 */
@Component
public class MenuUtil {

    public static final Integer MENU_NOT_CREATE = 46003;

    @Value("${user.domain}")
    private String domain;

    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private AccessTokenUtil accessTokenUtil;


    /**
     * 创建菜单
     *
     * @param menu 菜单json格式字符串
     * @return
     */
    public JSONObject createMenu(String menu) throws WxErrorException {
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getMenuCreateUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), menu);
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != 0) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 获取菜单
     *
     * @return
     */
    public JSONObject getMenu() throws WxErrorException {
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getMenuGetUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.GET.toString());
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != null) {
            if (wxError.getErrorCode().equals(MENU_NOT_CREATE)) {
                return null;
            }
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 删除菜单，在个性化菜单时，调用此接口会删除默认菜单及全部个性化菜单。
     *
     * @return
     */
    public JSONObject deleteMenu() throws WxErrorException {
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getMenuDeleteUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.GET.toString());
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != 0) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 创建个性化菜单
     *
     * @param menu
     * @return
     */
    public JSONObject createConditionalMenu(String menu) throws WxErrorException {
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getMenuAddConditionalUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), menu);
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 删除个性化菜单
     *
     * @param menuId:{"menuid":"208379533"}
     * @return
     */
    public JSONObject delConditionalMenu(String menuId) throws WxErrorException {
        Map map = new HashMap<String, String>(1) {{
            put("menuid", menuId);
        }};
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getMenuDelConditionalUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), JSON.toJSONString(map));
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != 0) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 测试匹配个性化菜单
     *
     * @param userId :{"user_id":"weixin"}
     *               user_id可以是粉丝的OpenID，
     *               也可以是粉丝的微信号。
     * @return
     */
    public JSONObject tryMachConditionalMenu(String userId) throws WxErrorException {
        Map map = new HashMap<String, String>(1) {{
            put("user_id", userId);
        }};
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getTryMatchUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), JSON.toJSONString(map));
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 获取自定义菜单配置
     * <p>
     * 如果公众号是通过API调用设置的菜单，
     * 则返回菜单的开发配置，而如果公众号
     * 是在公众平台官网通过网站功能发布菜单，
     * 则本接口返回运营者设置的菜单配置
     *
     * @return
     */
    public JSONObject getCurrentSelfMenuInfo() throws WxErrorException {
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getCurrentSelfMenuInfoUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.GET.toString());
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }


    /**
     * 初始化菜单
     *
     * @return
     */
    public String initMenu() {
        //创建点击一级菜单
        ViewButton button11 = new ViewButton();
        button11.setName("个人中心");
        button11.setType("view");
        String url = domain + "/authorize";
//        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
//        System.out.println(redirectUrl);
//        button11.setUrl(redirectUrl);
        button11.setUrl(url);

        //创建跳转型一级菜单
        ViewButton button21 = new ViewButton();
        button21.setName("百度一下");
        button21.setType("view");
        button21.setUrl("https://www.baidu.com");

        //创建其他类型的菜单与click型用法一致
        ClickButton button31 = new ClickButton();
        button31.setName("拍照发图");
        button31.setType("pic_photo_or_album");
        button31.setKey("31");

        ClickButton button32 = new ClickButton();
        button32.setName("发送位置");
        button32.setKey("32");
        button32.setType("location_select");

        //封装到一级菜单
        Button button = new Button();
        button.setName("菜单");
        button.setType("click");
        button.setSub_button(new Button[]{button31, button32});

        //封装菜单
        Menu menu = new Menu();
        menu.setButton(new Button[]{button21, button, button11});
//        Matchrule matchrule = new Matchrule();
//        matchrule.setLanguage(3);
//        menu.setMatchrule(matchrule);
//        return JSONObject.fromObject(menu).toString();
        return JSONObject.toJSONString(menu);
    }

}

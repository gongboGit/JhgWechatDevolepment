package com.jhg.marketing.web.util;

import com.alibaba.fastjson.JSONObject;
import com.jhg.marketing.web.util.exception.WxError;
import com.jhg.marketing.web.util.exception.WxErrorException;
import org.springframework.http.HttpMethod;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信 API、微信基本接口
 */

public class WeChatUtil {

    // token 接口
    public static final String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    // 创建菜单
    public static final String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";

    //获取菜单
    public static final String MENU_GET = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=%s";

    // 删除菜单
    public static final String MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s";

    // 创建个性化菜单
    public static final String MENU_ADDCONDITIONAL = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=%s";

    // 删除个性化菜单
    public static final String MENU_DELCONDITIONAL = "https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=%s";

    //测试个性化菜单匹配结果
    public static final String TRY_MATCH = "https://api.weixin.qq.com/cgi-bin/menu/trymatch?access_token=%s";

    //获取自定义菜单配置接口
    public static final String GET_CURRENT_SELFMENU_INFO = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=%s";

    // 获取账号粉丝信息
    public static final String GET_FANS_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";

    // 批量获取用户基本信息
    public static final String BATCH_GET_FANS_INFO = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=%s";

    // 获取账号粉丝列表
    public static final String GET_FANS_LIST = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s";

    // 获取素材列表
    public static final String GET_BATCH_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=%s";

    // 上传多媒体资料接口-临时
    public static final String UPLOAD_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=%s";

    //获取临时素材  GET
    public static final String GET_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";

    // 上传永久素材：图文-临时
    public static final String UPLOAD_NEWS = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=%s";

    // 网页授权OAuth2.0获取code
    public static final String GET_OAUTH_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=%s&scope=%s&state=%s#wechat_redirect";

    // 网页授权OAuth2.0获取token
    public static final String GET_OAUTH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    // 网页授权OAuth2.0获取用户信息
    public static final String GET_OAUTH_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

    // 生成二维码
    public static final String CREATE_QRCODE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s";

    // 根据ticket获取二维码图片
    public static final String SHOW_QRCODE = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%s";

    // js ticket
    public static final String GET_JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";

    // 发送客服消息
    public static final String SEND_CUSTOM_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s";

    //设置所属行业
    public static final String SET_INDUSTRY = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=%s";

    //获取所属行业
    public static final String GET_INDUSTRY = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=%s";

    //获得模板ID
    public static final String ADD_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=%s";

    //获取模板列表
    public static final String GET_ALL_PRIVATE_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=%s";

    //删除模板
    public static final String DEL_PRIVATE_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=%s";

    // 模板消息接口
    public static final String SEND_TEMPLATE_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";

    // 微信服务器ip
    public static final String CALLBACKIP = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=%s";

    // 统一下单订购接口
    public static final String PAY_UNIFIEDORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    // 统一下单订购沙盒接口
    public static final String SAND_BOX_NEW_PAY_UNIFIEDORDER = "https://api.mch.weixin.qq.com/sandboxnew/pay/unifiedorder";

    // 沙盒API密钥接口
    public static final String SAND_BO_NEW_SIGN_KEY = "https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey";

    // 上传永久图文图片素材
    public static final String UPLOAD_MATERIAL_IMG = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=%s";

    // 新增其他类型永久素材
    public static final String ADD_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=%s&type=%s";

    // 新增永久图文素材
    public static final String ADD_NEWS_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=%s";

    // 根据media_id来获取永久素材
    public static final String GET_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=%s";

    // 根据media_id来删除永久图文素材
    public static final String DELETE_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=%s";

    // 获取素材总数
    public static final String GET_MATERIALCOUNT = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=%s";

    // 修改永久图文url
    public static final String UPDATE_NEWS_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=%s";

    //获取用户标签列表
    private static final String GET_USER_TAG = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=%s";    //获取用户标签列表

    private static final String UPDATE_USER_TAG = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token=%s";

    //创建用户标签
    private static final String CREATE_USER_TAG = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=%s";

    //获取标签下粉丝列表
    private static final String GET_USER_LIST_BY_TAG = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=%s";

    //删除用户标签
    private static final String DELETE_USER_TAG = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=%s";    //删除用户标签

    private static final String BATCH_TAGGING = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=%s";

    private static final String BATCH_UNTAGGING = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=%s";

    private static final String GET_USER_TAG_LIST = "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=%s";

    private static final String UPDATE_REMARK = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=%s";

    /**
     * ###群发相关接口
     */
    /**
     * 临时素材接口-上传视频-POST
     */
    public static final String UPLOAD_VIDEO = "https://api.weixin.qq.com/cgi-bin/media/uploadvideo?access_token=%s";

    /**
     * 根据标签进行群发-POST
     */
    public static final String MASS_TAG = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=%s";

    /**
     * 根据openid列表进行群发-POST
     */
    public static final String MASS_OPENID = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=%s";

    /**
     * 删除群发（图文和视频）-POST
     */
    public static final String MASS_DELETE = "https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=%s";

    /**
     * 群发预览-POST
     */
    public static final String MASS_PREVIEW = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=%s";

    /**
     * 查询群发消息发送状态-POST
     */
    public static final String MASS_STATUS = "https://api.weixin.qq.com/cgi-bin/message/mass/get?access_token=%s";

    /**
     * 获取群发速度-POST
     */
    public static final String MASS_SPEED_GET = "https://api.weixin.qq.com/cgi-bin/message/mass/speed/get?access_token=%s";

    /**
     * 设置群发速度-POST
     */
    public static final String MASS_SPEED_SET = "https://api.weixin.qq.com/cgi-bin/message/mass/speed/set?access_token=%s";

    /**
     * ###统计分析相关接口
     */
    /**
     * 获取用户增减数据-7d-POST
     */
    public static final String GET_USER_SUMMARY = "https://api.weixin.qq.com/datacube/getusersummary?access_token=%s";
    /**
     * 获取累计用户数据-7d-POST
     */
    public static final String GET_USER_CUMULATE = "https://api.weixin.qq.com/datacube/getusercumulate?access_token=%s";

    /**
     * 获取图文群发每日数据-1d-POST
     */
    public static final String GET_ARTICLE_SUMMARY = "https://api.weixin.qq.com/datacube/getarticlesummary?access_token=%s";
    /**
     * 获取图文群发总数据-1d-POST
     */
    public static final String GET_ARTICLE_TOTAL = "https://api.weixin.qq.com/datacube/getarticletotal?access_token=%s";
    /**
     * 获取图文统计数据-3d-POST
     */
    public static final String GET_ARTICLE_READ = "https://api.weixin.qq.com/datacube/getuserread?access_token=%s";
    /**
     * 获取图文统计分时数据-1d-POST
     */
    public static final String GET_ARTICLE_READHOUR = "https://api.weixin.qq.com/datacube/getuserreadhour?access_token=%s";
    /**
     * 获取图文分享转发数据-7d-POST
     */
    public static final String GET_ARTICLE_SHARE = "https://api.weixin.qq.com/datacube/getusershare?access_token=%s";
    /**
     * 获取图文分享转发分时数据-1d-POST
     */
    public static final String GET_ARTICLE_SHAREHOUR = "https://api.weixin.qq.com/datacube/getusersharehour?access_token=%s";

    /**
     * 获取消息发送概况数据-7d-POST
     */
    public static final String GET_UPSTREAM_MSG = "https://api.weixin.qq.com/datacube/getupstreammsg?access_token=%s";
    /**
     * 获取消息分送分时数据-1d-POST
     */
    public static final String GET_UPSTREAM_MSGHOUR = "https://api.weixin.qq.com/datacube/getupstreammsghour?access_token=%s";
    /**
     * 获取消息发送周数据-30d-POST
     */
    public static final String GET_UPSTREAM_MSGWEEK = "https://api.weixin.qq.com/datacube/getupstreammsgweek?access_token=%s";
    /**
     * 获取消息发送月数据-30d-POST
     */
    public static final String GET_UPSTREAM_MSGMONTH = "https://api.weixin.qq.com/datacube/getupstreammsgmonth?access_token=%s";
    /**
     * 获取消息发送分布数据-15d-POST
     */
    public static final String GET_UPSTREAM_MSGDIST = "https://api.weixin.qq.com/datacube/getupstreammsgdist?access_token=%s";
    /**
     * 获取消息发送分布周数据-30d-POST
     */
    public static final String GET_UPSTREAM_MSGDISTWEEK = "https://api.weixin.qq.com/datacube/getupstreammsgdistweek?access_token=%s";
    /**
     * 获取消息发送分布月数据-30d-POST
     */
    public static final String GET_UPSTREAM_MSGDISTMONTH = "https://api.weixin.qq.com/datacube/getupstreammsgdistmonth?access_token=%s";

    /**
     * 获取接口分析数据-30d-POST
     */
    public static final String GET_INTERFACE_SUMMARY = "https://api.weixin.qq.com/datacube/getinterfacesummary?access_token=%s";
    /**
     * 获取接口分析分时数据-1d-POST
     */
    public static final String GET_INTERFACE_SUMMARYHOUR = "https://api.weixin.qq.com/datacube/getinterfacesummaryhour?access_token=%s";


    //素材文件后缀
    public static Map<String, String> type_fix = new HashMap<>();
    public static Map<String, String> media_fix = new HashMap<>();
    //素材文件大小
    public static Map<String, Long> type_length = new HashMap<>();
    //统计图表数据
    public static Map<String, String[]> data_cube = new HashMap<>();

    static {
        type_fix.put("image", "bmp|png|jpeg|jpg|gif");
        type_fix.put("voice", "mp3|wma|wav|amr");
        type_fix.put("video", "mp4");
        type_fix.put("thumb", "jpg");

        media_fix.put("image", "png|jpeg|jpg|gif");
        media_fix.put("voice", "mp3|amr");
        media_fix.put("video", "mp4");
        media_fix.put("thumb", "jpg");

        type_length.put("image", new Long(2 * 1024 * 1024));
        type_length.put("voice", new Long(2 * 1024 * 1024));
        type_length.put("video", new Long(10 * 1024 * 1024));
        type_length.put("thumb", new Long(64 * 1024));

        data_cube.put("getusersummary", new String[]{"7", GET_USER_SUMMARY});
        data_cube.put("getusercumulate", new String[]{"7", GET_USER_CUMULATE});
        data_cube.put("getarticlesummary", new String[]{"1", GET_ARTICLE_SUMMARY});
        data_cube.put("getarticletotal", new String[]{"1", GET_ARTICLE_TOTAL});
        data_cube.put("getuserread", new String[]{"3", GET_ARTICLE_READ});
        data_cube.put("getuserreadhour", new String[]{"1", GET_ARTICLE_READHOUR});
        data_cube.put("getusershare", new String[]{"7", GET_ARTICLE_SHARE});
        data_cube.put("getusersharehour", new String[]{"1", GET_ARTICLE_SHAREHOUR});
    }

    // 获取token接口
    public static String getTokenUrl(String appId, String appSecret) {
        return String.format(ACCESS_TOKEN, appId, appSecret);
    }

    // 获取上传临时素材接口
    public static String getUploadMediaUrl(String token, String type) {
        return String.format(UPLOAD_MEDIA, token, type);
    }

    // 获取临时素材接口
    public static String getTemporaryMediaMaterialUrl(String token, String type) {
        return String.format(GET_MEDIA, token, type);
    }

    // 获取菜单创建接口
    public static String getMenuCreateUrl(String token) {
        return String.format(MENU_CREATE, token);
    }

    // 获取菜单查询接口
    public static String getMenuGetUrl(String token) {
        return String.format(MENU_GET, token);
    }

    // 获取个性化菜单创建接口
    public static String getMenuAddConditionalUrl(String token) {
        return String.format(MENU_ADDCONDITIONAL, token);
    }

    // 获取个性化菜单删除接口
    public static String getMenuDelConditionalUrl(String token) {
        return String.format(MENU_DELCONDITIONAL, token);
    }

    // 获取个性化菜单删除接口
    public static String getTryMatchUrl(String token) {
        return String.format(TRY_MATCH, token);
    }

    // 获取个性化菜单删除接口
    public static String getCurrentSelfMenuInfoUrl(String token) {
        return String.format(GET_CURRENT_SELFMENU_INFO, token);
    }

    // 获取菜单删除接口
    public static String getMenuDeleteUrl(String token) {
        return String.format(MENU_DELETE, token);
    }

    // 获取粉丝信息接口
    public static String getFansInfoUrl(String token, String openid) {
        return String.format(GET_FANS_INFO, token, openid);
    }

    // 批量获取粉丝信息接口
    public static String getBatchGetFansInfoUrl(String token) {
        return String.format(BATCH_GET_FANS_INFO, token);
    }

    // 获取粉丝列表接口
    public static String getFansListUrl(String token, String nextOpenId) {
        if (nextOpenId == null) {
            return String.format(GET_FANS_LIST, token);
        } else {
            return String.format(GET_FANS_LIST + "&next_openid=%s", token, nextOpenId);
        }
    }

    // 获取素材列表接口
    public static String getBatchMaterialUrl(String token) {
        return String.format(GET_BATCH_MATERIAL, token);
    }

    // 获取上传图文消息接口
    public static String getUploadNewsUrl(String token) {
        return String.format(UPLOAD_NEWS, token);
    }

    //获取用户标签列表接口
    public static String getUserTagList(String token) {
        return String.format(GET_USER_TAG, token);
    }

    //获取用户标签列表接口
    public static String getUpdateUserTagUrl(String token) {
        return String.format(UPDATE_USER_TAG, token);
    }

    //获取创建用户标签接口
    public static String getCreateUserTag(String token) {
        return String.format(CREATE_USER_TAG, token);
    }

    //获取标签下粉丝列表
    public static String getUserListByTag(String token) {
        return String.format(GET_USER_LIST_BY_TAG, token);
    }

    //获取删除用户标签接口
    public static String getDeleteUserTag(String token) {
        return String.format(DELETE_USER_TAG, token);
    }

    //获取批量为用户打标签接口
    public static String getBatchTaggingUrl(String token) {
        return String.format(BATCH_TAGGING, token);
    }

    //获取批量为用户取消标签接口
    public static String getBatchUnTaggingUrl(String token) {
        return String.format(BATCH_UNTAGGING, token);
    }

    //获取批量为用户取消标签接口
    public static String getUserTagListUrl(String token) {
        return String.format(GET_USER_TAG_LIST, token);
    }

    //获取批量为用户取消标签接口
    public static String getUpdateRemarkUrl(String token) {
        return String.format(UPDATE_REMARK, token);
    }

    // 根据OpenID进行群发接口
    public static String getMassSendByOpenIdUrl(String token) {
        return String.format(MASS_OPENID, token);
    }

    // 根据标签进行群发接口
    public static String getMassSendByTagUrl(String token) {
        return String.format(MASS_TAG, token);
    }

    // 获取群发删除url
    public static String getMassSendDeleteUrl(String token) {
        return String.format(MASS_DELETE, token);
    }

    // 获取群发预览url
    public static String getMassSendPreviewUrl(String token) {
        return String.format(MASS_PREVIEW, token);
    }

    // 获取群发预览url
    public static String getMassSendStatusUrl(String token) {
        return String.format(MASS_STATUS, token);
    }

    // 群发的视频上传接口
    public static String getMassSendUploadVideoUrl(String token) {
        return String.format(UPLOAD_VIDEO, token);
    }

    // 网页授权OAuth2.0获取code
    public static String getOAuthCodeUrl(String appId, String redirectUrl, String scope, String state) {
        return String.format(GET_OAUTH_CODE, appId, urlEnodeUTF8(redirectUrl), "code", scope, state);
    }

    // 网页授权OAuth2.0获取token
    public static String getOAuthTokenUrl(String appId, String appSecret, String code) {
        return String.format(GET_OAUTH_TOKEN, appId, appSecret, code);
    }

    // 网页授权OAuth2.0获取用户信息
    public static String getOAuthUserinfoUrl(String token, String openid) {
        return String.format(GET_OAUTH_USERINFO, token, openid);
    }

    // 获取创建二维码接口url
    public static String getCreateQrcodeUrl(String token) {
        return String.format(CREATE_QRCODE, token);
    }

    // 获取显示二维码接口
    public static String getShowQrcodeUrl(String ticket) {
        return String.format(SHOW_QRCODE, ticket);
    }

    // 获取js ticket url
    public static String getJsApiTicketUrl(String token) {
        return String.format(GET_JSAPI_TICKET, token);
    }

    // 获取发送客服消息 url
    public static String getSendCustomMessageUrl(String token) {
        return String.format(SEND_CUSTOM_MESSAGE, token);
    }

    // 获取设置所属行业 url
    public static String getSetIndustryUrl(String token) {
        return String.format(SET_INDUSTRY, token);
    }

    // 获取设置的所属行业 url
    public static String getIndustryUrl(String token) {
        return String.format(GET_INDUSTRY, token);
    }

    // 获得模板ID
    public static String getAddTemplateUrl(String token) {
        return String.format(ADD_TEMPLATE, token);
    }

    // 获得模板列表 url
    public static String getAllPrivateTemplateUrl(String token) {
        return String.format(GET_ALL_PRIVATE_TEMPLATE, token);
    }

    // 获得删除模板 url
    public static String getDelPrivateTemplateUrl(String token) {
        return String.format(DEL_PRIVATE_TEMPLATE, token);
    }

    // 获取发送模板消息 url
    public static String getSendTemplateMessageUrl(String token) {
        return String.format(SEND_TEMPLATE_MESSAGE, token);
    }

    // 获取永久素材
    public static String getMaterialUrl(String token) {
        return String.format(GET_MATERIAL, token);
    }

    // 删除永久图文素材
    public static String getDelMaterialUrl(String token) {
        return String.format(DELETE_MATERIAL, token);
    }

    //获取素材总数url
    public static String getMaterialCountUrl(String token) {
        return String.format(GET_MATERIALCOUNT, token);
    }

    // 获取统一下单接口地址
    public static String getUnifiedOrderUrl() {
        return String.format(PAY_UNIFIEDORDER);
    }

    // 获取统一下单沙盒接口地址
    public static String getSandBoxNewPayUnifiedOrderUrl() {
        return String.format(SAND_BOX_NEW_PAY_UNIFIEDORDER);
    }

    // 获取沙盒API密钥接口地址
    public static String getSandBoxNewSignKey() {
        return String.format(SAND_BO_NEW_SIGN_KEY);
    }

    // 获取微信服务器ip地址（验证码token是否过期）
    public static String getCallbackIpUrl(String token) {
        return String.format(CALLBACKIP, token);
    }

    // 获取新增图文素材url
    public static String getNewsMaterialUrl(String token) {
        return String.format(ADD_NEWS_MATERIAL, token);
    }

    // 获取修改图文素材url
    public static String getUpdateNewsMaterialUrl(String token) {
        return String.format(UPDATE_NEWS_MATERIAL, token);
    }

    // 上传永久图文图片素材
    public static String getMaterialImgUrl(String token) {
        return String.format(UPLOAD_MATERIAL_IMG, token);
    }

    // 获取新增其他类型永久素材url
    public static String getAddMaterialUrl(String token, String type) {
        return String.format(ADD_MATERIAL, token, type);
    }


    /**
     * 获取创建临时二维码post data
     *
     * @param expireSecodes 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     * @param scene         临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000)
     * @return
     */
    public static String getQrcodeJson(Integer expireSecodes, Integer scene) {
        String postStr = "{\"expire_seconds\":%d,\"action_name\":\"QR_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":%d}}";
        return String.format(postStr, expireSecodes, scene);
    }

    /**
     * 获取创建临时二维码post data
     *
     * @param scene 临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000)
     * @return
     */
    public static String getQrcodeLimitJson(Integer scene) {
        String postStr = "{\"action_name\":\"QR_LIMIT_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":%d}}";
        return String.format(postStr, scene);
    }

    // 获取永久二维码
    public static String getQrcodeLimitJson(String sceneStr) {
        String postStr = "{\"action_name\":\"QR_LIMIT_STR_SCENE\",\"action_info\":{\"scene\":{\"scene_str\":%s}}";
        return String.format(postStr, sceneStr);
    }

    // 获取微信服务器ip
    public static boolean getCallbackIp(String token) {
        String tockenUrl = WeChatUtil.getCallbackIpUrl(token);
        JSONObject jsonObject = httpsRequest(tockenUrl, HttpMethod.GET.toString(), null);
        if (null != jsonObject && !jsonObject.containsKey("errcode")) {
            return true;
        }
        return false;
    }

    // 获取js ticket
//    public static JSTicket getJSTicket(String token) throws WxErrorException{
//        JSTicket jsTicket = null;
//        String jsTicketUrl = WxApi.getJsApiTicketUrl(token);
//        JSONObject jsonObject = httpsRequest(jsTicketUrl, HttpMethod.GET, null);
//        if (null != jsonObject && jsonObject.containsKey("errcode") && jsonObject.getIntValue("errcode") == 0) {
//            try {
//                jsTicket = new JSTicket();
//                jsTicket.setTicket(jsonObject.getString("ticket"));
//                jsTicket.setExpiresIn(jsonObject.getIntValue("expires_in"));
//            } catch (JSONException e) {
//                jsTicket = null;// 获取token失败
//            }
//        } else if (null != jsonObject) {
//            throw new WxErrorException(WxError.fromJson(jsonObject));
//        }
//        return jsTicket;
//    }

    // 获取OAuth2.0 Token
//    public static OAuthAccessToken getOAuthAccessToken(String appId, String appSecret, String code) throws WxErrorException{
//        OAuthAccessToken token = null;
//        String tockenUrl = getOAuthTokenUrl(appId, appSecret, code);
//        JSONObject jsonObject = httpsRequest(tockenUrl, HttpMethod.GET, null);
//        if (null != jsonObject && !jsonObject.containsKey("errcode")) {
//            try {
//                token = new OAuthAccessToken();
//                token.setAccessToken(jsonObject.getString("access_token"));
//                token.setExpiresIn(jsonObject.getIntValue("expires_in"));
//                token.setOpenid(jsonObject.getString("openid"));
//                token.setScope(jsonObject.getString("scope"));
//            } catch (JSONException e) {
//                token = null;// 获取token失败
//            }
//        } else if (null != jsonObject) {
//            throw new WxErrorException(WxError.fromJson(jsonObject));
//        }
//        return token;
//    }

    /**
     * 上传多媒体文件
     * 返回media_id
     */
    public static String uploadMedia(String accessToken, String mediaType, String mediaUri) {
        String uploadMediaUrl = String.format(UPLOAD_MEDIA, accessToken, mediaType);
        // 设置边界
        String boundary = "----------" + System.currentTimeMillis();
        try {
            URL uploadUrl = new URL(uploadMediaUrl);
            HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl.openConnection();
            uploadConn.setDoOutput(true);
            uploadConn.setDoInput(true);
            uploadConn.setRequestMethod("POST");
            // 设置请求头Content-Type
            uploadConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            // 获取媒体文件上传的输出流（往微信服务器写数据）
            OutputStream outputStream = uploadConn.getOutputStream();

            URL mediaUrl = new URL(mediaUri);
            HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl.openConnection();
            meidaConn.setDoOutput(true);
            meidaConn.setRequestMethod("GET");

            // 从请求头中获取内容类型
            String contentType = meidaConn.getHeaderField("Content-Type");
            // 根据内容类型判断文件扩展名
            String fileExt = ".jpg";
            // 请求体开始
            outputStream.write(("--" + boundary + "\r\n").getBytes());
            outputStream.write(
                    String.format("Content-Disposition: form-data; name=\"media\"; filename=\"file1%s\"\r\n", fileExt)
                            .getBytes());
            outputStream.write(String.format("Content-Type: %s\r\n\r\n", contentType).getBytes());

            // 获取媒体文件的输入流（读取文件）
            BufferedInputStream bis = new BufferedInputStream(meidaConn.getInputStream());
            byte[] buf = new byte[8096];
            int size = 0;
            while ((size = bis.read(buf)) != -1) {
                outputStream.write(buf, 0, size);
            }
            // 请求体结束
            outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
            outputStream.close();
            bis.close();
            meidaConn.disconnect();

            // 获取媒体文件上传的输入流（从微信服务器读数据）
            InputStream inputStream = uploadConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer buffer = new StringBuffer();
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            uploadConn.disconnect();
            // 使用JSON-lib解析返回结果
            JSONObject jsonObject = JSONObject.parseObject(buffer.toString());
            if (jsonObject.containsKey("media_id")) {
                return jsonObject.getString("media_id");
            }
            return null;
        } catch (Exception e) {
            String error = String.format("上传媒体文件失败：%s", e);
        }
        return null;
    }

    /**
     * 获取数据的起始日期，start和end的差值需小于“最大时间跨度”
     * （比如最大时间跨度为1时，start和end的差值只能为0，才能小于1），否则会报错
     *
     * @param accessToken
     * @param dcu         统计方法选择 (例如：getusersummary)
     * @param start       开始时间
     * @param end         结束时间
     * @return
     * @throws WxErrorException
     */

    public static JSONObject forDataCube(String accessToken, String dcu, String start, String end) throws WxErrorException {

        String[] cube = data_cube.get(dcu);
        if (cube == null) {
            throw new WxErrorException(WxError.newBuilder().setErrorCode(-7).setErrorMsg("无法获取统计方法").build());
        }
        String url = String.format(cube[1], accessToken);
        int days = 0;
        try {
            days = DateUtil.dayDiff(start, end);
        } catch (ParseException e) {
            throw new WxErrorException(WxError.newBuilder().setErrorCode(-4).setErrorMsg("时间转化出错").build());
        }
        //最大时间跨度
        if (days < 0 || days >= Integer.parseInt(cube[0])) {
            throw new WxErrorException(WxError.newBuilder().setErrorCode(9001031).setErrorMsg("时间区间不合法").build());
        }
        JSONObject json = new JSONObject();
        json.put("begin_date", start);
        json.put("end_date", end);
        String result = HttpClientUtils.sendHttpPost(url, json.toJSONString());
        WxError wxError = WxError.fromJson(result);
        if (wxError.getErrorCode() != 0) {
            throw new WxErrorException(wxError);
        }

        return JSONObject.parseObject(result);
    }

    /**
     * 用户增减数据
     * {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141082}
     *
     * @param accessToken 微信token
     * @param start       开始时间
     * @param end         结束时间
     * @return {
     * "list": [
     * {
     * "ref_date": "2014-12-07", //数据的日期
     * "user_source": 0, //用户的渠道，数值代表的含义如下： 0代表其他合计 1代表公众号搜索 17代表名片分享 30代表扫描二维码 43代表图文页右上角菜单 51代表支付后关注（在支付完成页） 57代表图文页内公众号名称 75代表公众号文章广告 78代表朋友圈广告
     * "new_user": 0, //新增的用户数量
     * "cancel_user": 0 //取消关注的用户数量，new_user减去cancel_user即为净增用户数量
     * }//后续还有ref_date在begin_date和end_date之间的数据
     * ]
     * }
     * @throws WxErrorException
     */
    public static JSONObject getUserSummary(String accessToken, String start, String end) throws WxErrorException {

        return forDataCube(accessToken, "getusersummary", start, end);
    }

    /**
     * 获取累计用户数据
     * {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141082}
     *
     * @param accessToken 微信token
     * @param start       开始时间
     * @param end         结束时间
     * @return {
     * "list": [
     * {
     * "ref_date": "2014-12-07", //数据的日期
     * "cumulate_user": 1217056 //总用户量
     * }, //后续还有ref_date在begin_date和end_date之间的数据
     * ]
     * }
     * @throws WxErrorException
     */
    public static JSONObject getUserCumulate(String accessToken, String start, String end) throws WxErrorException {

        return forDataCube(accessToken, "getusercumulate", start, end);
    }

    /**
     * 获取图文群发每日数据
     * {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084}
     *
     * @param accessToken
     * @param start
     * @param end
     * @return
     * @throws WxErrorException
     */
    public static JSONObject getArticleSummary(String accessToken, String start, String end) throws WxErrorException {

        return forDataCube(accessToken, "getarticlesummary", start, end);
    }

    /**
     * 获取图文群发总数据
     * {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084}
     *
     * @param accessToken
     * @param start
     * @param end
     * @return
     * @throws WxErrorException
     */
    public static JSONObject getArticleTotal(String accessToken, String start, String end) throws WxErrorException {

        return forDataCube(accessToken, "getarticletotal", start, end);
    }

    /**
     * 获取图文统计数据
     * {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084}
     *
     * @param accessToken
     * @param start
     * @param end
     * @return
     * @throws WxErrorException
     */
    public static JSONObject getUserRead(String accessToken, String start, String end) throws WxErrorException {

        return forDataCube(accessToken, "getuserread", start, end);
    }

    /**
     * 获取图文统计分时数据
     * {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084}
     *
     * @param accessToken
     * @param start
     * @param end
     * @return
     * @throws WxErrorException
     */
    public static JSONObject getUserReadHour(String accessToken, String start, String end) throws WxErrorException {

        return forDataCube(accessToken, "getuserreadhour", start, end);
    }

    /**
     * 获取图文分享转发数据
     * {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084}
     *
     * @param accessToken
     * @param start
     * @param end
     * @return
     * @throws WxErrorException
     */
    public static JSONObject getUserShare(String accessToken, String start, String end) throws WxErrorException {

        return forDataCube(accessToken, "getusershare", start, end);
    }

    /**
     * 获取图文分享转发分时数据
     * {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084}
     *
     * @param accessToken
     * @param start
     * @param end
     * @return
     * @throws WxErrorException
     */
    public static JSONObject getUserShareHour(String accessToken, String start, String end) throws WxErrorException {

        return forDataCube(accessToken, "getusersharehour", start, end);
    }

    // 发送请求
    public static JSONObject httpsRequest(String requestUrl, String requestMethod) {
        return httpsRequest(requestUrl, requestMethod, null);
    }

    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            TrustManager[] tm = {new MyTrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(requestMethod);
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = JSONObject.parseObject(buffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static String httpsRequestByXml(String requestUrl, String requestMethod, String outputStr) {
        String retStrXml = "";
        try {
            TrustManager[] tm = {new MyTrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(requestMethod);
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            conn.disconnect();
            retStrXml = buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retStrXml;
    }

    public static byte[] httpsRequestByte(String requestUrl, String requestMethod) {
        return httpsRequestByte(requestUrl, requestMethod, null);
    }

    public static byte[] httpsRequestByte(String requestUrl, String requestMethod, String outputStr) {
        try {
            TrustManager[] tm = {new MyTrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(requestMethod);
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            InputStream inputStream = conn.getInputStream();
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int n = 0;
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);
            }
            return output.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String urlEnodeUTF8(String str) {
        String result = str;
        try {
            result = URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 是否微信浏览器
     *
     * @param request
     * @return
     */
    public static boolean isWeChat(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent").toLowerCase();
        if (ua.indexOf("micromessenger") > -1) {
            return true;//微信
        }
        return false;//非微信手机浏览器

    }
}

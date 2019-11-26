package com.jhg.marketing.web.util;

import com.jhg.marketing.dao.domin.SysUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 应用工具类
 *
 * @author lxl
 * @since 2018/11/11 12:28
 */
public class ApplicationUtil {

    public ApplicationUtil() {
        throw new RuntimeException();
    }

    /**
     * 获取Session中的登录用户
     *
     * @return
     */
    public static SysUser getSessionUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        return (SysUser) session.getAttribute("user");
    }

    /**
     * 获取登录用户的openId
     *
     * @return
     */
    public static String getSessionOpenId() {
        SysUser sessionUser = getSessionUser();
        return sessionUser.getOpenId();
    }

    /**
     * 获取Session中的登录用户ID
     *
     * @return
     */
    public static Integer getSessionUserId() {
        return getSessionUser().getId();
    }

    /**
     * 获取Session中的登录用户
     *
     * @return
     */
    public static String getWebRealPath() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getSession().getServletContext().getRealPath("");
    }

    /**
     * helloWorld -> hello_world
     *
     * @param underscoreName
     * @return
     */
    public static String mapCamelCaseToUnderscore(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.length() > 0) {
            result.append(underscoreName.substring(0, 1).toLowerCase());
            for (int i = 1; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if (Character.isUpperCase(ch)) {
                    result.append("_");
                    result.append(Character.toLowerCase(ch));
                } else {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }


    /**
     * hello_world -> helloWorld
     *
     * @param camelCase
     * @return
     */
    public static String mapUnderscoreToCamelCase(String camelCase) {
        StringBuilder result = new StringBuilder();
        if (camelCase != null && camelCase.length() > 0) {
            boolean flag = false;
            for (int i = 0; i < camelCase.length(); i++) {
                char ch = camelCase.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }

}

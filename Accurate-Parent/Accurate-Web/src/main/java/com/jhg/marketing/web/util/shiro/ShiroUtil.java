package com.jhg.marketing.web.util.shiro;


import com.jhg.marketing.dao.domin.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

public class ShiroUtil {

    private ShiroUtil() {
        throw new RuntimeException();
    }

    /**
     * 加盐参数
     */
    public final static String HASH_ALGORITHM_NAME = "MD5";

    /**
     * 循环次数
     */
    public final static int HASH_ITERATIONS = 10;

    /**
     * Shiro密码加密工具类
     *
     * @param credentials 密码
     * @param saltSource  密码盐
     * @return
     */
    public static String md5(String credentials, String saltSource) {
        ByteSource salt = ByteSource.Util.bytes(saltSource);
        return new SimpleHash(HASH_ALGORITHM_NAME, credentials, salt, HASH_ITERATIONS).toString();
    }

    /**
     * 获取当前 Subject
     *
     * @return Subject
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取封装的 User
     *
     * @return User
     */
    public static SysUser getUser() {
        return (SysUser) getSubject().getPrincipals().getPrimaryPrincipal();
    }

    /**
     * 从Shiro获取session
     *
     * @return
     */
    public static Session getSession() {
        return getSubject().getSession();
    }

    /**
     * 获取Shiro指定的sessionKey
     *
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T getSessionAttr(String key) {
        Session session = getSession();
        return session != null ? (T) session.getAttribute(key) : null;
    }

    /**
     * 设置Shiro指定的sessionKey
     *
     * @param key
     * @param value
     */
    public static void setSessionAttr(String key, Object value) {
        Session session = getSession();
        session.setAttribute(key, value);
    }

    /**
     * 移除Shiro指定的sessionKey
     *
     * @param key
     */
    public static void removeSessionAttr(String key) {
        Session session = getSession();
        if (session != null) {
            session.removeAttribute(key);
        }
    }

}

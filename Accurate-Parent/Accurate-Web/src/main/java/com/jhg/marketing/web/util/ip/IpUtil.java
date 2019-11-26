package com.jhg.marketing.web.util.ip;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 取得本机IP地址 、取得服务器网络地址
 *
 * @author lxl
 * @since 2018/11/10 0:35
 */
public class IpUtil {

    private IpUtil() {
        throw new RuntimeException("不能实例化此对象！");
    }

    /**
     * 取得本机IP地址
     *
     * @return
     */
    public static String getLocalIP() {
        InetAddress localIP = null;
        try {
            localIP = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException("未知IP");
        }
        return localIP.getHostAddress();
    }

    /**
     * 取得服务器网络地址
     *
     * @param www
     * @return
     */
    public static String getServerIP(String www) {
        InetAddress serverIP = null;
        try {
            serverIP = InetAddress.getByName(www.trim());
            // 取得例如www.baidu.com的IP地址
        } catch (UnknownHostException e) {
            throw new RuntimeException("未知IP");
        }
        return serverIP.getHostAddress();
    }

    /**
     * 获得访问者IP
     */
    public static String getVisitorialIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.0";
        }
        if (ip.contains(",")) {
            return ip.split(",")[0];
        }
        return ip;
    }

    public static void main(String[] args) {
        System.out.println(getLocalIP());
        System.out.println(getServerIP("www.baidu.com"));
    }
}
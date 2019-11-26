package com.jhg.marketing.web.util;

import java.util.HashSet;
import java.util.Set;

/**
 * 字符串工具类
 */
public class StringUtil {

    private StringUtil() {
        throw new RuntimeException();
    }

    /**
     * 判断字符串为空
     *
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * 判断字符串不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 计算两个字符串的Jaccard相似度：https://blog.csdn.net/qq_37218708/article/details/80143874
     *
     * @param str1
     * @param str2
     * @return
     */
    public static float compare(String str1, String str2) {
        // set元素不可重复
        Set<Character> s1 = new HashSet<>();
        Set<Character> s2 = new HashSet<>();

        for (int i = 0; i < str1.length(); i++) {
            // 将string里面的元素一个一个按索引放进set集合
            s1.add(str1.charAt(i));
        }
        for (int j = 0; j < str2.length(); j++) {
            s2.add(str2.charAt(j));
        }

        // 并集元素个数
        float mergeNum = 0;
        // 相同元素个数（交集）
        float commonNum = 0;

        for (Character ch1 : s1) {
            for (Character ch2 : s2) {
                if (ch1.equals(ch2)) {
                    commonNum++;
                }
            }
        }

        mergeNum = s1.size() + s2.size() - commonNum;

        float jaccard = commonNum / mergeNum;

        return jaccard;
    }
}

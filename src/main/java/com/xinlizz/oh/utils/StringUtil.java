package com.xinlizz.oh.utils;

import org.springframework.util.StringUtils;

/**
 * StringUtils String工具类
 *
 * @Author: xinlizz
 * @Date: 2018/7/31
 */
public class StringUtil extends StringUtils {

    /**
     * 字符串是否相等 for example：
     * <li>StringUtil.equal(null, "a")  return false</li>
     * <li>StringUtil.equal("a", null)  return false</li>
     * <li>StringUtil.equal(null, null)  return false</li>
     * <li>StringUtil.equal("b", "a")  return false</li>
     * <li>StringUtil.equal("a", "a")  return true</li>
     *
     * @return boolean
     *
     * @author xinlizz
     * @Date 2018/7/31
     * @Param [str1, str2]
     */
    public static boolean equals(String str1, String str2) {
        if (null == str1 || null == str2) {
            return false;
        }
        return str1.equals(str2);
    }
}

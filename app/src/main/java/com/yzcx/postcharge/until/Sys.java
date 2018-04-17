package com.yzcx.postcharge.until;

/**
 * @author liuqingjie
 * @create $date$
 * @description $desc$
 */

public class Sys {
    public static boolean isNull(String str) {
        // System.out.println("!!!");
        if (null == str || str.length() <= 0 || str.equals("null")) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(String str) {
        // System.out.println("!!!");
        if (null == str || str.length() <= 0 || str.equals("null")) {
            return false;
        }
        return true;
    }

    public static String isCheckNull(String str) {
        // System.out.println("!!!");
        if (null == str || str.length() <= 0 || str.equals("null") || str.equals("NULL")) {
            return "";
        }
        return str;
    }
}

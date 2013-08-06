package com.sjtu.onlinelibrary.util;

import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-28
 * Time: 下午3:25
 */
public class LangUtil {
    public static boolean isNullOrEmpty(final String str) {
        return str == null || "".equals(str);
    }

    static final SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

    static final SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static SimpleDateFormat getDefaultDateFormat() {
        return dateformat;
    }
    
    public static SimpleDateFormat getDefaultTimeFormat() {
        return timeformat;
    }
}

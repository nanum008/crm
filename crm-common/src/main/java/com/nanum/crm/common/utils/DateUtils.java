package com.nanum.crm.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    // 将时间格式化输出成字符串，默认格式：yyyy-MM-dd HH:mm:ss
    public static String formatDateTime(Date date) {
        return DateUtils.formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    //传入格式和Date对象，输出指定格式的时间字符串。
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String str = simpleDateFormat.format(date);
        return str;
    }
}

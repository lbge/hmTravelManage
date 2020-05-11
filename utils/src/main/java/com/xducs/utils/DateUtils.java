package com.xducs.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换工具类
 * @author Linbo Ge
 * @date 2020/5/3 - 15:56
 */
public class DateUtils {
    /**
     * 字符串转换成日期
     * @param date
     * @param patt 约束格式
     * @return
     */
    public static String dateToString(Date date, String patt){
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        return sdf.format(date);
    }

    public static Date stringToDate(String str,String patt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        return sdf.parse(str);
    }
}

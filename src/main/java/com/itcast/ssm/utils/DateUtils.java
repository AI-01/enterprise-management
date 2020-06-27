package com.itcast.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Lin
 * @create 2020/3/23 - 21:36
 */
public class DateUtils {

    //日期转为字符串
    public static String dateToString(Date date, String pattern){
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String dateString = format.format(date);
        return dateString;
    }

    //字符串转为日期
    public static Date stringToDate(String string, String pattern) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = format.parse(string);
        return date;
    }
}

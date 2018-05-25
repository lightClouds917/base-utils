package com.java4all.date;

import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yunqing
 * Date 2018/3/19
 * Description:日期处理工具类
 * int类型转为日期
 * 从日期中获取年/月/日
 * 字符串转日期：2015-03-24,2015/03/24,2015年3月24日,2015.03.34
 * 日期转字符串 如：2017年7月1日
 * 日期转字符串 如：2017-7-1
 * 获取当前年
 * 获取当前月
 */
public class DateUtil {
    /**
     * int类型转为日期类
     * 一般用于解析Excel
     *
     * parse date by int
     * we use it always in parsing Excel
     * @param dateNum
     * @return
     */
    public static Date int2Date(Integer dateNum){
        Calendar calendar = new GregorianCalendar(1900,0,-1);
        Date dateRe = DateUtils.addDays(calendar.getTime(),dateNum);
        return dateRe;
    }

    /**
     * 从日期中获取年/月/日
     * get year or month or day from date which instance of Date
     * @param date
     * @param type
     * @return
     */
    public static Integer getYearOrMonthOrDay(Date date,String type){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if("year".equals(type)){
            return calendar.get(Calendar.YEAR);
        }else if("month".equals(type)){
            return calendar.get(Calendar.MONTH)+1;
        }else if("day".equals(type)){
            return calendar.get(Calendar.DAY_OF_MONTH);
        }
        return null;
    }

    /**
     * 字符串转日期：2015-03-24,2015/03/24,2015年3月24日,2015.03.34
     * parse String to Date
     * @param str
     * @return
     * @throws Exception
     */
    public static Date string2Date(String str) throws Exception{
        Date date = null;
        if(str.contains("/")){
            str = str.replace("/","-");
        }else if(str.contains(".")){
            str = str.replace(".","-");
        }else if(!str.contains("日") && str.contains("年") && str.contains("月")){
            str = str.replace("年","-");
            str = str.replace("月","");
        }else if(str.contains("日") && str.contains("年") && str.contains("月")){
            str = str.replace("年","-");
            str = str.replace("月","-");
            str = str.replace("日","");
        }
        try {
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            date = format1.parse(str);
        }catch (Exception ex){
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM");
            date = format2.parse(str);
        }
        return date;
    }

    /**
     * 日期转字符串 如：2017年7月1日
     * parse Date to String   eg：2017年7月1日
     * @param date
     * @return
     */
    public static String date2String1(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String str = calendar.get(Calendar.YEAR)+"年"+(calendar.get(Calendar.MONTH)+1)+"月"+calendar.get(Calendar.DAY_OF_MONTH)+"日";
        return str;
    }

    /**
     * 日期转字符串 如：2017-7-1
     * parse Date to String   eg：2017-7-1
     * @param date
     * @return
     */
    public static String date2String2(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String str = calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
        return str;
    }

    /**
     * 获取当前年
     * get now year eg:2018
     */
    public static Integer getNowYear(){
        Calendar cal = Calendar.getInstance();
        int nowYear = cal.get(Calendar.YEAR);
        return nowYear;
    }

    /**
     * 获取当前月
     * get now month  eg:8
     */
    public static Integer getNowMonth(){
        Calendar cal = Calendar.getInstance();
        int nowMonth = cal.get(Calendar.MONTH) + 1;
        return nowMonth;
    }

    /**
     * get last day start and today start time
     *
     * eg: today is 2018/4/3 ,but now, i would like to get last day start
     * like : 2018/4/2 00:00:00 and today start time 2018/4/3 00:00:00
     * @return
     */
    public static Map getLastDayStartAndTodayStart(){
        HashMap<String, Date> map = new HashMap<>();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            String s = format.format(calendar.getTime());

            Date dateStart = dateFormat.parse(s + " 00:00:00");
            calendar.add(Calendar.DAY_OF_MONTH,1);
            String s1 = format.format(calendar.getTime());
            Date endStart = dateFormat.parse(s1 + " 00:00:00");
            map.put("dateStart",dateStart);
            map.put("endStart",endStart);

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }

}

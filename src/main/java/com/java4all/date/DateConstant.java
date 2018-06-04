package com.java4all.date;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * Author: momo
 * Date: 2018/6/2
 * Description:日期常量类 静态获取
 * 常用日期字符串
 */
public class DateConstant{

    /**年-月*/
    public static final String NOW_yyyy_MM = LocalDate.now().toString().substring(0,7);
    public static final String LAST_yyyy_MM = LocalDate.now().minusYears(1L).toString().substring(0,7);
    public static final String NOW_yyyy_01 = LocalDate.now().with(TemporalAdjusters.firstDayOfYear()).toString().substring(0,7);
    public static final String NOW_yyyy_12 = LocalDate.now().with(TemporalAdjusters.lastDayOfYear()).toString().substring(0,7);
    public static final String LAST_yyyy_01 = LocalDate.now().minusYears(1L).with(TemporalAdjusters.firstDayOfYear()).toString().substring(0,7);
    public static final String LAST_yyyy_12 = LocalDate.now().minusYears(1L).with(TemporalAdjusters.lastDayOfYear()).toString().substring(0,7);

    /**年-月-日*/
    public static final String NOW_yyyy_MM_dd = LocalDate.now().toString();
    public static final String LAST_yyyy_MM_dd = LocalDate.now().minusYears(1L).toString();
    public static final String NOW_yyyy_01_01 = LocalDate.now().with(TemporalAdjusters.firstDayOfYear()).toString();
    public static final String NOW_yyyy_12_31 = LocalDate.now().with(TemporalAdjusters.lastDayOfYear()).toString();
    public static final String LAST_yyyy_01_01 = LocalDate.now().minusYears(1L).with(TemporalAdjusters.firstDayOfYear()).toString();
    public static final String LAST_yyyy_12_31 = LocalDate.now().minusYears(1L).with(TemporalAdjusters.lastDayOfYear()).toString();

}

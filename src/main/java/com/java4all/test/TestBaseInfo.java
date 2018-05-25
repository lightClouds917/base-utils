package com.java4all.test;

import com.java4all.baseWrapper.BaseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: momo
 * Date: 2018/5/24
 * Description:测试BaseInfo
 */
public class TestBaseInfo {

    private static final Logger logger = LoggerFactory.getLogger("");

    public static void main(String[] args){
        List<BaseInfo> list = new ArrayList();
        list.add(new BaseInfo("1001","电子产业",500,null,null,null));
        list.add(new BaseInfo("1002","食品行业",800,null,null,null));
        list.add(new BaseInfo("1003","石油行业",250,null,null,null));

        logger.info("计算百分比前："+list.toString());

        //计算百分比
        list = BaseInfo.calculatePercent(list,null);
        list = BaseInfo.calculatePercent(list,2000);
        logger.info("计算百分比前："+list.toString());

        //按照数量从高到底排序
        list = list.stream().sorted(Comparator.comparing(BaseInfo::getNum).reversed()).collect(Collectors.toList());
        logger.info("计算百分比前："+list.toString());
    }
}

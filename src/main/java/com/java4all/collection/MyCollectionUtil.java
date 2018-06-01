package com.java4all.collection;

import com.java4all.reflect.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Author: momo
 * Date: 2018/5/31
 * Description:集合工具类
 * 1.根据map的value值排序List<Map>
 * 2.根据对象属性值排序List<Object>
 */
public class MyCollectionUtil {
    private static final Logger logger = Logger.getLogger("");

    /**
     * 根据map的value值排序List<Map>
     * @param list map集合
     * @param sortKey 排序的键
     * @param orderType asc 升序，desc 逆序
     * @return
     */
    public static List<Map<String,Object>> mapSort(List<Map<String,Object>> list,String sortKey,String orderType){
        return (CollectionUtils.isEmpty(list) || StringUtils.isBlank(sortKey)) ? null :
                orderType.equals("asc") ? list.stream().sorted(Comparator.comparing(map -> map.get(sortKey).toString())).collect(Collectors.toList()) :
                list.stream().sorted((map1,map2)->map2.get(sortKey).toString().compareTo(map1.get(sortKey).toString())).collect(Collectors.toList());
    }

    /**
     * 根据对象属性值排序List<Object>
     * Object无getXx方法，此方法无法通用，替换掉实体类即可
     * 以下以User 的age 属性为例
     * @param list
     * @param orderType asc 升序，desc 逆序
     * @return
     */
    public static List<Object> listSort(List<User> list, String orderType){
        return CollectionUtils.isEmpty(list) ? null :
                orderType.equals("asc") ? list.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList()) :
                        list.stream().sorted(Comparator.comparing(User::getAge).reversed()).collect(Collectors.toList());

    }

}

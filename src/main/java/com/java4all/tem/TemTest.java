package com.java4all.tem;

import com.java4all.collection.MyCollectionUtil;
import com.java4all.reflect.User;
import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Author: momo
 * Date: 2018/5/31
 * Description:用作测试的临时代码存放区，无生产作用
 */
public class TemTest {
    private static final Logger logger = Logger.getLogger("");

    public static void main(String[] args){
        testMyCollectionUtil();
    }

    public static void testMyCollectionUtil(){
        List<Map<String,Object>> list  = new ArrayList<>();
        Map map1 = new HashedMap();
        map1.put("name","张三");
        map1.put("age",62);
        list.add(map1);

        Map map2 = new HashedMap();
        map2.put("name","李四");
        map2.put("age",42);
        list.add(map2);

        Map map3 = new HashedMap();
        map3.put("name","陈川");
        map3.put("age",15);
        list.add(map3);

        Map map4 = new HashedMap();
        map4.put("name","胡亮");
        map4.put("age",22.5);
        list.add(map4);

        Map map5 = new HashedMap();
        map5.put("name","刘宁");
        map5.put("age",32);
        list.add(map5);

        Map map6 = new HashedMap();
        map6.put("name","陈鑫");
        map6.put("age",87.2342);
        list.add(map6);

        List listRe1 = MyCollectionUtil.mapSort(list, "age","desc");
        List listRe2 = MyCollectionUtil.mapSort(list, "age","asc");
        List listRe3 = MyCollectionUtil.mapSort(list, "","asc");
        List listRe4 = MyCollectionUtil.mapSort(null, "age","asc");
        List listRe5 = MyCollectionUtil.mapSort(list, "age","");


        List<User> list2 = new ArrayList<>();
        list2.add(new User(1,"aa",75,"河南","河南","南阳",null,null));
        list2.add(new User(2,"bb",13,"河南","河南","南阳",null,null));
        list2.add(new User(3,"cc",33,"河南","河南","南阳",null,null));
        list2.add(new User(4,"ff",47,"河南","河南","南阳",null,null));
        list2.add(new User(5,"dd",18,"河南","河南","南阳",null,null));

        List<Object> list3 = MyCollectionUtil.listSort(list2, "asc");
        List<Object> list4 = MyCollectionUtil.listSort(list2, "desc");
        logger.info("测试结果");

    }
}

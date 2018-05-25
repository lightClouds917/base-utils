package com.java4all.json;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.LinkedMap;

import java.util.*;

/**
 * Author: momo
 * Date: 2018/5/7
 * Description:json转化工具类
 *
 * map转json
 * Object转json
 * json转map
 * json转List<T>
 * json转到对象
 * json转List<Map<String, Object>>
 * List<Map<String,Object>>转json
 * 将json字符串转为Map结构（复杂json，嵌套结构都可以）
 *
 */
public class JsonUtil {

    //public static void main(String[] args){
    //    Map<String,Object> map = new LinkedMap();
    //    map.put("title","国务院2号文件");
    //    map.put("attach","根据中华人民共和国");
    //    String s = mapToJson(map);
    //
    //    Map<String,Object> map2 = new LinkedMap();
    //    map2.put("title","省委三号文件");
    //    map2.put("attach","本次大会研究决定");
    //    System.out.println(map2);
    //    String s2 = mapToJson(map2);
    //
    //    List<Map<String,Object>> list = new LinkedList<>();
    //    list.add(map);
    //    list.add(map2);
    //    String s1 = mapListToJson(list);
    //    System.out.println(s1);
    //    List<Map<String, Object>> list1 = jsonToMapList(s1);
    //
    //    System.out.println(list1.get(0).get("title"));
    //    System.out.println(list1.get(0).get("attach"));
    //
    //
    //}


    /**
     * map转json
     * @param map        {title=国务院2号文件, attach=根据中华人民共和国}
     * @return  json     {"title":"国务院2号文件","attach":"根据中华人民共和国"}
     */
    public static String mapToJson(Map<String,Object> map){
        ObjectMapper obm = new ObjectMapper();
        String param = null;
        try {
            param = obm.writeValueAsString(map);
            return param;
        }catch (Exception ex){
            throw new RuntimeException("map转json出错",ex);
        }
    }

    /**
     * json转map
     * @param jsonStr   {"title":"国务院2号文件","attach":"根据中华人民共和国"}
     * @return  map     {title=国务院2号文件, attach=根据中华人民共和国}
     */
    public static Map<String,Object> jsonToMap(String jsonStr){
        try {
            Map<String,Object> map2= JSON.parseObject(jsonStr,LinkedMap.class);
            return map2;
        }catch (Exception ex){
            throw new RuntimeException("json转map出错",ex);
        }
    }

    /**
     * json转List<Map<String, Object>>
     * @param jsonStr
     * @return
     */
    public static List<Map<String,Object>> jsonToMapList(String jsonStr){
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            List<Object> list2 = JSON.parseArray(jsonStr);
            for(int i = 0,length = list2.size();i < length;i++){
                Map<String,Object> map = (Map<String,Object>)list2.get(i);
                list.add(map);
            }
            return list;
        }catch (Exception ex){
            throw new RuntimeException("json转List<Map<String,Object>>出错",ex);
        }
    }

    /**
     * List<Map<String,Object>>转json
     * @param mapList       [{title=国务院2号文件, attach=根据中华人民共和国}, {title=省委三号文件, attach=本次大会研究决定}]
     * @return String       [{"title":"国务院2号文件","attach":"根据中华人民共和国"}, {"title":"省委三号文件","attach":"本次大会研究决定"}]
     */
    public static String mapListToJson(List<Map<String,Object>> mapList){
        System.out.println(mapList.toString());
        List<Object> list = new ArrayList<>();
        ObjectMapper obm = new ObjectMapper();
        try {
            if(mapList != null && mapList.size() > 0){
                for (int i = 0,length = mapList.size();i < length;i++){
                    list.add(obm.writeValueAsString(mapList.get(i)));
                }
            }
            System.out.println(list.toString());
            return list.toString();
        }catch (Exception ex){
            throw new RuntimeException("List<Map<String,Object>>转json出错",ex);
        }
    }

    /**
     * 将json字符串转为Map结构
     * 如果json复杂，结果可能是map嵌套map
     * @param jsonStr 入参，json格式字符串
     * @return 返回一个map
     */
    public static Map<String, Object> json2Map(String jsonStr) {
        Map<String, Object> map = new HashMap<>();
        if(jsonStr != null && !"".equals(jsonStr)){
            //最外层解析
            JSONObject json = JSONObject.fromObject(jsonStr);
            for (Object k : json.keySet()) {
                Object v = json.get(k);
                //如果内层还是数组的话，继续解析
                if (v instanceof JSONArray) {
                    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                    Iterator<JSONObject> it = ((JSONArray) v).iterator();
                    while (it.hasNext()) {
                        JSONObject json2 = it.next();
                        list.add(json2Map(json2.toString()));
                    }
                    map.put(k.toString(), list);
                } else {
                    map.put(k.toString(), v);
                }
            }
            return map;
        }else{
            return null;
        }
    }

    /**
     * Object转json
     * @param object
     * @param prettyFormat
     * @return
     */
    public static final String toJSONString(Object object, boolean prettyFormat) {
        return JSON.toJSONString(object, prettyFormat);
    }

    /**
     * json转List<T>
     * @param jsonStr json字符串
     * @param clazz class名称
     * @param <T>
     * @return
     */
    public static <T> List<T> getList(String jsonStr, Class<T> clazz) {
        List<T> list = new ArrayList<T>();
        try {
            list = JSON.parseArray(jsonStr, clazz);
        } catch (Exception ex) {
            throw new RuntimeException("json字符串转List失败",ex);
        }
        return list;
    }

    /**
     * json转到对象
     * @param jsontext
     * @param clazz 如User.class
     * @param <T>
     * @return
     */
    public static final <T> T getObject(String jsontext, Class<T> clazz) {
        T t = null;
        try {
            t = JSON.parseObject(jsontext, clazz);
        } catch (Exception ex) {
            throw new RuntimeException("json字符串转换失败",ex);
        }
        return t;
    }


    //public static <T> T fromString(String jsonString, Class<T> c) {
    //    ObjectMapper om = new ObjectMapper();
    //    try {
    //        //当字段不匹配时，保证转化其他字段能够正常转化
    //        om.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    //        return om.readValue(jsonString, c);
    //    } catch(Exception e) {
    //        throw new RuntimeException("json转对象出错；"+e);
    //        return null;
    //    }
    //}
    //
    //public static <T> List<T> toList(String jsonString, Class<T> c) {
    //    ObjectMapper om = new ObjectMapper();
    //    TypeFactory typeFactory = TypeFactory.defaultInstance();
    //    try {
    //        //当字段不匹配时，保证转化其他字段能够正常转化
    //        om.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    //        return om.readValue(jsonString, typeFactory.constructCollectionType(List.class, c));
    //    } catch (Exception e) {
    //        throw new RuntimeException("json转list出错；"+e);
    //        return null;
    //    }
    //}
    //
    //public static <T> List<T> toMapList(String jsonString, Class<Map> c) {
    //    ObjectMapper om = new ObjectMapper();
    //    TypeFactory typeFactory = TypeFactory.defaultInstance();
    //    try {
    //        //当字段不匹配时，保证转化其他字段能够正常转化
    //        om.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    //        return om.readValue(jsonString, typeFactory.constructCollectionType(List.class, c));
    //    } catch (Exception e) {
    //        throw  new RuntimeException("json转list出错：",e);
    //        return null;
    //    }
    //}
}

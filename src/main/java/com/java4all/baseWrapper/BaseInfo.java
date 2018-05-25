package com.java4all.baseWrapper;

import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: momo
 * Date: 2018/5/24
 * Description:信息封装类，此类较为通用
 * 计算百分比
 * 升序
 * 降序
 */
public class BaseInfo implements Comparable<BaseInfo>{

    private String code;

    private String name;

    private Integer num;

    private String str;

    private String percent;

    private String type;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BaseInfo() {
    }

    public BaseInfo(String code, String name, Integer num, String str, String percent, String type) {
        this.code = code;
        this.name = name;
        this.num = num;
        this.str = str;
        this.percent = percent;
        this.type = type;
    }

    @Override
    public int compareTo(BaseInfo o) {
        return 0;
    }

    @Override
    public String toString() {
        return "BaseInfo{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", num=" + num +
                ", str='" + str + '\'' +
                ", percent='" + percent + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    /**
     * 计算百分比 将BaseInfo中的数量转换为百分比，可直接传入list批量转化
     * @param list
     * @param sum 分母
     * @return
     */
    public static List<BaseInfo> calculatePercent(List<BaseInfo> list,Integer sum){
        String percent = "0.00%";
        if(!CollectionUtils.isEmpty(list)){
            sum = sum != null ? sum : list.stream().mapToInt(BaseInfo::getNum).sum();
            for (int i = 0,length = list.size(); i < length; i++) {
                Integer num = list.get(i).getNum() == null ? 0 : list.get(i).getNum();
                percent = (sum == 0 || sum == null) ? "-" : new BigDecimal((double)num*100/sum).setScale(2,BigDecimal.ROUND_HALF_UP)+"%";
                list.get(i).setPercent(percent);
            }
        }
        return list;
    }

    /**
     * 按照num升序
     * @param list
     * @return
     */
    public static List<BaseInfo> sortASC(List<BaseInfo> list){
        return list == null ? null : list.stream().sorted(Comparator.comparing(BaseInfo::getNum)).collect(Collectors.toList());
    }

    /**
     * 按照num降序
     * @param list
     * @return
     */
    public static List<BaseInfo> sortDESC(List<BaseInfo> list){
        return list == null ? null : list.stream().sorted(Comparator.comparing(BaseInfo::getNum).reversed()).collect(Collectors.toList());
    }
}

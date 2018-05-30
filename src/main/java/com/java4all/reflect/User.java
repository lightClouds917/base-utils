package com.java4all.reflect;

import java.util.Date;

/**
 * Author: momo
 * Date: 2018/5/30
 * Description:
 */
public class User {

    private Integer id;

    private String name;

    private Integer age;

    private String address;

    private String province;

    private String city;

    private Date birthday;

    private Date createTime;

    public User() {
    }

    public User(Integer id, String name, Integer age, String address, String province, String city, Date birthday, Date createTime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.province = province;
        this.city = city;
        this.birthday = birthday;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

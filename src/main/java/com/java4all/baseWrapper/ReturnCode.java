package com.java4all.baseWrapper;

/**
 * Author: momo
 * Date: 2018/5/24
 * Description:自定义返回码
 */
public enum ReturnCode {

    SUCCESS("0000","查询成功"),
    NODATA("0001","查询成功无记录"),
    FEAILED("0002","查询失败"),
    ACCOUNT_ERROR("1000", "账户不存在或被禁用"),
    API_NOT_EXISTS("1001", "请求的接口不存在"),
    API_NOT_PER("1002", "没有该接口的访问权限"),
    PARAMS_ERROR("1004", "参数为空或格式错误"),
    SIGN_ERROR("1005", "数据签名错误"),
    AMOUNT_NOT_QUERY("1010", "余额不够，无法进行查询"),
    API_DISABLE("1011", "查询权限已被限制"),
    UNKNOWN_IP("1099", "非法IP请求"),
    SYSTEM_ERROR("9999", "系统异常");

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ReturnCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}

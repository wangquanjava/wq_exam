package com.wq.vo;

public class BaseOutResp {

    private String code;
    private String msg;
    private Object data; // data 需要被子类用实际类型覆盖，否则 parse 会得到一个 map

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}

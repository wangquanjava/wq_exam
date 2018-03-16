package com.wq.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by wqquan.wang on 2017/11/9.
 */
public enum WSActionEnum {
    ASSIGN("ASSIGN", "发题"),
    TAKEUP("TAKEUP", "收题");


    private String code;
    private String name;

    WSActionEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static WSActionEnum codeOf(String code) {
        for (WSActionEnum e : WSActionEnum.values()) {
            if (StringUtils.equals(e.getCode(), code)) {
                return e;
            }
        }
        return null;
    }
}

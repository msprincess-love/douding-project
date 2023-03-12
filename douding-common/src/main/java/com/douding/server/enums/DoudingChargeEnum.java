package com.douding.server.enums;

public enum DoudingChargeEnum {

    CHARGE("C", "收费"),
    FREE("F", "免费");

    private String code;

    private String desc;

    DoudingChargeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}

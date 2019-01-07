package com.app.medibox.bean;

import java.io.Serializable;

/**
 * Created by yangy on 2018/5/4.
 */

public class Medicinal implements Serializable {
    //药品名称
    private String name;
    //时间
    private String time;
    //功能主治
    private String function;
    //使用方法
    private String usageMethod;
    //放置处
    private String place;
    //识别码
    private String idCode;
    //药品类型
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getUsageMethod() {
        return usageMethod;
    }

    public void setUsageMethod(String usageMethod) {
        this.usageMethod = usageMethod;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}

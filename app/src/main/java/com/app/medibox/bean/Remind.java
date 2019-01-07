package com.app.medibox.bean;

import com.app.medibox.util.UUIDTool;

import java.io.Serializable;

/**
 * Created by yangy on 2018/5/4.
 */

public class Remind implements Serializable {
    private String id;
    private String time;
    private String timePart;
    private String medicineName;
    private String medicineName1;
    private Integer timePartTag;

    public Remind() {
        id = UUIDTool.getUUID();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTimePartTag() {
        return timePartTag;
    }

    public void setTimePartTag(Integer timePartTag) {
        this.timePartTag = timePartTag;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimePart() {
        return timePart;
    }

    public void setTimePart(String timePart) {
        this.timePart = timePart;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineName1() {
        return medicineName1;
    }

    public void setMedicineName1(String medicineName1) {
        this.medicineName1 = medicineName1;
    }
}

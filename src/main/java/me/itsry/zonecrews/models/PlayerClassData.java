package me.itsry.zonecrews.models;

public class PlayerClassData {

    private String uuid;
    private String class_name;

    public PlayerClassData(String uuid, String class_name) {
        this.uuid = uuid;
        this.class_name = class_name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

}

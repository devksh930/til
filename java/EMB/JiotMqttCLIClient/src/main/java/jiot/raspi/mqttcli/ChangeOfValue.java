/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.mqttcli;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 *
 * @author yjkim
 */
public class ChangeOfValue {

    public static final String TYPE = "cov";

    private String handlerId;
    private int pointId;
    private String pointName;
    private int presentValue;

    public ChangeOfValue(String handlerId, int pointId,
            String pointName, int presentValue) {
        this.handlerId = handlerId;
        this.pointId = pointId;
        this.pointName = pointName;
        this.presentValue = presentValue;
    }

    public ChangeOfValue(JsonObject jsonObj) {
        handlerId = jsonObj.get("handlerId").getAsString();
        pointId = jsonObj.get("pointId").getAsInt();
        pointName = jsonObj.get("pointName").getAsString();
        presentValue = jsonObj.get("value").getAsInt();
    }

    public String getHandlerId() {
        return handlerId;
    }

    public int getPointId() {
        return pointId;
    }

    public String getPointName() {
        return pointName;
    }

    public int getPresentValue() {
        return presentValue;
    }

    @Override
    public String toString() {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", TYPE);
        obj.addProperty("handlerId", handlerId);
        obj.addProperty("pointId", pointId);
        obj.addProperty("pointName", pointName);
        obj.addProperty("value", presentValue);
        return (new Gson()).toJson(obj);
    }
}
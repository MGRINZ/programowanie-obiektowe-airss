package com.example.projekt1.Model.Devices;

import com.example.projekt1.Model.Device;

public class Smartphone extends Device {

    private String os;

    public Smartphone(String manufacturer, String model, String os) {
        super(manufacturer, model);
        this.os = os;
    }

    @Override
    public String getDeviceType() {
        return "Smartfon";
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}

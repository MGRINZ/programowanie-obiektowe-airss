package com.example.projekt1.Model.Devices;

import com.example.projekt1.Model.Device;

public class Smartphone extends Device {

    private final String os;

    public Smartphone(String manufacturer, String model, String os) {
        super(manufacturer, model);
        this.os = os;
    }

    @Override
    public String getDeviceType() {
        return "Smartfon";
    }
}

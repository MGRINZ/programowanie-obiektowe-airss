package com.example.projekt1.Model.Devices;

import com.example.projekt1.Model.Device;

public class Printer extends Device {

    private String type;

    public Printer(String manufacturer, String model, String type) {
        super(manufacturer, model);
        this.type = type;
    }

    @Override
    public String getDeviceType() {
        return "Drukarka";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

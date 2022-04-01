package com.example.projekt1.Model.Devices;

import com.example.projekt1.Model.Device;

public class Printer extends Device {

    private final String type;

    public Printer(String manufacturer, String model, String type) {
        super(manufacturer, model);
        this.type = type;
    }

    @Override
    public String getDeviceType() {
        return "Drukarka";
    }
}

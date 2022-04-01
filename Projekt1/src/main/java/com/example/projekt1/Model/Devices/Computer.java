package com.example.projekt1.Model.Devices;

import com.example.projekt1.Model.Device;

public class Computer extends Device {

    private String os;
    private boolean formatAllowed;
    private String type;

    public Computer(String manufacturer, String model, String os, boolean formatAllowed, String type) {
        super(manufacturer, model);
        this.os = os;
        this.formatAllowed = formatAllowed;
        this.type = type;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public boolean isFormatAllowed() {
        return formatAllowed;
    }

    public void setFormatAllowed(boolean formatAllowed) {
        this.formatAllowed = formatAllowed;
    }

    public String getDeviceType() {
        return type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

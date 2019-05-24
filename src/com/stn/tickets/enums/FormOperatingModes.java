package com.stn.tickets.enums;

import com.stn.tickets.utils.Constants;

public enum FormOperatingModes {

    NEW(1),
    UPDATE_DELETE(2),
    VIEW(3);

    private int operatingTypeId;

    FormOperatingModes(int id) {
        this.operatingTypeId = id;
    }

    public int getOperatingTypeId() {
        return operatingTypeId;
    }

    public static FormOperatingModes getFormOperatingModeById(int id) {
        for (FormOperatingModes mode : FormOperatingModes.values())
            if (mode.getOperatingTypeId() == id)
                return mode;
        return null;
    }

}

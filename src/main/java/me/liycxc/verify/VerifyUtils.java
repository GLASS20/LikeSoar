package me.liycxc.verify;

import lombok.experimental.UtilityClass;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

@UtilityClass
public class VerifyUtils {
    public String getSerialNumber() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
        return hardwareAbstractionLayer.getComputerSystem().getBaseboard().getSerialNumber();
    }
}

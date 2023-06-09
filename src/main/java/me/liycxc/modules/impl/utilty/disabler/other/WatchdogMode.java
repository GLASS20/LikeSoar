package me.liycxc.modules.impl.utilty.disabler.other;

import me.liycxc.api.events.EventTarget;
import me.liycxc.api.events.impl.EventPreUpdate;
import me.liycxc.api.value.impl.BoolValue;
import me.liycxc.modules.impl.utilty.disabler.DisablerMode;
import me.liycxc.utils.PlayerUtils;

public class WatchdogMode extends DisablerMode {
    public WatchdogMode() {
        super("Watchdog");
    }

    public BoolValue disable = new BoolValue("disable",true);

    @EventTarget
    public void onPreUpdate(EventPreUpdate event) {
        PlayerUtils.tellPlayer("Watchdog was " + (disable.get() ? "disable" : "enable"));
    }
}

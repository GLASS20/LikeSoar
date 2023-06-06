package me.liycxc.manages.component.impl;

import me.liycxc.NekoCat;
import me.liycxc.api.events.EventTarget;
import me.liycxc.api.events.impl.EventKey;
import me.liycxc.manages.component.Component;
import me.liycxc.modules.Module;

import java.util.Map;

public class KeybindComponent extends Component {
    @EventTarget
    public void onKey(EventKey key) {
        if (mc.currentScreen != null) {
            return;
        }
        if (NekoCat.instance.moduleManager.keyBinds.containsKey(key.getKey())) {
            for (Map.Entry<Integer, Module> entry : NekoCat.instance.moduleManager.keyBinds.entries()) {
                if (entry.getKey().equals(key.getKey())) {
                    entry.getValue().toggle();
                }
            }
        }
    }
}

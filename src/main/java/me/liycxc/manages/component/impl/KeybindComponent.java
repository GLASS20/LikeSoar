package me.liycxc.manages.component.impl;

import me.liycxc.NekoCat;
import me.liycxc.api.events.EventTarget;
import me.liycxc.api.events.impl.EventKey;
import me.liycxc.manages.component.Component;

public class KeybindComponent extends Component {
    @EventTarget
    public void onKey(EventKey key) {
        if (mc.currentScreen != null) {
            return;
        }
        if (NekoCat.instance.moduleManager.keyBinds.containsKey(key.getKey())) {
            NekoCat.instance.moduleManager.keyBinds.get(key.getKey()).toggle();
        }
    }
}

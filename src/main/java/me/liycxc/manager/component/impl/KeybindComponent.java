package me.liycxc.manager.component.impl;

import me.liycxc.NekoCat;
import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.EventKey;
import me.liycxc.manager.component.Component;

public class KeybindComponent extends Component {
    @EventTarget
    public void onKey(EventKey key) {
        if (NekoCat.instance.moduleManager.keyBinds.containsKey(key.getKey())) {
            NekoCat.instance.moduleManager.keyBinds.get(key.getKey()).toggle();
        }
    }
}

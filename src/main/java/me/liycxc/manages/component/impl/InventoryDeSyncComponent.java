package me.liycxc.manages.component.impl;

import me.liycxc.api.events.EventTarget;
import me.liycxc.api.events.impl.EventReceivePacket;
import me.liycxc.manages.component.Component;
import me.liycxc.utils.PlayerUtils;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S2DPacketOpenWindow;

public class InventoryDeSyncComponent extends Component {

    private static boolean active, deSynced;

    @EventTarget
    public void onPacketReceive(EventReceivePacket event) {
        Packet<?> p = event.getPacket();

        if (p instanceof S2DPacketOpenWindow) {
            if (active) {
                event.setCancelled(true);
                deSynced = true;
                active = false;
            }
        }
    };

    public static void setActive(String command) {
        if (active || deSynced || mc.currentScreen != null) {
            return;
        }

        PlayerUtils.tellPlayer(command);
        active = true;
    }

    public static boolean isDeSynced() {
        return deSynced;
    }
}

package me.liycxc.modules.impl.combat;

import me.liycxc.NekoCat;
import me.liycxc.api.impl.BoolValue;
import me.liycxc.api.tags.ModuleTag;
import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.EventPreMotion;
import me.liycxc.modules.Module;
import me.liycxc.modules.ModuleCategory;
import net.minecraft.client.network.NetworkPlayerInfo;

@ModuleTag
public class AntiBot extends Module {
    public AntiBot() {
        super("AntiBot", "omg matrix bot", ModuleCategory.Combat);
    }

//    private final BoolValue advancedAntiBot = new BoolValue("Always Nearby Check", false);

    private final BoolValue watchdogAntiBot = new BoolValue("Watchdog Check", false);

/*    private final BoolValue funcraftAntiBot = new BoolValue("Funcraft Check", false);

    private final BoolValue ncps = new BoolValue("NPC Detection Check", false);

    private final BoolValue middleClick = new BoolValue("Middle Click Bot", false);*/

    @EventTarget
    public void onPreMotion(EventPreMotion event) {
        if (watchdogAntiBot.get()) {
            mc.theWorld.playerEntities.forEach(player -> {
                final NetworkPlayerInfo info = mc.getNetHandler().getPlayerInfo(player.getUniqueID());

                if (info == null) {
                    NekoCat.instance.botManager.add(player);
                } else {
                    NekoCat.instance.botManager.remove(player);
                }
            });
        }
    }

    @Override
    public void onDisable() {
        NekoCat.instance.botManager.clear();
    }
}

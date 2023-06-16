package me.liycxc.modules.impl.combat;

import me.liycxc.api.events.EventTarget;
import me.liycxc.api.events.impl.EventReceivePacket;
import me.liycxc.api.value.impl.ListValue;
import me.liycxc.modules.Module;
import me.liycxc.modules.ModuleCategory;
import me.liycxc.utils.module.player.MoveUtil;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.potion.Potion;

public class Velocity extends Module {
    public Velocity() {
        super("Velocity","No kb", ModuleCategory.Combat);
    }

    private final ListValue mode = new ListValue("Mode",new String[]{"Silky","None"},"Silky");

    @EventTarget
    public void onRPacket(EventReceivePacket event) {
        Packet<?> packet = event.getPacket();
        if (packet instanceof S12PacketEntityVelocity) {
            if (((S12PacketEntityVelocity) packet).entityID != mc.thePlayer.getEntityId()) {
                return;
            }
            switch(mode.get()) {
                case "Silky": {
                    event.setCancelled(true);
                    if (mc.thePlayer.onGround) {
                        mc.thePlayer.motionY = ((S12PacketEntityVelocity) packet).getMotionY() / 8000.0;
                        if (mc.thePlayer.isPotionActive(Potion.moveSpeed) && MoveUtil.isMoving()) MoveUtil.strafe();
                    }
                    break;
                }
                case "None": {

                }
            }
        }
    }

}

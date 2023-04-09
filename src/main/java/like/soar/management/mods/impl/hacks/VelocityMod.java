package like.soar.management.mods.impl.hacks;

import like.soar.management.events.EventTarget;
import like.soar.management.events.impl.EventReceivePacket;
import like.soar.management.events.impl.EventSendPacket;
import like.soar.management.events.impl.EventUpdate;
import like.soar.Soar;
import like.soar.management.mods.Mod;
import like.soar.management.mods.ModCategory;
import like.soar.utils.PlayerUtils;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;

import java.util.ArrayList;

public class VelocityMod extends Mod {
    public VelocityMod() {
        super("Velocity","Nokkkkk", ModCategory.HACK);
    }

    @Override
    public void setup() {
        ArrayList<String> modes = new ArrayList<>();
        modes.add("Packet");
        modes.add("AAC");
        modes.add("AAC3");
        modes.add("AAC4");
        this.addModeSetting("Mode",this,"Packet",modes);
        this.addSliderSetting("Horizontal",this,0,-100,100,true);
        this.addSliderSetting("Vertical",this,0,-100,100,true);
    }

    @EventTarget
    public void onPacketReceive(EventReceivePacket event) {
        float horizontal = Soar.instance.settingsManager.getSettingByName(this,"Horizontal").getValInt();
        float vertical = Soar.instance.settingsManager.getSettingByName(this,"Vertical").getValInt();
        if (Soar.instance.settingsManager.getSettingByName(this,"Mode").getValString().equals("Packet")) {
            if (event.getPacket() instanceof S12PacketEntityVelocity) {
                S12PacketEntityVelocity packet = (S12PacketEntityVelocity) event.getPacket();

                if (packet.getEntityID() == mc.thePlayer.getEntityId()) {
                    if (horizontal == 0f && vertical == 0f) {
                        event.setCancelled(true);
                    } else {
                        packet.setMotionX((int) (packet.getMotionX() * horizontal / 100.0));
                        packet.setMotionY((int) (packet.getMotionY() * vertical / 100.0));
                        packet.setMotionZ((int) (packet.getMotionZ() * horizontal / 100.0));
                    }
                }
            }

            //hypixel fucker
            if (event.getPacket() instanceof S27PacketExplosion) {
                handle(event);
            }
        }
    }


    public void handle(EventReceivePacket event) {
        float horizontal = Soar.instance.settingsManager.getSettingByName(this,"Horizontal").getValInt();
        float vertical = Soar.instance.settingsManager.getSettingByName(this,"Vertical").getValInt();
        S27PacketExplosion packet = (S27PacketExplosion) event.getPacket();

        if (horizontal == 0f && vertical == 0f) {
            event.setCancelled(true);
        } else {
            packet.setMotionX(packet.getMotionX() * horizontal / 100.0f);
            packet.setMotionY(packet.getMotionY() * vertical / 100.0f);
            packet.setMotionZ(packet.getMotionZ() * horizontal / 100.0f);
        }
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        if (Soar.instance.settingsManager.getSettingByName(this,"Mode").getValString().equals("AAC3")) {
            if (mc.thePlayer.hurtTime > 0 && mc.thePlayer.fallDistance < 3) {
                if (mc.thePlayer.moveForward == 0 && mc.thePlayer.moveStrafing == 0) {
                    mc.thePlayer.motionY -= 1.001D;
                    mc.thePlayer.motionX *= 0.2D;
                    mc.thePlayer.motionZ *= 0.2D;
                    mc.thePlayer.motionY += 1D;
                } else {
                    mc.thePlayer.motionX *= 0.5D;
                    mc.thePlayer.motionZ *= 0.5D;
                }
            }
        } else if (Soar.instance.settingsManager.getSettingByName(this,"Mode").getValString().equals("AAC4")) {
            if (mc.thePlayer.hurtTime > 0 && PlayerUtils.isMoving()) {
                mc.thePlayer.motionX *= 0.6;
                mc.thePlayer.motionZ *= 0.6;
            }
        }
    }

    @EventTarget
    public void onPacketSend(EventSendPacket event) {
        if (!Soar.instance.settingsManager.getSettingByName(this,"Mode").getValString().equals("AAC"))
            return;

        if (event.getPacket() instanceof C02PacketUseEntity && mc.thePlayer.movementInput.moveForward == 1) {
            final C02PacketUseEntity packet = (C02PacketUseEntity) event.getPacket();
            if (packet.getAction() == C02PacketUseEntity.Action.ATTACK && mc.thePlayer.onGround) {
                mc.thePlayer.setSprinting(true);
            }
        }
    }
}

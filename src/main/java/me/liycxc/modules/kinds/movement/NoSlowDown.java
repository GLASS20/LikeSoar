package me.liycxc.modules.kinds.movement;

import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.EventPreMotion;
import me.liycxc.events.impl.EventSendPacket;
import me.liycxc.events.impl.EventSlowDown;
import me.liycxc.modules.ListValue;
import me.liycxc.modules.Module;
import me.liycxc.modules.ModuleCategory;
import me.liycxc.modules.kinds.combat.killAura.utils.MoveUtil;
import me.liycxc.utils.MSTimer;
import me.liycxc.utils.PlayerUtils;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;

public class NoSlowDown extends Module {
    public NoSlowDown() {
        super("NoSlowDown","Get blocking no slow", ModuleCategory.Movement);
    }

    public ListValue modeValue = new ListValue("Mode",new String[]{"Vanilla","Hypixel"},"Vanilla");

    MSTimer timer = new MSTimer();

    @EventTarget
    public void onUpdate(EventPreMotion event) {
        if (!MoveUtil.isMoving() || !mc.gameSettings.keyBindUseItem.isPressed()) {
            return;
        }

        switch (modeValue.get()) {
            case "Vanilla": {
                break;
            }
            case "Hypixel": {
                if (mc.thePlayer.getHeldItem() != null && mc.thePlayer.getHeldItem().getItem() instanceof ItemSword && mc.thePlayer.isBlocking()) {
                    mc.getNetHandler().addToSendQueueNoEvent(new C08PacketPlayerBlockPlacement(mc.thePlayer.getHeldItem()));
                }
                if (mc.thePlayer.getHeldItem() != null && mc.thePlayer.getHeldItem().getItem() instanceof ItemBow && mc.thePlayer.isUsingItem()) {
                    if (timer.hasTimePassed(230)) {
                        final int slot = mc.thePlayer.inventory.currentItem;
                        mc.getNetHandler().addToSendQueueNoEvent(new C09PacketHeldItemChange((slot + 1) % 8));
                        mc.getNetHandler().addToSendQueueNoEvent(new C09PacketHeldItemChange(slot));
                        timer.reset();
                    }
                }
                break;
            }
        }
    }

    @EventTarget
    public void onSendPacket(EventSendPacket eventSendPacket) {
        Packet packet = eventSendPacket.getPacket();

        if (mc.thePlayer == null) {
            return;
        }

        if (!MoveUtil.isMoving() || !mc.gameSettings.keyBindUseItem.isPressed()) {
            return;
        }

        switch (modeValue.get()) {
            case "Vanilla": {
                break;
            }
            case "Hypixel": {
                if (packet instanceof C07PacketPlayerDigging && ((C07PacketPlayerDigging) packet).status == C07PacketPlayerDigging.Action.RELEASE_USE_ITEM && mc.thePlayer.getHeldItem() != null && !(mc.thePlayer.getHeldItem().getItem() instanceof ItemBow)) {
                    eventSendPacket.setCancelled(true);
                    final int slot = mc.thePlayer.inventory.currentItem;
                    mc.getNetHandler().addToSendQueueNoEvent(new C09PacketHeldItemChange((slot + 1) % 8));
                    mc.getNetHandler().addToSendQueueNoEvent(new C09PacketHeldItemChange(slot));
                    PlayerUtils.tellPlayer("Ues");
                }
                break;
            }
        }
    }

    @EventTarget
    public void onSlowDown (EventSlowDown eventSlowDown) {
        if (mc.thePlayer.isUsingItem() && !mc.thePlayer.isInWeb && !mc.thePlayer.isInWater() && !mc.thePlayer.isInLava() && MoveUtil.isMoving()) {
            eventSlowDown.setCancelled(true);
        }
    }
}

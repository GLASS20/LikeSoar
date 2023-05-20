package me.liycxc.modules.kinds.combat;

import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.EventTick;
import me.liycxc.modules.*;
import me.liycxc.utils.TimerUtils;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemSword;

public class AutoClick extends Module {
    public AutoClick() {
        super("AutoClick","Mouse down auto click", ModuleCategory.Combat);
    }
    
    private IntegerValue aCps = new IntegerValue("A CPS",8,1,20);
    private IntegerValue bCps = new IntegerValue("B CPS",8,1,20);
    private BoolValue rightClick = new BoolValue("Right Click",false);
    private BoolValue leftClick = new BoolValue("Left Click",true);
    private ListValue autoBlock = new ListValue("AutoBlock",new String[]{"None", "Legit", "Fast"},"None",() -> leftClick.get());
    private IntegerValue autoblockDelay = new IntegerValue("AutoBlock Delay",120,100,300,() -> leftClick.get() && autoBlock.get().equals("Legit"));

    private long leftDelay = TimerUtils.randomClickDelay(minCps(),maxCps());
    private long leftLastSwing = 0L;
    
    private long rightDelay = TimerUtils.randomClickDelay(minCps(),maxCps());
    private long rightLastSwing = 0L;

    private TimerUtils timeHelper = new TimerUtils();
    
    @EventTarget
    public void onTick(EventTick tick) {
        // Left Click
        if (mc.gameSettings.keyBindAttack.isKeyDown() && leftClick.get()) {
            if (System.currentTimeMillis() - leftLastSwing >= leftDelay) {
                mc.thePlayer.swingItem();
                KeyBinding.onTick(mc.gameSettings.keyBindAttack.getKeyCode());

                // Bad packets
               /* switch (mc.objectMouseOver.typeOfHit) {
                    case ENTITY:
                        mc.playerController.attackEntity(mc.thePlayer, mc.objectMouseOver.entityHit);
                        break;

                    case BLOCK:
                        BlockPos blockpos = mc.objectMouseOver.getBlockPos();

                        if (mc.theWorld.getBlockState(blockpos).getBlock().getMaterial() != Material.air) {
                            mc.playerController.clickBlock(blockpos, mc.objectMouseOver.sideHit);
                            break;
                        }

                    case MISS:
                    default:
                        if (mc.playerController.isNotCreative()) {
                            mc.leftClickCounter = 10;
                        }
                }*/

                // Legit AutoBlock
                if (autoBlock.get().equals("Legit") && mc.objectMouseOver.entityHit != null && mc.objectMouseOver.entityHit.isEntityAlive() && mc.thePlayer.inventory.getCurrentItem() != null) {
                    if (mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword && timeHelper.delay(autoblockDelay.get().longValue())) {
                        mc.thePlayer.getCurrentEquippedItem().useItemRightClick(mc.theWorld, mc.thePlayer);
                        timeHelper.reset();
                    }
                }
                // Fast AutoBlock
                if (autoBlock.get().equals("Fast")) {
                    mc.thePlayer.getCurrentEquippedItem();
                    if (!(mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword)) {
                        return;
                    }
                    mc.thePlayer.getHeldItem().useItemRightClick(mc.theWorld, mc.thePlayer);
                }
                leftLastSwing = System.currentTimeMillis();
                leftDelay = TimerUtils.randomClickDelay(minCps(), maxCps());
            }
        }

        // Right Click
        if (mc.gameSettings.keyBindUseItem.isKeyDown() && !mc.thePlayer.isUsingItem() && rightClick.get() && System.currentTimeMillis() - rightLastSwing >= rightDelay) {
            // Minecraft Click Handling
            KeyBinding.onTick(mc.gameSettings.keyBindUseItem.getKeyCode());

            rightLastSwing = System.currentTimeMillis();
            rightDelay = TimerUtils.randomClickDelay(minCps(),maxCps());
        }
    }
    
    private int maxCps() {
        return Math.max(aCps.get(),bCps.get());
    }
    
    private int minCps() {
        return Math.min(aCps.get(),bCps.get());
    }
}

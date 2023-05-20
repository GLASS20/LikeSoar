package me.liycxc.modules.kinds.utilty;

import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.EventTick;
import me.liycxc.modules.BoolValue;
import me.liycxc.modules.IntegerValue;
import me.liycxc.modules.Module;
import me.liycxc.modules.ModuleCategory;
import me.liycxc.utils.TimerUtils;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemBlock;

public class RightClick extends Module {
    public RightClick() {
        super("RightClick","AutoClick when you right mouse down", ModuleCategory.Util);
    }

    public IntegerValue cpsA = new IntegerValue("A CPS",10,1,30);
    public IntegerValue cpsB = new IntegerValue("B CPS",10,1,30);
    public BoolValue onlyBlock = new BoolValue("Only Blocks",true);


    public int getMaxCps() {
        return Math.max(cpsA.get(),cpsB.get());
    }

    public int getMinCps() {
        return Math.min(cpsA.get(),cpsB.get());
    }

    private long rightDelay = TimerUtils.randomClickDelay(getMinCps(), getMaxCps());
    private long rightLastSwing = 0L;

    @EventTarget
    public void onTick(EventTick tick) {
        boolean onlyBlocks = onlyBlock.get();
        if (mc.gameSettings.keyBindUseItem.isKeyDown() && !mc.thePlayer.isUsingItem() && System.currentTimeMillis() - rightLastSwing >= rightDelay) {
            if (mc.thePlayer.inventory.getCurrentItem() == null) {
                if (onlyBlocks) {
                    return;
                }
            } else if(onlyBlocks && !(mc.thePlayer.inventory.getCurrentItem().getItem() instanceof ItemBlock)) {
                return;
            }
            KeyBinding.onTick(mc.gameSettings.keyBindUseItem.getKeyCode());
            rightLastSwing = System.currentTimeMillis();
            rightDelay = TimerUtils.randomClickDelay(getMinCps(), getMaxCps());
        }
    }


}

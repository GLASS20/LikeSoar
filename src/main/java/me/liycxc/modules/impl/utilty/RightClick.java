package me.liycxc.modules.impl.utilty;

import me.liycxc.api.impl.BoolValue;
import me.liycxc.api.impl.IntValue;
import me.liycxc.api.tags.ModuleTag;
import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.EventTick;
import me.liycxc.modules.Module;
import me.liycxc.modules.ModuleCategory;
import me.liycxc.utils.TimerUtils;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemBlock;

@ModuleTag
public class RightClick extends Module {
    public RightClick() {
        super("RightClick","AutoClick when you right mouse down", ModuleCategory.Util);
    }

    public IntValue cpsA = new IntValue("A CPS",10,1,30);
    public IntValue cpsB = new IntValue("B CPS",10,1,30);
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

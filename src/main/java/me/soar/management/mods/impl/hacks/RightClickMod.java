package me.soar.management.mods.impl.hacks;

import me.soar.Soar;
import me.soar.management.events.EventTarget;
import me.soar.management.events.impl.EventTick;
import me.soar.management.mods.Mod;
import me.soar.management.mods.ModCategory;
import me.soar.utils.TimerUtils;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemBlock;

public class RightClickMod extends Mod {
    public RightClickMod() {
        super("RightClick","Hacker uuuu", ModCategory.HACK);
    }

    @Override
    public void setup() {
        this.addSliderSetting("Cps A", this, 10, 1, 30, true);
        this.addSliderSetting("Cps B", this, 15, 1, 30, true);
        this.addBooleanSetting("Only Blocks",this,true);
    }

    public int getMaxCps() {
        return Math.max(Soar.instance.settingsManager.getSettingByName(this, "Cps A").getValInt(), Soar.instance.settingsManager.getSettingByName(this, "Cps B").getValInt());
    }

    public int getMinCps() {
        return Math.min(Soar.instance.settingsManager.getSettingByName(this, "Cps A").getValInt(), Soar.instance.settingsManager.getSettingByName(this, "Cps B").getValInt());
    }

    private long rightDelay = TimerUtils.randomClickDelay(getMinCps(), getMaxCps());
    private long rightLastSwing = 0L;

    @EventTarget
    public void onTick(EventTick tick) {
        boolean onlyBlocks = Soar.instance.settingsManager.getSettingByName(this,"Only Blocks").getValBoolean();
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

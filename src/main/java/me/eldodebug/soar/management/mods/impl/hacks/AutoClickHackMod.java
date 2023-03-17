package me.eldodebug.soar.management.mods.impl.hacks;

import me.eldodebug.soar.Soar;
import me.eldodebug.soar.management.events.EventTarget;
import me.eldodebug.soar.management.events.impl.EventTick;
import me.eldodebug.soar.management.mods.Mod;
import me.eldodebug.soar.management.mods.ModCategory;
import me.eldodebug.soar.utils.TimerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemSword;

public class AutoClickHackMod extends Mod {
    public AutoClickHackMod() {
        super("AutoClick Hack", "Hacker uu", ModCategory.OTHER);
    }

    @Override
    public void setup() {
        this.addSliderSetting("Cps A", this, 5, 1, 20, true);
        this.addSliderSetting("Cps B", this, 8, 1, 20, true);
        this.addBooleanSetting("AutoBlock", this, true);
        this.addSliderSetting("AutoBlock Delay", this, 120, 100, 200, true);
    }

    public int getMaxCps() {
        return Math.max(Soar.instance.settingsManager.getSettingByName(this, "Cps A").getValInt(), Soar.instance.settingsManager.getSettingByName(this, "Cps B").getValInt());
    }

    public int getMinCps() {
        return Math.min(Soar.instance.settingsManager.getSettingByName(this, "Cps A").getValInt(), Soar.instance.settingsManager.getSettingByName(this, "Cps B").getValInt());
    }

    private long leftDelay = TimerUtils.randomClickDelay(getMinCps(), getMaxCps());
    private long leftLastSwing = 0L;
    private TimerUtils abTimer = new TimerUtils();

    @EventTarget
    public void onTick(EventTick tick) {
        boolean autoBlock = Soar.instance.settingsManager.getSettingByName(this,"AutoBlock").getValBoolean();
        long autoblockDelay = Soar.instance.settingsManager.getSettingByName(this,"AutoBlock Delay").getValInt();

        if(mc.gameSettings.keyBindAttack.isKeyDown()) {
            if (System.currentTimeMillis() - leftLastSwing >= leftDelay) {
                mc.thePlayer.swingItem();

                KeyBinding.onTick(mc.gameSettings.keyBindAttack.getKeyCode());
                if (mc.objectMouseOver.entityHit != null) {
                    mc.playerController.attackEntity(mc.thePlayer, Minecraft.getMinecraft().objectMouseOver.entityHit);
                }

                // Legit AutoBlock
                if (mc.objectMouseOver.entityHit != null && mc.objectMouseOver.entityHit.isEntityAlive() && mc.thePlayer.inventory.getCurrentItem() != null) {
                    if (mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword && abTimer.delay(autoblockDelay)) {
                        mc.thePlayer.getCurrentEquippedItem().useItemRightClick(mc.theWorld, mc.thePlayer);
                        abTimer.reset();
                    }
                }

                leftLastSwing = System.currentTimeMillis();
                leftDelay = TimerUtils.randomClickDelay(getMinCps(), getMaxCps());
            }
        }
    }
}

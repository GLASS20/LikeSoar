package me.liycxc.pvp.management.mods.impl.hacks;

import me.liycxc.NekoCat;
import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.EventTick;
import me.liycxc.pvp.management.mods.Mod;
import me.liycxc.pvp.management.mods.ModCategory;
import me.liycxc.utils.TimerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemSword;

public class AutoClickMod extends Mod {
    public AutoClickMod() {
        super("AutoClick", "Hacker uu", ModCategory.HACK);
    }

    @Override
    public void setup() {
        this.addSliderSetting("Cps A", this, 5, 1, 20, true);
        this.addSliderSetting("Cps B", this, 8, 1, 20, true);
        this.addBooleanSetting("AutoBlock", this, true);
        this.addSliderSetting("AutoBlock Delay", this, 120, 100, 200, true);
        this.addBooleanSetting("Only Swords",this,false);
    }

    public int getMaxCps() {
        return Math.max(NekoCat.instance.settingsManager.getSettingByName(this, "Cps A").getValInt(), NekoCat.instance.settingsManager.getSettingByName(this, "Cps B").getValInt());
    }

    public int getMinCps() {
        return Math.min(NekoCat.instance.settingsManager.getSettingByName(this, "Cps A").getValInt(), NekoCat.instance.settingsManager.getSettingByName(this, "Cps B").getValInt());
    }

    private long leftDelay = TimerUtils.randomClickDelay(getMinCps(), getMaxCps());
    private long leftLastSwing = 0L;
    private TimerUtils abTimer = new TimerUtils();

    @EventTarget
    public void onTick(EventTick tick) {
        boolean autoBlock = NekoCat.instance.settingsManager.getSettingByName(this,"AutoBlock").getValBoolean();
        long autoblockDelay = NekoCat.instance.settingsManager.getSettingByName(this,"AutoBlock Delay").getValInt();
        boolean onlySwords = NekoCat.instance.settingsManager.getSettingByName(this,"Only Swords").getValBoolean();

        if(mc.gameSettings.keyBindAttack.isKeyDown()) {
            if (mc.thePlayer.inventory.getCurrentItem() == null) {
                if (onlySwords) {
                    return;
                }
            } else if (onlySwords && !(mc.thePlayer.inventory.getCurrentItem().getItem() instanceof ItemSword)) {
                return;
            }

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

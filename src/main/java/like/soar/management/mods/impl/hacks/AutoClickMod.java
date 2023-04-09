package like.soar.management.mods.impl.hacks;

import like.soar.management.events.EventTarget;
import like.soar.management.events.impl.EventTick;
import like.soar.management.mods.ModCategory;
import like.soar.Soar;
import like.soar.management.mods.Mod;
import like.soar.utils.TimerUtils;
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
        boolean onlySwords = Soar.instance.settingsManager.getSettingByName(this,"Only Swords").getValBoolean();

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

package me.soar.management.mods.impl;

import me.soar.Soar;
import me.soar.management.events.EventTarget;
import me.soar.management.events.impl.EventFovUpdate;
import me.soar.management.mods.Mod;
import me.soar.management.mods.ModCategory;
import me.soar.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class BowZoomMod extends Mod{

	public BowZoomMod() {
		super("Bow Zoom", "Zooming when using a bow", ModCategory.RENDER);
	}

	@Override
	public void setup() {
		this.addSliderSetting("Factor", this, 5, 1, 15, true);
	}
	
	@EventTarget
	public void onFovUpdate(EventFovUpdate event) {
		
		float base = 1.0F;
		EntityPlayer entity = event.getEntity();
		ItemStack item = entity.getItemInUse();
		int useDuration = entity.getItemInUseDuration();
		
		float bowFov = Soar.instance.settingsManager.getSettingByName(this, "Factor").getValInt();
		
		if(item != null && item.getItem() == Items.bow) {
			int duration = (int) Math.min(useDuration, 20.0F);
			float modifier = PlayerUtils.MODIFIER_BY_TICK.get(duration);
			base-= modifier * bowFov;
			event.setFov(base);
		}
	}
}

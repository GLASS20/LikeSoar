package like.soar.management.mods.impl;

import java.awt.Color;

import like.soar.management.events.EventTarget;
import like.soar.management.events.impl.EventHitOverlay;
import like.soar.Soar;
import like.soar.management.mods.Mod;
import like.soar.management.mods.ModCategory;
import like.soar.utils.color.ColorUtils;

public class HitColorMod extends Mod{

	public HitColorMod() {
		super("HitColor", "Change the color of the hit", ModCategory.RENDER);
	}

	@Override
	public void setup() {
		this.addSliderSetting("Alpha", this, 0.8, 0, 1, false);
	}
	
	@EventTarget
	public void onHitOverlay(EventHitOverlay event) {
		Color color = ColorUtils.getClientColor(0);
		float alpha = (float) Soar.instance.settingsManager.getSettingByName(this, "Alpha").getValDouble();
		
		event.setRed(color.getRed() / 255F);
		event.setGreen(color.getGreen() / 255F);
		event.setBlue(color.getBlue() / 255F);
		event.setAlpha(alpha);
	}
}

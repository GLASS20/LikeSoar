package like.soar.management.mods.impl;

import java.awt.Color;

import like.soar.Soar;
import like.soar.management.mods.Mod;
import like.soar.management.mods.ModCategory;
import like.soar.management.settings.Setting;
import like.soar.utils.color.ColorUtils;

public class GlintColorMod extends Mod{

	public static GlintColorMod instance = new GlintColorMod();
	
	public GlintColorMod() {
		super("Glint Color", "Customize Glint Color", ModCategory.RENDER);
	}

	@Override
	public void setup() {
		Soar.instance.settingsManager.addSetting(new Setting("Rainbow", this, false));
	}
	
	public Color getColor() {
		if(Soar.instance.settingsManager.getSettingByClass(GlintColorMod.class, "Rainbow").getValBoolean()) {
			return ColorUtils.rainbow(0, 25, 255);
		}
		
		return ColorUtils.getClientColor(0);
	}
}

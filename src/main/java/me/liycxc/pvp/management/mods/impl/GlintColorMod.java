package me.liycxc.pvp.management.mods.impl;

import me.liycxc.NekoCat;
import me.liycxc.pvp.management.mods.Mod;
import me.liycxc.pvp.management.mods.ModCategory;
import me.liycxc.pvp.management.settings.Setting;
import me.liycxc.utils.color.ColorUtils;

import java.awt.*;

public class GlintColorMod extends Mod {

	public static GlintColorMod instance = new GlintColorMod();
	
	public GlintColorMod() {
		super("Glint Color", "Customize Glint Color", ModCategory.RENDER);
	}

	@Override
	public void setup() {
		NekoCat.instance.settingsManager.addSetting(new Setting("Rainbow", this, false));
	}
	
	public Color getColor() {
		if(NekoCat.instance.settingsManager.getSettingByClass(GlintColorMod.class, "Rainbow").getValBoolean()) {
			return ColorUtils.rainbow(0, 25, 255);
		}
		
		return ColorUtils.getClientColor(0);
	}
}

package me.soar.management.mods.impl;

import me.soar.Soar;
import me.soar.management.mods.Mod;
import me.soar.management.mods.ModCategory;
import me.soar.management.settings.Setting;

public class MenuBlurMod extends Mod{

	public MenuBlurMod() {
		super("Menu Blur", "Blur the menu", ModCategory.RENDER);
	}

	@Override
	public void setup() {
		Soar.instance.settingsManager.addSetting(new Setting("Radius", this, 20, 1, 40, true));
	}
}

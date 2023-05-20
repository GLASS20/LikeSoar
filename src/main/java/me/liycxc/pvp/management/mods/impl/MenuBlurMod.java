package me.liycxc.pvp.management.mods.impl;

import me.liycxc.NekoCat;
import me.liycxc.pvp.management.mods.Mod;
import me.liycxc.pvp.management.mods.ModCategory;
import me.liycxc.pvp.management.settings.Setting;

public class MenuBlurMod extends Mod {

	public MenuBlurMod() {
		super("Menu Blur", "Blur the menu", ModCategory.RENDER);
	}

	@Override
	public void setup() {
		NekoCat.instance.settingsManager.addSetting(new Setting("Radius", this, 20, 1, 40, true));
	}
}

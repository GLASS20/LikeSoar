package me.liycxc.gui.management.mods.impl;

import me.liycxc.NekoCat;
import me.liycxc.gui.management.mods.Mod;
import me.liycxc.gui.management.settings.Setting;
import me.liycxc.gui.management.mods.ModCategory;

public class MenuBlurMod extends Mod {

	public MenuBlurMod() {
		super("Menu Blur", "Blur the menu", ModCategory.RENDER);
	}

	@Override
	public void setup() {
		NekoCat.instance.settingsManager.addSetting(new Setting("Radius", this, 20, 1, 40, true));
	}
}

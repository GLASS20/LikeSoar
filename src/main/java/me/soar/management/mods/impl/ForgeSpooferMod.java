package me.soar.management.mods.impl;

import me.soar.management.mods.Mod;
import me.soar.management.mods.ModCategory;
import me.soar.utils.ClientUtils;

public class ForgeSpooferMod extends Mod {

	public ForgeSpooferMod() {
		super("Fake Forge", "Recognize it as Forge on the server side", ModCategory.OTHER);
	}

	@Override
	public void onEnable() {
		super.onEnable();
		ClientUtils.showNotification("Mod", "Please reconnect to the server to reflect");
	}
}

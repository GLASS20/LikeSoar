package me.soar.management.mods.impl;

import me.soar.management.mods.Mod;
import me.soar.management.mods.ModCategory;
import me.soar.utils.ClientUtils;

public class HitDelayFixMod extends Mod{

	public HitDelayFixMod() {
		super("Hit Delay Fix", "Fix delay which is triggered every time you click without attacking a player mob", ModCategory.PLAYER);
	}

	@Override
	public void onEnable() {
		super.onEnable();
		ClientUtils.showNotification("Warning", "This mod is banned on some servers");
	}
}

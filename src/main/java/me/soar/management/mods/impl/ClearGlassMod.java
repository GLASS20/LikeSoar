package me.soar.management.mods.impl;

import me.soar.management.mods.Mod;
import me.soar.management.mods.ModCategory;

public class ClearGlassMod extends Mod{

	public ClearGlassMod() {
		super("Clear Glass", "Clear glass block", ModCategory.RENDER);
	}

	@Override
	public void onEnable() {
		super.onEnable();
        mc.renderGlobal.loadRenderers();
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
        mc.renderGlobal.loadRenderers();
	}
}

package me.liycxc.gui.management.mods.impl;

import me.liycxc.gui.management.mods.Mod;
import me.liycxc.gui.management.mods.ModCategory;

public class FullbrightMod extends Mod {

	public FullbrightMod() {
		super("Fullbright", "Brighten the screen", ModCategory.RENDER);
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
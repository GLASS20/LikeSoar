package me.soar.management.mods.impl;

import me.soar.management.mods.Mod;
import me.soar.management.mods.ModCategory;

public class SlowSwingMod extends Mod{

	public SlowSwingMod() {
		super("Slow Swing", "Slow down swing speed", ModCategory.OTHER);
	}
	
	@Override
	public void setup() {
		this.addSliderSetting("Delay", this, 14, 2, 20, true);
	}

}

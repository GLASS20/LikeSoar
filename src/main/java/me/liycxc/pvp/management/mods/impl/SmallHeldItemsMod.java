package me.liycxc.pvp.management.mods.impl;

import me.liycxc.pvp.management.mods.Mod;
import me.liycxc.pvp.management.mods.ModCategory;

public class SmallHeldItemsMod extends Mod {

	public SmallHeldItemsMod() {
		super("Small Held Items", "Make your hands smaller", ModCategory.RENDER);
	}
	
	@Override
	public void setup() {
		this.addSliderSetting("X", this, 0.75, -1, 1, false);
		this.addSliderSetting("Y", this, -0.15, -1, 1, false);
		this.addSliderSetting("Z", this, -1, -1, 1, false);
	}
}

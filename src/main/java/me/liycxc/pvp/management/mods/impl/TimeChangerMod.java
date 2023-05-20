package me.liycxc.pvp.management.mods.impl;

import me.liycxc.pvp.management.mods.Mod;
import me.liycxc.pvp.management.mods.ModCategory;

public class TimeChangerMod extends Mod {

	public TimeChangerMod() {
		super("Time Changer", "Change world time", ModCategory.RENDER);
	}

	@Override
	public void setup() {
		this.addSliderSetting("Time", this, 0, 0, 15000, true);
	}
}

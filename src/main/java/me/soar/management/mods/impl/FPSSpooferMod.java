package me.soar.management.mods.impl;

import me.soar.management.mods.Mod;
import me.soar.management.mods.ModCategory;

public class FPSSpooferMod extends Mod{

	public FPSSpooferMod() {
		super("FPS Spoofer", "Spoof Minecraft framerate", ModCategory.OTHER);
	}

	@Override
	public void setup() {
		this.addSliderSetting("Multiplication", this, 3, 1, 20, true);
	}
}

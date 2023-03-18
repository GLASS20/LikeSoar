package me.soar.management.mods.impl;

import me.soar.management.mods.Mod;
import me.soar.management.mods.ModCategory;

public class MotionBlurMod extends Mod{

	public MotionBlurMod() {
		super("Motion Blur", "Adding afterimages to movements", ModCategory.RENDER);
	}

	@Override
	public void setup() {
		this.addSliderSetting("Amount", this, 0.5, 0.1, 0.85, false);
	}
}

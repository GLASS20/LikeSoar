package me.liycxc.gui.management.mods.impl;

import me.liycxc.gui.management.mods.Mod;
import me.liycxc.gui.management.mods.ModCategory;

public class BlockOverlayMod extends Mod {

	public BlockOverlayMod() {
		super("Block Overlay", "Enclose the selected block", ModCategory.RENDER);
	}

	@Override
	public void setup() {
		this.addBooleanSetting("Outline", this, false);
		this.addSliderSetting("Width", this, 3, 1, 10, true);
		this.addBooleanSetting("Fill", this, true);
		this.addSliderSetting("Opacity", this, 0.5, 0.1, 1, false);
		this.addBooleanSetting("Depth", this, true);
	}
}
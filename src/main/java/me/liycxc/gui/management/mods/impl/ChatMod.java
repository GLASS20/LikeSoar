package me.liycxc.gui.management.mods.impl;

import me.liycxc.gui.management.mods.Mod;
import me.liycxc.gui.management.mods.ModCategory;

public class ChatMod extends Mod {

	public ChatMod() {
		super("Chat", "Customize chat", ModCategory.RENDER);
	}

	@Override
	public void setup() {
		this.addBooleanSetting("Bar Animation", this, false);
		this.addBooleanSetting("Smooth", this, true);
		this.addSliderSetting("Smooth Speed", this, 4, 1, 10, true);
		this.addBooleanSetting("Transparent background", this, true);
	}
}

package me.liycxc.gui.management.mods.impl;

import me.liycxc.gui.management.mods.Mod;
import me.liycxc.gui.management.mods.ModCategory;

public class TabEditorMod extends Mod {

	public TabEditorMod() {
		super("Tab Editor", "Customize Tab List", ModCategory.RENDER);
	}

	@Override
	public void setup() {
		this.addBooleanSetting("Remove Background", this, false);
		this.addBooleanSetting("Remove Player Head", this, false);
	}
}

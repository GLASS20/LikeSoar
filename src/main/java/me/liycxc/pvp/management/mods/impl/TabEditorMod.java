package me.liycxc.pvp.management.mods.impl;

import me.liycxc.pvp.management.mods.Mod;
import me.liycxc.pvp.management.mods.ModCategory;

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

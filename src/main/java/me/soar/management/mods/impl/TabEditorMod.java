package me.soar.management.mods.impl;

import me.soar.management.mods.Mod;
import me.soar.management.mods.ModCategory;

public class TabEditorMod extends Mod{

	public TabEditorMod() {
		super("Tab Editor", "Customize Tab List", ModCategory.RENDER);
	}

	@Override
	public void setup() {
		this.addBooleanSetting("Remove Background", this, false);
		this.addBooleanSetting("Remove Player Head", this, false);
	}
}

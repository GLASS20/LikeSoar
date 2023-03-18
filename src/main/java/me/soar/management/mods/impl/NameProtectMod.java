package me.soar.management.mods.impl;

import me.soar.management.events.EventTarget;
import me.soar.management.events.impl.EventText;
import me.soar.management.mods.Mod;
import me.soar.management.mods.ModCategory;

public class NameProtectMod extends Mod{

	public NameProtectMod() {
		super("Name Protect", "Protect your name", ModCategory.RENDER);
	}

	@EventTarget
	public void onText(EventText event) {
		event.replace(mc.getSession().getUsername(), "You");
	}
}

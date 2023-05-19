package me.liycxc.gui.management.mods.impl;

import me.liycxc.gui.management.mods.Mod;
import me.liycxc.gui.management.events.EventTarget;
import me.liycxc.gui.management.events.impl.EventText;
import me.liycxc.gui.management.mods.ModCategory;

public class NameProtectMod extends Mod {

	public NameProtectMod() {
		super("Name Protect", "Protect your name", ModCategory.RENDER);
	}

	@EventTarget
	public void onText(EventText event) {
		event.replace(mc.getSession().getUsername(), "You");
	}
}

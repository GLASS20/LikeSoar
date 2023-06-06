package me.liycxc.pvp.management.mods.impl;

import me.liycxc.api.events.EventTarget;
import me.liycxc.api.events.impl.EventText;
import me.liycxc.pvp.management.mods.Mod;
import me.liycxc.pvp.management.mods.ModCategory;

public class NameProtectMod extends Mod {

	public NameProtectMod() {
		super("Name Protect", "Protect your name", ModCategory.RENDER);
	}

	@EventTarget
	public void onText(EventText event) {
		event.replace(mc.getSession().getUsername(), "You");
	}
}

package me.liycxc.gui.management.mods.impl;

import me.liycxc.NekoCat;
import me.liycxc.gui.management.mods.Mod;
import me.liycxc.gui.management.events.EventTarget;
import me.liycxc.gui.management.events.impl.EventKey;
import me.liycxc.gui.management.mods.ModCategory;

public class HypixelQuickPlayMod extends Mod {

	public HypixelQuickPlayMod() {
		super("Hypixel Quick Play", "Quickly enter Hypixel commands", ModCategory.PLAYER);
	}
	
	@EventTarget
	public void onKey(EventKey event) {
		if(event.getKey() == NekoCat.instance.keyBindManager.QUICKPLAY.getKeyCode()) {
	    	mc.displayGuiScreen(NekoCat.instance.guiManager.getGuiQuickPlay());
		}
	}
}

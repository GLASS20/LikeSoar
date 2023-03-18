package me.soar.management.mods.impl;

import me.soar.Soar;
import me.soar.management.events.EventTarget;
import me.soar.management.events.impl.EventKey;
import me.soar.management.mods.Mod;
import me.soar.management.mods.ModCategory;

public class HypixelQuickPlayMod extends Mod{

	public HypixelQuickPlayMod() {
		super("Hypixel Quick Play", "Quickly enter Hypixel commands", ModCategory.PLAYER);
	}
	
	@EventTarget
	public void onKey(EventKey event) {
		if(event.getKey() == Soar.instance.keyBindManager.QUICKPLAY.getKeyCode()) {
	    	mc.displayGuiScreen(Soar.instance.guiManager.getGuiQuickPlay());
		}
	}
}

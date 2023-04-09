package like.soar.management.mods.impl;

import like.soar.management.events.EventTarget;
import like.soar.management.events.impl.EventKey;
import like.soar.Soar;
import like.soar.management.mods.Mod;
import like.soar.management.mods.ModCategory;

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

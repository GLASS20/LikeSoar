package me.liycxc.gui.management.mods.impl;

import me.liycxc.gui.management.events.EventTarget;
import me.liycxc.gui.management.events.impl.EventRender2D;
import me.liycxc.gui.management.events.impl.EventRenderShadow;
import me.liycxc.gui.management.mods.HudMod;
import me.liycxc.utils.PlayerUtils;

public class PotionCounterMod extends HudMod{

	public PotionCounterMod() {
		super("Potion Counter", "Display the number of potions you have");
	}

	@EventTarget
	public void onRender2D(EventRender2D event) {
		super.onRender2D();
	}
	
	@EventTarget
	public void onRenderShadow(EventRenderShadow event) {
		super.onRenderShadow();
	}
	
	@Override
	public String getText() {
		return PlayerUtils.getPotionsFromInventory() + " pots";
	}
}

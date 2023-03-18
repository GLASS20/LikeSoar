package me.soar.management.mods.impl;

import me.soar.management.events.EventTarget;
import me.soar.management.events.impl.EventRender2D;
import me.soar.management.events.impl.EventRenderShadow;
import me.soar.management.mods.HudMod;
import me.soar.utils.PlayerUtils;

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

package like.soar.management.mods.impl;

import like.soar.management.events.EventTarget;
import like.soar.management.events.impl.EventRender2D;
import like.soar.management.events.impl.EventRenderShadow;
import like.soar.management.mods.HudMod;

public class SaturationMod extends HudMod{

	public SaturationMod() {
		super("Saturation", "Display player saturation");
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
		return String.format("%.0f", mc.thePlayer.getFoodStats().getSaturationLevel());
	}
}

package like.soar.management.mods.impl;

import like.soar.management.events.EventTarget;
import like.soar.management.events.impl.EventRender2D;
import like.soar.management.events.impl.EventRenderShadow;
import like.soar.management.mods.HudMod;

public class WeatherDisplayMod extends HudMod{

	public WeatherDisplayMod() {
		super("Weather Display", "Display current weather");
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
		
		String weather = "Clear";
		
		if(mc.theWorld.isRaining()) {
			weather = "Raining";
		}
		
		if(mc.theWorld.isThundering()) {
			weather = "Thundering";
		}
		
		return "Weather: " + weather;
	}
}

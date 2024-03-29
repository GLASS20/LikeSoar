package me.liycxc.pvp.management.mods.impl;

import me.liycxc.api.events.EventTarget;
import me.liycxc.api.events.impl.EventRender2D;
import me.liycxc.api.events.impl.EventRenderShadow;
import me.liycxc.pvp.management.mods.HudMod;

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

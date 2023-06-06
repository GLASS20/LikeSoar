package me.liycxc.pvp.management.mods.impl;

import me.liycxc.api.events.EventTarget;
import me.liycxc.api.events.impl.EventRender2D;
import me.liycxc.api.events.impl.EventRenderShadow;
import me.liycxc.pvp.management.mods.HudMod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TimeDisplayMod extends HudMod{

	public TimeDisplayMod() {
		super("Time Display", "Display current time");
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
		
        Calendar c = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("HH:mm a", Locale.US);
        String time = df.format(c.getTime());
        
        return time;
	}
}

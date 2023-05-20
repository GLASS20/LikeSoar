package me.liycxc.pvp.management.mods.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.EventRender2D;
import me.liycxc.events.impl.EventRenderShadow;
import me.liycxc.pvp.management.mods.HudMod;

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

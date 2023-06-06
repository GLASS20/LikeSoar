package me.liycxc.pvp.management.mods.impl;

import me.liycxc.NekoCat;
import me.liycxc.api.events.EventTarget;
import me.liycxc.api.events.impl.EventRender2D;
import me.liycxc.api.events.impl.EventRenderShadow;
import me.liycxc.pvp.management.mods.HudMod;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class HealthDisplayMod extends HudMod{

	private static final DecimalFormat healthFormat = new DecimalFormat("0.0");
	
	public HealthDisplayMod() {
		super("Health Display", "Display your health");
	}

	@Override
	public void setup() {
		
		ArrayList<String> options = new ArrayList<String>();
		
		options.add("Health");
		options.add("HP");
		
		this.addModeSetting("Type", this, "Health", options);
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
		
		String mode = NekoCat.instance.settingsManager.getSettingByName(this, "Type").getValString();
		
		return mode + ": " + healthFormat.format(mc.thePlayer.getHealth());
	}
}

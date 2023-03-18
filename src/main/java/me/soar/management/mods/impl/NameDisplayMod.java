package me.soar.management.mods.impl;

import java.util.ArrayList;

import me.soar.Soar;
import me.soar.management.events.EventTarget;
import me.soar.management.events.impl.EventRender2D;
import me.soar.management.events.impl.EventRenderShadow;
import me.soar.management.mods.HudMod;

public class NameDisplayMod extends HudMod{

	public NameDisplayMod() {
		super("Name Display", "Display your Minecraft account name");
	}

	@Override
	public void setup() {
		
		ArrayList<String> options = new ArrayList<String>();
		
		options.add("Name");
		options.add("IGN");
		
		this.addModeSetting("Type", this, "IGN", options);
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
		
		String mode = Soar.instance.settingsManager.getSettingByName(this, "Type").getValString();
		
		return mode + ": " + mc.getSession().getUsername();
	}
}

package me.soar.management.mods.impl;

import me.soar.management.events.EventTarget;
import me.soar.management.events.impl.EventRender2D;
import me.soar.management.events.impl.EventRenderShadow;
import me.soar.management.mods.HudMod;
import me.soar.utils.server.ServerUtils;

public class PingDisplayMod extends HudMod{

	public PingDisplayMod() {
		super("Ping Display", "Display your ping");
	}

	@EventTarget
	public void onRender2D(EventRender2D event) {
		super.onRender2D();
	}
	
	@EventTarget
	public void onRenderShdow(EventRenderShadow event) {
		super.onRenderShadow();
	}
	
	@Override
	public String getText() {
		return ServerUtils.getPing() + " ms";
	}
}

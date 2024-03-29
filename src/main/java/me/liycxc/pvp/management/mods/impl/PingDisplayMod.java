package me.liycxc.pvp.management.mods.impl;

import me.liycxc.api.events.EventTarget;
import me.liycxc.api.events.impl.EventRender2D;
import me.liycxc.api.events.impl.EventRenderShadow;
import me.liycxc.pvp.management.mods.HudMod;
import me.liycxc.utils.server.ServerUtils;

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

package me.soar.management.mods.impl;

import me.soar.management.events.EventTarget;
import me.soar.management.events.impl.EventRender2D;
import me.soar.management.events.impl.EventRenderShadow;
import me.soar.management.mods.HudMod;
import me.soar.utils.server.ServerUtils;

public class ServerIPDisplayMod extends HudMod{

	public ServerIPDisplayMod() {
		super("ServerIP Display", "Display server ip you have joined");
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
		return ServerUtils.getServerIP();
	}
}

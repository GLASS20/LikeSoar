package me.liycxc.gui.management.mods.impl;

import me.liycxc.gui.management.events.EventTarget;
import me.liycxc.gui.management.events.impl.EventRender2D;
import me.liycxc.gui.management.events.impl.EventRenderShadow;
import me.liycxc.gui.management.mods.HudMod;
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

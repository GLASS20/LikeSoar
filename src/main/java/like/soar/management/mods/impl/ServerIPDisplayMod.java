package like.soar.management.mods.impl;

import like.soar.management.events.EventTarget;
import like.soar.management.events.impl.EventRender2D;
import like.soar.management.events.impl.EventRenderShadow;
import like.soar.management.mods.HudMod;
import like.soar.utils.server.ServerUtils;

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

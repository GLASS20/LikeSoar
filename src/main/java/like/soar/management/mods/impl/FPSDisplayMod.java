package like.soar.management.mods.impl;

import like.soar.management.events.EventTarget;
import like.soar.management.events.impl.EventRender2D;
import like.soar.management.events.impl.EventRenderShadow;
import like.soar.management.mods.HudMod;
import net.minecraft.client.Minecraft;

public class FPSDisplayMod extends HudMod{

	public FPSDisplayMod() {
		super("FPS Display", "Display your Minecraft FPS");
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
		return Minecraft.getDebugFPS() + " FPS";
	}
}

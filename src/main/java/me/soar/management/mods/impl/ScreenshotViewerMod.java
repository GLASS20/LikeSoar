package me.soar.management.mods.impl;

import me.soar.Soar;
import me.soar.management.events.EventTarget;
import me.soar.management.events.impl.EventKey;
import me.soar.management.mods.Mod;
import me.soar.management.mods.ModCategory;

public class ScreenshotViewerMod extends Mod{

	public ScreenshotViewerMod() {
		super("Screenshot Viewer", "View a list of screenshots", ModCategory.RENDER);
	}
	
	@EventTarget
	public void onKey(EventKey event) {
		if(event.getKey() == Soar.instance.keyBindManager.SCREENSHOT_VIEWER.getKeyCode()) {
			mc.displayGuiScreen(Soar.instance.guiManager.getGuiScreenshotViewer());
		}
	}
}

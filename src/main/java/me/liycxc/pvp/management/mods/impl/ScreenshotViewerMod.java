package me.liycxc.pvp.management.mods.impl;

import me.liycxc.pvp.management.mods.Mod;
import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.EventKey;
import me.liycxc.NekoCat;
import me.liycxc.pvp.management.mods.ModCategory;

public class ScreenshotViewerMod extends Mod {

	public ScreenshotViewerMod() {
		super("Screenshot Viewer", "View a list of screenshots", ModCategory.RENDER);
	}
	
	@EventTarget
	public void onKey(EventKey event) {
		if(event.getKey() == NekoCat.instance.keyBindManager.SCREENSHOT_VIEWER.getKeyCode()) {
			mc.displayGuiScreen(NekoCat.instance.guiManager.getGuiScreenshotViewer());
		}
	}
}

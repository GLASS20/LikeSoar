package like.soar.management.mods.impl;

import like.soar.management.events.EventTarget;
import like.soar.management.events.impl.EventKey;
import like.soar.Soar;
import like.soar.management.mods.Mod;
import like.soar.management.mods.ModCategory;

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

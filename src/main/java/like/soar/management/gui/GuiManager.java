package like.soar.management.gui;

import like.soar.gui.GuiQuickPlay;
import like.soar.gui.clickgui.ClickGUI;
import like.soar.gui.mainmenu.GuiSoarMainMenu;
import like.soar.gui.screenshot.GuiScreenshotViewer;

public class GuiManager {

	private ClickGUI clickGUI;
	private GuiSoarMainMenu guiMainMenu;
	private GuiQuickPlay guiQuickPlay;
	private GuiScreenshotViewer guiScreenshotViewer;
	
	public GuiManager() {
		clickGUI = new ClickGUI();
		guiMainMenu = new GuiSoarMainMenu();
		guiQuickPlay = new GuiQuickPlay();
		guiScreenshotViewer = new GuiScreenshotViewer();
	}

	public ClickGUI getClickGUI() {
		return clickGUI;
	}

	public GuiSoarMainMenu getGuiMainMenu() {
		return guiMainMenu;
	}

	public GuiQuickPlay getGuiQuickPlay() {
		return guiQuickPlay;
	}

	public GuiScreenshotViewer getGuiScreenshotViewer() {
		return guiScreenshotViewer;
	}
}

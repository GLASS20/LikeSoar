package me.liycxc.pvp.management.gui;

import me.liycxc.pvp.GuiQuickPlay;
import me.liycxc.pvp.clickgui.ClickGUI;
import me.liycxc.pvp.mainmenu.GuiSoarMainMenu;
import me.liycxc.pvp.screenshot.GuiScreenshotViewer;
import me.liycxc.ui.clickgui.ClickGui;

public class GuiManager {

	private ClickGUI clickGUI;
	private ClickGui gclickGUI;
	private GuiSoarMainMenu guiMainMenu;
	private GuiQuickPlay guiQuickPlay;
	private GuiScreenshotViewer guiScreenshotViewer;
	
	public GuiManager() {
		clickGUI = new ClickGUI();
		gclickGUI = new ClickGui();
		guiMainMenu = new GuiSoarMainMenu();
		guiQuickPlay = new GuiQuickPlay();
		guiScreenshotViewer = new GuiScreenshotViewer();
	}

	public ClickGUI getClickGUI() {
		return clickGUI;
	}

	public ClickGui getgClickGUI() {
		return gclickGUI;
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

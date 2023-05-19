package me.liycxc.gui.management.mods.impl;

import me.liycxc.NekoCat;
import me.liycxc.gui.management.events.EventTarget;
import me.liycxc.gui.management.events.impl.EventKey;
import me.liycxc.gui.management.mods.Mod;
import me.liycxc.gui.management.mods.ModCategory;

public class ClickGUIMod extends Mod {

	public ClickGUIMod() {
		super("ClickGUI", "Show client settings", ModCategory.OTHER);
	}

	@Override
	public void setup() {
		this.setHide(true);
		this.setToggled(true);
	}
	
	@EventTarget
	public void onKey(EventKey event) {
		if(event.getKey() == NekoCat.instance.keyBindManager.CLICKGUI.getKeyCode()) {
	    	mc.displayGuiScreen(NekoCat.instance.guiManager.getClickGUI());
		}
		if(event.getKey() == NekoCat.instance.keyBindManager.HACKCLICKGUI.getKeyCode()) {
			mc.displayGuiScreen(NekoCat.instance.guiManager.getgClickGUI());
		}
	}
	
    @Override
    public void onDisable() {
        super.onEnable();
        this.setToggled(true);
    }
}

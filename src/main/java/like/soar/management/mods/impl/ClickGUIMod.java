package like.soar.management.mods.impl;

import like.soar.management.events.EventTarget;
import like.soar.management.events.impl.EventKey;
import like.soar.Soar;
import like.soar.management.mods.Mod;
import like.soar.management.mods.ModCategory;

public class ClickGUIMod extends Mod{

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
		if(event.getKey() == Soar.instance.keyBindManager.CLICKGUI.getKeyCode()) {
	    	mc.displayGuiScreen(Soar.instance.guiManager.getClickGUI());
		}
	}
	
    @Override
    public void onDisable() {
        super.onEnable();
        this.setToggled(true);
    }
}

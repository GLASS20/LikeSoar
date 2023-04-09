package like.soar.management.mods.impl;

import like.soar.management.events.EventTarget;
import like.soar.management.events.impl.EventText;
import like.soar.management.mods.Mod;
import like.soar.management.mods.ModCategory;

public class NameProtectMod extends Mod{

	public NameProtectMod() {
		super("Name Protect", "Protect your name", ModCategory.RENDER);
	}

	@EventTarget
	public void onText(EventText event) {
		event.replace(mc.getSession().getUsername(), "You");
	}
}

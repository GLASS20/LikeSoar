package like.soar.management.mods.impl;

import like.soar.management.mods.Mod;
import like.soar.management.mods.ModCategory;
import like.soar.utils.ClientUtils;

public class HitDelayFixMod extends Mod{

	public HitDelayFixMod() {
		super("Hit Delay Fix", "Fix delay which is triggered every time you click without attacking a player mob", ModCategory.PLAYER);
	}

	@Override
	public void onEnable() {
		super.onEnable();
		ClientUtils.showNotification("Warning", "This mod is banned on some servers");
	}
}

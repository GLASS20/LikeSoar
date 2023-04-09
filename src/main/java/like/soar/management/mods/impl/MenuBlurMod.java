package like.soar.management.mods.impl;

import like.soar.Soar;
import like.soar.management.mods.Mod;
import like.soar.management.mods.ModCategory;
import like.soar.management.settings.Setting;

public class MenuBlurMod extends Mod{

	public MenuBlurMod() {
		super("Menu Blur", "Blur the menu", ModCategory.RENDER);
	}

	@Override
	public void setup() {
		Soar.instance.settingsManager.addSetting(new Setting("Radius", this, 20, 1, 40, true));
	}
}

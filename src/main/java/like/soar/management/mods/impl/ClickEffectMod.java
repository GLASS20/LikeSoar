package like.soar.management.mods.impl;

import like.soar.management.mods.Mod;
import like.soar.management.mods.ModCategory;

public class ClickEffectMod extends Mod{

	public ClickEffectMod() {
		super("Click Effect", "Show effects when clicked", ModCategory.OTHER);
	}

	@Override
	public void setup() {
		this.addBooleanSetting("Accent Color", this, true);
	}
}

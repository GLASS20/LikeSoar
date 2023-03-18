package me.soar.management.mods.impl;

import me.soar.management.events.EventTarget;
import me.soar.management.events.impl.EventUpdate;
import me.soar.management.mods.Mod;
import me.soar.management.mods.ModCategory;
import net.minecraft.client.settings.KeyBinding;

public class SprintMod extends Mod{

	public SprintMod() {
		super("Sprint", "Automatically Sprint", ModCategory.PLAYER);
	}

	@EventTarget
	public void onUpdate(EventUpdate event) {
		KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), true);
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), false);
	}
}

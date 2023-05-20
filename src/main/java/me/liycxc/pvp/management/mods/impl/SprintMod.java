package me.liycxc.pvp.management.mods.impl;

import me.liycxc.pvp.management.mods.Mod;
import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.EventUpdate;
import me.liycxc.pvp.management.mods.ModCategory;
import net.minecraft.client.settings.KeyBinding;

public class SprintMod extends Mod {

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

package me.liycxc.gui.management.mods.impl;

import me.liycxc.NekoCat;
import me.liycxc.gui.management.events.EventTarget;
import me.liycxc.gui.management.events.impl.EventCameraRotation;
import me.liycxc.gui.management.mods.Mod;
import me.liycxc.gui.management.mods.ModCategory;
import net.minecraft.client.renderer.GlStateManager;

public class FarCameraMod extends Mod {

	public FarCameraMod() {
		super("Far Camera", "Move the camera away", ModCategory.OTHER);
	}

	@Override
	public void setup() {
		this.addSliderSetting("Range", this, 20, 1, 50, true);
	}
	
	@EventTarget
	public void onCameraRotation(EventCameraRotation event) {
		
		int range = NekoCat.instance.settingsManager.getSettingByName(this, "Range").getValInt();
		
		if(mc.gameSettings.thirdPersonView == 1) {
			GlStateManager.translate(0.0F, 0.0F, -range);
		}
	}
}

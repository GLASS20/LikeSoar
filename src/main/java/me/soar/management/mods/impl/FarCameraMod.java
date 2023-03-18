package me.soar.management.mods.impl;

import me.soar.Soar;
import me.soar.management.events.EventTarget;
import me.soar.management.events.impl.EventCameraRotation;
import me.soar.management.mods.Mod;
import me.soar.management.mods.ModCategory;
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
		
		int range = Soar.instance.settingsManager.getSettingByName(this, "Range").getValInt();
		
		if(mc.gameSettings.thirdPersonView == 1) {
			GlStateManager.translate(0.0F, 0.0F, -range);
		}
	}
}

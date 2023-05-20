package me.liycxc.pvp.management.mods.impl;

import me.liycxc.NekoCat;
import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.EventRenderCrosshair;
import me.liycxc.pvp.management.mods.Mod;
import me.liycxc.pvp.management.mods.ModCategory;
import me.liycxc.utils.GlUtils;
import me.liycxc.utils.render.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class CrosshairMod extends Mod {

	public CrosshairMod() {
		super("Crosshair", "Customize crosshair", ModCategory.RENDER);
	}
	
	@Override
	public void setup() {
		
		ArrayList<String> options = new ArrayList<>();
		
		options.add("1");
		options.add("2");
		options.add("3");
		options.add("4");
		options.add("5");
		
		this.addBooleanSetting("Hide ThirdPersonView", this, false);
		this.addBooleanSetting("Custom Crosshair", this, false);
		this.addModeSetting("Type", this, "1", options);
		this.addSliderSetting("Scale", this, 1, 0.3, 2, false);
	}
	
	@EventTarget
	public void onRenderCrosshair(EventRenderCrosshair event) {
		
		String mode = NekoCat.instance.settingsManager.getSettingByName(this, "Type").getValString();
		
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        
        if((NekoCat.instance.settingsManager.getSettingByName(this, "Hide ThirdPersonView").getValBoolean() && mc.gameSettings.thirdPersonView != 0)) {
        	event.setCancelled(true);
        }
        
        if(NekoCat.instance.settingsManager.getSettingByName(this, "Custom Crosshair").getValBoolean()) {
        	event.setCancelled(true);
        	if((!NekoCat.instance.settingsManager.getSettingByName(this, "Hide ThirdPersonView").getValBoolean()) || (NekoCat.instance.settingsManager.getSettingByName(this, "Hide ThirdPersonView").getValBoolean() && mc.gameSettings.thirdPersonView == 0)) {
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(775, 769, 1, 0);
                GlStateManager.enableAlpha();
                GlStateManager.enableDepth();
                
            	mc.getTextureManager().bindTexture(new ResourceLocation("soar/mods/crosshair/type" + mode + ".png"));
            	GlUtils.startScale(sr.getScaledWidth() / 2 - 7, sr.getScaledHeight() / 2 - 7, 16, 16, (float) NekoCat.instance.settingsManager.getSettingByName(this, "Scale").getValDouble());
            	RenderUtils.drawTexturedModalRect(sr.getScaledWidth() / 2 - 7, sr.getScaledHeight() / 2 - 7, 0, 0, 16, 16);
            	GlUtils.stopScale();
        	}
        }
	}
}
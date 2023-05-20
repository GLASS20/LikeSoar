package me.liycxc.pvp.management.mods.impl;

import me.liycxc.NekoCat;
import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.EventRender2D;
import me.liycxc.events.impl.EventRenderShadow;
import me.liycxc.pvp.management.mods.Mod;
import me.liycxc.pvp.management.mods.ModCategory;
import me.liycxc.utils.color.ColorUtils;
import me.liycxc.utils.font.FontUtils;
import me.liycxc.utils.render.RenderUtils;
import me.liycxc.utils.render.RoundedUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.ArrayList;

public class ClientInfoMod extends Mod {

	public ClientInfoMod() {
		super("Client Info", "Display Client Infomation", ModCategory.HUD);
	}

	@Override
	public void setup() {
		
		ArrayList<String> options = new ArrayList<String>();
		
		options.add("Text");
		options.add("Chill");
		options.add("Icon");
		
		this.addModeSetting("Design", this, "Text", options);
		this.addBooleanSetting("Accent Color", this, false);
		this.addBooleanSetting("Background", this, true);
		this.addSliderSetting("Opacity", this, 0.8, 0, 1, false);
	}
	
	@EventTarget
	public void onRender2D(EventRender2D event) {
		
		String mode = NekoCat.instance.settingsManager.getSettingByName(this, "Design").getValString();
		float opacity = (float) NekoCat.instance.settingsManager.getSettingByName(this, "Opacity").getValDouble();
		
		switch(mode) {
			case "Text":
				FontUtils.regular_bold36.drawStringWithClientColor(NekoCat.instance.getName(), this.getX(), this.getY(), true);
				
				this.setWidth((int) FontUtils.regular_bold36.getStringWidth(NekoCat.instance.getName()));
				this.setHeight((int) FontUtils.regular_bold36.getHeight());
				break;
			case "Chill":
				String text = NekoCat.instance.getName() + " | " + Minecraft.getDebugFPS() + "fps | " + mc.thePlayer.getName();
				
				RenderUtils.drawRect(this.getX(), this.getY(), (float) (FontUtils.regular20.getStringWidth(text) + 8), (float) (FontUtils.regular20.getHeight() + 8), new Color(92, 92, 92).getRGB());
				RenderUtils.drawRect(this.getX() + 2, this.getY() + 2, (float) (FontUtils.regular20.getStringWidth(text) + 4), (float) (FontUtils.regular20.getHeight() + 4), new Color(52, 52, 52).getRGB());
				
				FontUtils.regular20.drawString(text, this.getX() + 4, this.getY() + 5, -1);
				
				this.setWidth((int) (FontUtils.regular20.getStringWidth(text)) + 8);
				this.setHeight((int) (8 + (FontUtils.regular20.getHeight())));
				break;
			case "Icon":
				int size = 64;
				Color color = NekoCat.instance.settingsManager.getSettingByName(this, "Accent Color").getValBoolean() ? ColorUtils.getClientColor(0) : Color.WHITE;
				
				if(NekoCat.instance.settingsManager.getSettingByName(this, "Background").getValBoolean()) {
					RoundedUtils.drawGradientRound(this.getX() + 9, this.getY() + 9, 64 - 18, 64 - 18, 23, ColorUtils.getClientColor(0, (int) (opacity * 255)), ColorUtils.getClientColor(90, (int) (opacity * 255)), ColorUtils.getClientColor(180, (int) (opacity * 255)), ColorUtils.getClientColor(270, (int) (opacity * 255)));
				}
				
				GlStateManager.enableBlend();			
				mc.getTextureManager().bindTexture(new ResourceLocation("soar/icon.png"));

				GlStateManager.color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, opacity);
				RenderUtils.drawScaledCustomSizeModalRect(this.getX(), this.getY(), size, size, size, size, size, size, size, size);
				GlStateManager.disableBlend();
				
				this.setWidth(64);
				this.setHeight(64);
				break;
		}
	}
	
	@EventTarget
	public void onRenderShadow(EventRenderShadow event) {
		
		String mode = NekoCat.instance.settingsManager.getSettingByName(this, "Design").getValString();
		
		if(mode.equals("Chill")) {
			RenderUtils.drawRect(this.getX(), this.getY(), this.getWidth() - this.getX(), this.getHeight() - this.getY(), new Color(0, 0, 0).getRGB());
		}
		
		if(mode.equals("Icon")) {
			RoundedUtils.drawRound(this.getX() + 9, this.getY() + 9, 64 - 18, 64 - 18, 23, Color.BLACK);
		}
	}
}

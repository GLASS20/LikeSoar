package me.liycxc.pvp.management.mods.impl;

import me.liycxc.NekoCat;
import me.liycxc.api.events.EventTarget;
import me.liycxc.api.events.impl.EventRender2D;
import me.liycxc.api.events.impl.EventRenderShadow;
import me.liycxc.pvp.management.mods.Mod;
import me.liycxc.pvp.management.mods.ModCategory;
import me.liycxc.utils.GlUtils;
import me.liycxc.utils.animation.simple.SimpleAnimation;
import me.liycxc.utils.color.ColorUtils;
import me.liycxc.utils.font.FontUtils;
import me.liycxc.utils.render.RoundedUtils;
import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class KeystrokesMod extends Mod {

	private SimpleAnimation wAnimation = new SimpleAnimation(0.0F), aAnimation = new SimpleAnimation(0.0F), sAnimation = new SimpleAnimation(0.0F), dAnimation = new SimpleAnimation(0.0F), spaceAnimation = new SimpleAnimation(0.0F);
	private SimpleAnimation wOpacityAnimation = new SimpleAnimation(0.0F), aOpacityAnimation = new SimpleAnimation(0.0F), sOpacityAnimation = new SimpleAnimation(0.0F), dOpacityAnimation = new SimpleAnimation(0.0F), spaceOpacityAnimation = new SimpleAnimation(0.0F);
	
	public KeystrokesMod() {
		super("Keystrokes", "Display pressed keys", ModCategory.HUD);
	}
	
	@Override
	public void setup() {
		this.addBooleanSetting("Space", this, true);
	}
	
	@EventTarget
	public void onRender2D(EventRender2D event) {
		
		boolean isIngame = !(mc.currentScreen instanceof Gui);
		boolean wKey = isIngame && Keyboard.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode());
		boolean aKey = isIngame && Keyboard.isKeyDown(mc.gameSettings.keyBindLeft.getKeyCode());
		boolean sKey = isIngame && Keyboard.isKeyDown(mc.gameSettings.keyBindBack.getKeyCode());
		boolean dKey = isIngame && Keyboard.isKeyDown(mc.gameSettings.keyBindRight.getKeyCode());
		boolean spaceKey = isIngame && Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode());
		int radius = (int) NekoCat.instance.settingsManager.getSettingByClass(HUDMod.class, "Radius").getValDouble();

		//W
		this.drawBackground(this.getX() + 32, this.getY(), 28, 28);

		wAnimation.setAnimation(wKey ? 1.0F : 0, 14);
		wOpacityAnimation.setAnimation(wKey ? ColorUtils.getClientOpacity() : 0, 14);
		
		GlUtils.startScale((this.getX() + 32) / 2 + (this.getX() + 32 + 28) / 2, (this.getY()) / 2 + (this.getY() + 28) / 2, wAnimation.getValue());
		
		RoundedUtils.drawGradientRound(this.getX() + 32, this.getY(), 28, 28, radius, new Color(220, 220, 220, (int) wOpacityAnimation.getValue()), new Color(220, 220, 220, (int) wOpacityAnimation.getValue()), new Color(220, 220, 220, (int) wOpacityAnimation.getValue()), new Color(220, 220, 220, (int) wOpacityAnimation.getValue()));

		GlUtils.stopScale();
		
		FontUtils.regular_bold26.drawString("W", this.getX() + 39, this.getY() + 8, this.getFontColor().getRGB());
		
		//A
		this.drawBackground(this.getX(), this.getY() + 32, 28, 28);
		
		aAnimation.setAnimation(aKey ? 1.0F : 0, 14);
		aOpacityAnimation.setAnimation(aKey ? ColorUtils.getClientOpacity() : 0, 14);
		
		GlUtils.startScale((this.getX()) / 2 + (this.getX() + 28) / 2, (this.getY() + 32) / 2 + (this.getY() + 32 + 28) / 2, aAnimation.getValue());
		
		RoundedUtils.drawGradientRound(this.getX(), this.getY() + 32, 28, 28, radius, new Color(220, 220, 220, (int) aOpacityAnimation.getValue()), new Color(220, 220, 220, (int) aOpacityAnimation.getValue()), new Color(220, 220, 220, (int) aOpacityAnimation.getValue()), new Color(220, 220, 220, (int) aOpacityAnimation.getValue()));
		
		GlUtils.stopScale();
		
		FontUtils.regular_bold26.drawString("A", this.getX() + 10, this.getY() + 40, this.getFontColor().getRGB());
		
		//S
		this.drawBackground(this.getX() + 32, this.getY() + 32, 28, 28);
		
		sAnimation.setAnimation(sKey ? 1.0F : 0, 14);
		sOpacityAnimation.setAnimation(sKey ? ColorUtils.getClientOpacity() : 0, 14);
		
		GlUtils.startScale((this.getX() + 32) / 2 + (this.getX() + 32 + 28) / 2, (this.getY() + 32) / 2 + (this.getY() + 32 + 28) / 2, sAnimation.getValue());
		
		RoundedUtils.drawGradientRound(this.getX() + 32, this.getY() + 32, 28, 28, radius, new Color(220, 220, 220, (int) sOpacityAnimation.getValue()), new Color(220, 220, 220, (int) sOpacityAnimation.getValue()), new Color(220, 220, 220, (int) sOpacityAnimation.getValue()), new Color(220, 220, 220, (int) sOpacityAnimation.getValue()));

		GlUtils.stopScale();
		
		FontUtils.regular_bold26.drawString("S", this.getX() + 42, this.getY() + 40, this.getFontColor().getRGB());
		
		//D
		this.drawBackground(this.getX() + 64, this.getY() + 32, 28, 28);
		
		dAnimation.setAnimation(dKey ? 1.0F : 0, 14);
		dOpacityAnimation.setAnimation(dKey ? ColorUtils.getClientOpacity() : 0, 14);
		
		GlUtils.startScale((this.getX() + 64) / 2 + (this.getX() + 64 + 28) / 2, (this.getY() + 32) / 2 + (this.getY() + 32 + 28) / 2, dAnimation.getValue());
		
		RoundedUtils.drawGradientRound(this.getX() + 64, this.getY() + 32, 28, 28, radius, new Color(220, 220, 220, (int) dOpacityAnimation.getValue()), new Color(220, 220, 220, (int) dOpacityAnimation.getValue()), new Color(220, 220, 220, (int) dOpacityAnimation.getValue()), new Color(220, 220, 220, (int) dOpacityAnimation.getValue()));

		GlUtils.stopScale();
		
		FontUtils.regular_bold26.drawString("D", this.getX() + 74, this.getY() + 40, this.getFontColor().getRGB());

		if(NekoCat.instance.settingsManager.getSettingByName(this, "Space").getValBoolean()) {
			
			//Space
			this.drawBackground(this.getX(), this.getY() + 64, 92, 23);
			
			spaceAnimation.setAnimation(spaceKey ? 1.0F : 0, 14);
			spaceOpacityAnimation.setAnimation(spaceKey ? ColorUtils.getClientOpacity() : 0, 14);
			
			GlUtils.startScale((this.getX()) / 2 + (this.getX() + 92) / 2, (this.getY() + 64) / 2 + (this.getY() + 64 + 28) / 2, spaceAnimation.getValue());
			
			RoundedUtils.drawGradientRound(this.getX(), this.getY() + 64, 92, 23, radius, new Color(220, 220, 220, (int) spaceOpacityAnimation.getValue()), new Color(220, 220, 220, (int) spaceOpacityAnimation.getValue()), new Color(220, 220, 220, (int) spaceOpacityAnimation.getValue()), new Color(220, 220, 220, (int) spaceOpacityAnimation.getValue()));

			GlUtils.stopScale();
			
			RoundedUtils.drawRound(this.getX() + 15, this.getY() + 75, 62, 2, 1, this.getFontColor());
		}
		
		this.setWidth(92);
		this.setHeight(NekoCat.instance.settingsManager.getSettingByName(this, "Space").getValBoolean() ? 87 : 60);
	}
	
	@EventTarget
	public void onRenderShadow(EventRenderShadow event) {
		this.drawShadow(this.getX() + 32, this.getY(), 28, 28);
		this.drawShadow(this.getX(), this.getY() + 32, 28, 28);
		this.drawShadow(this.getX() + 32, this.getY() + 32, 28, 28);
		this.drawShadow(this.getX() + 64, this.getY() + 32, 28, 28);
		
		if(NekoCat.instance.settingsManager.getSettingByName(this, "Space").getValBoolean()) {
			this.drawShadow(this.getX(), this.getY() + 64, 92, 23);
		}
	}

}

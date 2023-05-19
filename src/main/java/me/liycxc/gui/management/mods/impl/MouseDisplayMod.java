package me.liycxc.gui.management.mods.impl;

import me.liycxc.gui.management.mods.Mod;
import me.liycxc.gui.management.events.EventTarget;
import me.liycxc.gui.management.events.impl.EventRender2D;
import me.liycxc.gui.management.events.impl.EventRenderShadow;
import me.liycxc.gui.management.events.impl.EventUpdate;
import org.lwjgl.input.Mouse;

import me.liycxc.gui.management.mods.ModCategory;
import me.liycxc.utils.TimerUtils;
import me.liycxc.utils.animation.simple.SimpleAnimation;
import me.liycxc.utils.render.RoundedUtils;

public class MouseDisplayMod extends Mod {

	private float prevX;
	private float prevY;
	private float lastX;
	private float lastY;
	
	private TimerUtils timer = new TimerUtils();
	
	private SimpleAnimation yawAnimation = new SimpleAnimation(0.0F);
	private SimpleAnimation pitchAnimation = new SimpleAnimation(0.0F);
	
	public MouseDisplayMod() {
		super("Mouse Display", "Display mouse movement", ModCategory.HUD);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate event) {
		
		int mouseX = Mouse.getX();
		int mouseY = -Mouse.getY();
		
		if(timer.delay(150)) {
			prevX = mouseX;
			prevY = mouseY;
			timer.reset();
		}
		
		lastX = mouseX - prevX;
		lastY = mouseY - prevY;
	}
	
	@EventTarget
	public void onRender2D(EventRender2D event) {
		
		this.drawBackground(this.getX(), this.getY(), 50, 50);
		
		if(lastX > 15) {
			lastX = 15;
		}
		
		if(lastX < -15) {
			lastX = -15;
		}
		
		if(lastY > 15) {
			lastY = 15;
		}
		
		if(lastY < -15) {
			lastY = -15;
		}
		
		yawAnimation.setAnimation(lastX, 100);
		pitchAnimation.setAnimation(lastY, 100);
		
		RoundedUtils.drawRound(this.getX() + 21 + yawAnimation.getValue(), this.getY() + 21 + pitchAnimation.getValue(), 8, 8, 4, this.getFontColor());
		
		this.setWidth(50);
		this.setHeight(50);
	}
	
	@EventTarget
	public void onRenderShadow(EventRenderShadow event) {
		this.drawShadow(this.getX(), this.getY(), this.getWidth() - this.getX(), this.getHeight() - this.getY());
	}
}

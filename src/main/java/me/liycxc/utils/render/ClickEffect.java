package me.liycxc.utils.render;

import me.liycxc.NekoCat;
import me.liycxc.pvp.management.mods.impl.ClickEffectMod;
import me.liycxc.utils.animation.simple.SimpleAnimation;
import me.liycxc.utils.color.ColorUtils;

import java.awt.*;

public class ClickEffect {
	
	private float x, y;
	
	private SimpleAnimation animation = new SimpleAnimation(0.0F);
	
	public ClickEffect(float x, float y) {
		this.x = x;
		this.y = y;
		animation.setValue(0);
	}
	
	public void draw() {
		animation.setAnimation(100, 12);
        double radius = 8 * animation.getValue() / 100;
        int alpha = (int)(255 - 255 * animation.getValue() / 100);
        boolean accentColor = NekoCat.instance.settingsManager.getSettingByClass(ClickEffectMod.class, "Accent Color").getValBoolean();
        int color = accentColor ? ColorUtils.getClientColor(0, alpha).getRGB() : new Color(255, 255, 255, alpha).getRGB();
        
        if(NekoCat.instance.modManager.getModByClass(ClickEffectMod.class).isToggled()) {
            RenderUtils.drawArc(x, y, radius, color, 0, 360, 5);
        }
	}
	
	public boolean canRemove() {
		return animation.getValue() > 99;
	}
}

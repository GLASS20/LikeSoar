package me.liycxc.ui.clickgui.comp.impl;


import me.liycxc.modules.BoolValue;
import me.liycxc.modules.Module;
import me.liycxc.ui.clickgui.comp.Comp;
import me.liycxc.ui.clickgui.impl.FeatureCategory;
import me.liycxc.utils.GlUtils;
import me.liycxc.utils.Logger;
import me.liycxc.utils.animation.simple.SimpleAnimation;
import me.liycxc.utils.color.ColorUtils;
import me.liycxc.utils.font.FontUtils;
import me.liycxc.utils.mouse.MouseUtils;
import me.liycxc.utils.render.RoundedUtils;

import java.awt.*;

public class CompBoolean extends Comp {

	private SimpleAnimation animation = new SimpleAnimation(0.0F);
	private SimpleAnimation animation2 = new SimpleAnimation(0.0F);
	
    public CompBoolean(double x, double y, FeatureCategory parent, Module mod, BoolValue setting) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.mod = mod;
        this.setting = setting;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
    	animation.setAnimation((boolean) setting.get() ? 1 : 0, 10);
    	animation2.setAnimation((boolean) setting.get() ? 255 : 0, 12);
    	
    	RoundedUtils.drawRound((float) (parent.getX() + x - 70), (float) (parent.getY() + y), 10, 10, 3, ColorUtils.getBackgroundColor(2));
    	
    	GlUtils.startScale((float) (parent.getX() + x - 70 + parent.getX() + x - 70 + 10) / 2, (float) (parent.getY() + y + parent.getY() + y + 10) / 2, animation.getValue());
    	RoundedUtils.drawRound((float) (parent.getX() + x - 70), (float) (parent.getY() + y), 10, 10, 3, ColorUtils.getClientColor(0, (int) animation2.getValue()));
        FontUtils.icon20.drawString("H", (float) (parent.getX() + x - 70), (float) (parent.getY() + y + 3), new Color(255, 255, 255).getRGB());
        GlUtils.stopScale();

        FontUtils.regular20.drawString(setting.getName(), (float) (parent.getX() + x - 55), (float) (parent.getY() + y + 2), ColorUtils.getFontColor(2).getRGB());
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if(MouseUtils.isInside(mouseX, mouseY, parent.getX() + x - 70, parent.getY() + y + FeatureCategory.scrollVAnimation.getValue(), 10, 10) && mouseButton == 0) {
            setting.set(!(boolean)setting.get());
            Logger.log("parent " + parent.getName());
            parent.isHitd = true;
        }
    }
}

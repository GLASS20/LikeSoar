package me.liycxc.pvp.clickgui.comp.impl;

import me.liycxc.pvp.clickgui.category.impl.FeatureCategory;
import me.liycxc.pvp.clickgui.comp.Comp;
import me.liycxc.pvp.management.mods.Mod;
import me.liycxc.pvp.management.settings.Setting;
import me.liycxc.utils.GlUtils;
import me.liycxc.utils.animation.simple.SimpleAnimation;
import me.liycxc.utils.color.ColorUtils;
import me.liycxc.utils.font.FontUtils;
import me.liycxc.utils.mouse.MouseUtils;
import me.liycxc.utils.render.RoundedUtils;

import java.awt.*;

public class CompCheckBox extends Comp {

	private SimpleAnimation animation = new SimpleAnimation(0.0F);
	private SimpleAnimation animation2 = new SimpleAnimation(0.0F);
	
    public CompCheckBox(float x, float y, FeatureCategory parent, Mod mod, Setting setting) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.mod = mod;
        this.setting = setting;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
    	
    	animation.setAnimation(setting.getValBoolean() ? 1 : 0, 10);
    	animation2.setAnimation(setting.getValBoolean() ? 255 : 0, 12);
    	
    	RoundedUtils.drawRound((float) (parent.getX() + x - 70), (float) (parent.getY() + y), 10, 10, 3, ColorUtils.getBackgroundColor(2));
    	
    	GlUtils.startScale((float) (parent.getX() + x - 70 + parent.getX() + x - 70 + 10) / 2, (float) (parent.getY() + y + parent.getY() + y + 10) / 2, animation.getValue());
    	RoundedUtils.drawRound((float) (parent.getX() + x - 70), (float) (parent.getY() + y), 10, 10, 3, ColorUtils.getClientColor(0, (int) animation2.getValue()));
    	FontUtils.icon20.drawString("H", (parent.getX() + x - 70), (parent.getY() + y + 3), new Color(255, 255, 255).getRGB());
    	GlUtils.stopScale();
    	
        FontUtils.regular20.drawString(setting.getName(), (int)(parent.getX() + x - 55), (parent.getY() + y + 2), ColorUtils.getFontColor(2).getRGB());
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (MouseUtils.isInside(mouseX, mouseY, parent.getX() + x - 70, parent.getY() + y, 10, 10) && mouseButton == 0) {
            setting.setValBoolean(!setting.getValBoolean());
        }
    }

}

package me.liycxc.ui.clickgui.comp.impl;

import me.liycxc.modules.IntegerValue;
import me.liycxc.modules.Module;
import me.liycxc.ui.clickgui.comp.Comp;
import me.liycxc.ui.clickgui.impl.FeatureCategory;
import me.liycxc.utils.animation.simple.SimpleAnimation;
import me.liycxc.utils.color.ColorUtils;
import me.liycxc.utils.font.FontUtils;
import me.liycxc.utils.mouse.MouseUtils;
import me.liycxc.utils.render.RoundedUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CompInt extends Comp {

    private boolean dragging = false;
    private double renderWidth;
    private double renderWidth2;
    private SimpleAnimation animation = new SimpleAnimation(0.0F);

    public CompInt(double x, double y, FeatureCategory parent, Module mod, IntegerValue setting) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.mod = mod;
        this.setting = setting;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
        super.drawScreen(mouseX, mouseY);

        int min = ((IntegerValue)setting).getMinimum();
        int max = ((IntegerValue)setting).getMaximum();
        double l = 90;

        renderWidth = (l) * (((IntegerValue) setting).get() - min) / (max - min);
        renderWidth2 = (l) * (((IntegerValue)setting).getMaximum() - min) / (max - min);

        animation.setAnimation((float) renderWidth, 14);
        
        double diff = Math.min(l, Math.max(0, mouseX - (parent.getX() + x - 70)));
        
        if (dragging) {
            if (diff == 0) {
                setting.set(((IntegerValue)setting).getMinimum());
            }
            else {
                double newValue = roundToPlace(((diff / l) * (max - min) + min), 2);
                ((IntegerValue) setting).set(newValue);
            }
        }
        
        RoundedUtils.drawRound((float) (parent.getX() + x - 70), (float) (parent.getY() + y + 13), (float) (renderWidth2), 6, 3, ColorUtils.getBackgroundColor(2));
        RoundedUtils.drawGradientRoundLR((float) (parent.getX() + x - 70), (float) (parent.getY() + y + 13), animation.getValue(), 6, 3, ColorUtils.getClientColor(0), ColorUtils.getClientColor(20));
        RoundedUtils.drawRound((float) (parent.getX() + x) + animation.getValue() - 72, (float) (parent.getY() + y + 10), 5, 12, 2, ColorUtils.getBackgroundColor(1));

        FontUtils.regular20.drawString(setting.getName() + ": " + setting.get(),(int)(parent.getX() + x - 70),(int)(parent.getY() + y), ColorUtils.getFontColor(2).getRGB());
    }
    
    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (MouseUtils.isInside(mouseX, mouseY, parent.getX() + x - 70, parent.getY() + y + 10 + FeatureCategory.scrollVAnimation.getValue(),renderWidth2 + 3, 10) && mouseButton == 0) {
            dragging = true;
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        dragging = false;
    }

    private double roundToPlace(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
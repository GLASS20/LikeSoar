package me.glass20.clickgui.comp.impl;

import like.soar.utils.animation.simple.SimpleAnimation;
import like.soar.utils.color.ColorUtils;
import like.soar.utils.font.FontUtils;
import like.soar.utils.mouse.MouseUtils;
import like.soar.utils.render.RoundedUtils;
import me.glass20.FloatValue;
import me.glass20.clickgui.comp.Comp;
import me.glass20.clickgui.impl.FeatureCategory;
import me.glass20.modules.Module;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CompFloat extends Comp {

    private boolean dragging = false;
    private double renderWidth;
    private double renderWidth2;
    private SimpleAnimation animation = new SimpleAnimation(0.0F);
    
    public CompFloat(double x, double y, FeatureCategory parent, Module mod, FloatValue setting) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.mod = mod;
        this.setting = setting;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
        super.drawScreen(mouseX, mouseY);

        double min = ((FloatValue)setting).getMinimum();
        double max = ((FloatValue)setting).getMaximum();
        double l = 90;

        renderWidth = (l) * (((FloatValue) setting).get() - min) / (max - min);
        renderWidth2 = (l) * (((FloatValue)setting).getMaximum() - min) / (max - min);

        animation.setAnimation((float) renderWidth, 14);
        
        double diff = Math.min(l, Math.max(0, mouseX - (parent.getX() + x - 70)));
        
        if (dragging) {
            if (diff == 0) {
                setting.set(((FloatValue)setting).getMinimum());
            }
            else {
                double newValue = roundToPlace(((diff / l) * (max - min) + min), 2);
                ((FloatValue) setting).set(newValue);
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

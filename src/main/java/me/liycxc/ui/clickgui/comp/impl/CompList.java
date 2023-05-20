package me.liycxc.ui.clickgui.comp.impl;

import me.liycxc.modules.ListValue;
import me.liycxc.modules.Module;
import me.liycxc.ui.clickgui.comp.Comp;
import me.liycxc.ui.clickgui.impl.FeatureCategory;
import me.liycxc.utils.color.ColorUtils;
import me.liycxc.utils.font.FontUtils;
import me.liycxc.utils.mouse.MouseUtils;
import me.liycxc.utils.render.RoundedUtils;

public class CompList extends Comp {

    public CompList(double x, double y, FeatureCategory parent, Module mod, ListValue setting) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.mod = mod;
        this.setting = setting;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
        super.drawScreen(mouseX, mouseY);

        RoundedUtils.drawRound((float) (parent.getX() + x - 70), (float) (parent.getY() + y), (float) FontUtils.regular20.getStringWidth(setting.getName() + ": " + setting.get()) + 5, 11, 3, ColorUtils.getBackgroundColor(2));
        FontUtils.regular20.drawString(setting.getName() + ": " + setting.get(), (int)(parent.getX() + x - 68), (int)(parent.getY() + y + 2), ColorUtils.getFontColor(2).getRGB());
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (MouseUtils.isInside(mouseX, mouseY, parent.getX() + x - 70, parent.getY() + y + FeatureCategory.scrollVAnimation.getValue(), 70, 10) && mouseButton == 0) {
            int max = ((ListValue) setting).getValues().length;
            if (parent.modeIndex + 1 >= max) {
                parent.modeIndex = 0;
            } else {
                parent.modeIndex++;
            }
            setting.set((((ListValue) setting).getValues())[parent.modeIndex]);
            parent.isHitd = true;
        }
    }
}
package me.soar.gui.clickgui.comp;

import me.soar.gui.clickgui.category.impl.FeatureCategory;
import me.soar.management.mods.Mod;
import me.soar.management.settings.Setting;

public class Comp {

    public double x, y, width, height;
    public FeatureCategory parent;
    public Mod mod;
    public Setting setting;

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {}

    public void mouseReleased(int mouseX, int mouseY, int state) {}

    public void drawScreen(int mouseX, int mouseY) {}

    public void keyTyped(char typedChar, int keyCode) {}

}

package me.liycxc.gui.clickgui.comp;

import me.liycxc.gui.clickgui.category.impl.FeatureCategory;
import me.liycxc.gui.management.mods.Mod;
import me.liycxc.gui.management.settings.Setting;

public class Comp {

    public float x, y, width, height;
    public FeatureCategory parent;
    public Mod mod;
    public Setting setting;

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {}

    public void mouseReleased(int mouseX, int mouseY, int state) {}

    public void drawScreen(int mouseX, int mouseY) {}

    public void keyTyped(char typedChar, int keyCode) {}

}

package me.liycxc.ui.clickgui.comp;

import me.liycxc.api.Value;
import me.liycxc.modules.Module;
import me.liycxc.ui.clickgui.impl.FeatureCategory;

public class Comp {

    public double x, y, width, height;
    public FeatureCategory parent;
    public Module mod;
    public Value setting;

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {}

    public void mouseReleased(int mouseX, int mouseY, int state) {}

    public void drawScreen(int mouseX, int mouseY) {}

    public void keyTyped(char typedChar, int keyCode) {}
}
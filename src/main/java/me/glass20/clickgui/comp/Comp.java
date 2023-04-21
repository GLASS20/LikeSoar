package me.glass20.clickgui.comp;

import me.glass20.Value;
import me.glass20.clickgui.impl.FeatureCategory;
import me.glass20.modules.Module;

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
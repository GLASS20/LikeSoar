package like.soar.gui.clickgui.comp;

import like.soar.gui.clickgui.category.impl.FeatureCategory;
import like.soar.management.settings.Setting;
import like.soar.management.mods.Mod;

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

package me.glass20.clickgui;

import like.soar.Soar;
import like.soar.utils.animation.simple.SimpleAnimation;
import net.minecraft.client.Minecraft;

public class Category {

    public Minecraft mc = Minecraft.getMinecraft();
    private String name;
    public SimpleAnimation introAnimation = new SimpleAnimation(0.0F);
    public SimpleAnimation fontAnimation[] = {
            new SimpleAnimation(0.0F), new SimpleAnimation(0.0F), new SimpleAnimation(0.0F)
    };

    public Category(String name) {
        this.name = name;
    }

    public void initGui() {}

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {}

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {}

    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {}

    public void updateScreen() {}

    public void keyTyped(char typedChar, int keyCode) {}

    public String getName() {
        return name;
    }

    public float getX() {
        return Soar.instance.guiManager.getgClickGUI().getX();
    }

    public float getY() {
        return Soar.instance.guiManager.getgClickGUI().getY();
    }

    public float getWidth() {
        return Soar.instance.guiManager.getgClickGUI().getWidth();
    }

    public float getHeight() {
        return Soar.instance.guiManager.getgClickGUI().getHeight();
    }
}

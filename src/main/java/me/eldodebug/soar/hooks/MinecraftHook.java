package me.eldodebug.soar.hooks;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import me.eldodebug.soar.Soar;
import me.eldodebug.soar.management.mods.impl.BorderlessFullscreenMod;
import net.minecraft.client.Minecraft;

import static net.minecraft.client.Minecraft.displayFixCancel;

public class MinecraftHook {

    
    public static void displayFix(boolean fullscreen, int displayWidth, int displayHeight) throws LWJGLException {
        Display.setFullscreen(false);
        if (fullscreen) {
            if (Soar.instance.modManager != null && Soar.instance.modManager.getModByClass(BorderlessFullscreenMod.class).isToggled()) {
                System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
            } else {
                Display.setFullscreen(true);
                DisplayMode displaymode = Display.getDisplayMode();
                Minecraft.getMinecraft().displayWidth = Math.max(1, displaymode.getWidth());
                Minecraft.getMinecraft().displayHeight = Math.max(1, displaymode.getHeight());
            }
        } else {
            if (Soar.instance.modManager != null && Soar.instance.modManager.getModByClass(BorderlessFullscreenMod.class).isToggled()) {
                System.setProperty("org.lwjgl.opengl.Window.undecorated", "false");
            } else {
                Display.setDisplayMode(new DisplayMode(displayWidth, displayHeight));
            }
        }

        Display.setResizable(false);
        Display.setResizable(true);

        displayFixCancel = true;
    }
    
    public static void fullScreenFix(boolean fullscreen, int displayWidth, int displayHeight) throws LWJGLException {
        if (Soar.instance.modManager != null && Soar.instance.modManager.getModByClass(BorderlessFullscreenMod.class).isToggled()) {
            if (fullscreen) {
                System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
                Display.setDisplayMode(Display.getDesktopDisplayMode());
                Display.setLocation(0, 0);
                Display.setFullscreen(false);
            } else {
                System.setProperty("org.lwjgl.opengl.Window.undecorated", "false");
                Display.setDisplayMode(new DisplayMode(displayWidth, displayHeight));
            }
        } else {
            Display.setFullscreen(fullscreen);
            System.setProperty("org.lwjgl.opengl.Window.undecorated", "false");
        }

        Display.setResizable(false);
        Display.setResizable(true);
    }
}

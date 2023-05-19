package me.liycxc.gui.management.mods.impl;

import java.awt.Color;
import java.io.IOException;

import me.liycxc.gui.management.mods.Mod;
import me.liycxc.gui.management.events.EventTarget;
import me.liycxc.gui.management.events.impl.EventRender2D;
import me.liycxc.gui.management.events.impl.EventRenderShadow;
import org.lwjgl.opengl.GL11;

import me.liycxc.gui.management.mods.ModCategory;
import me.liycxc.ui.minimap.XaeroMinimap;
import me.liycxc.ui.minimap.animation.MinimapAnimation;
import me.liycxc.ui.minimap.interfaces.InterfaceHandler;
import me.liycxc.utils.ClientUtils;
import me.liycxc.utils.render.RenderUtils;
import net.minecraft.client.Minecraft;

public class MinimapMod extends Mod {

	private XaeroMinimap minimap = new XaeroMinimap();
	
	public MinimapMod() {
		super("Minimap", "Display minimap", ModCategory.HUD);
	}

	@EventTarget
	public void onRender2D(EventRender2D event) {
        Minecraft.getMinecraft().entityRenderer.setupOverlayRendering();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        InterfaceHandler.drawInterfaces((Minecraft.getMinecraft()).getTimer().renderPartialTicks);
        MinimapAnimation.tick();
        
        this.setWidth(75);
        this.setHeight(75);
	}
	
	@EventTarget
	public void onRenderShadow(EventRenderShadow event) {
		RenderUtils.drawRect(this.getX(), this.getY(), 75, 75, Color.BLACK.getRGB());
	}
	
	@Override
	public void onEnable() {
		
		super.onEnable();
		
		if(!ClientUtils.loadedMinimap) {
			
			ClientUtils.loadedMinimap = true;
			
			try {
				minimap.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

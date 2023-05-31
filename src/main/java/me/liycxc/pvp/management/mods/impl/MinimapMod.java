package me.liycxc.pvp.management.mods.impl;

import me.liycxc.NekoCat;
import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.EventRender2D;
import me.liycxc.events.impl.EventRenderShadow;
import me.liycxc.pvp.management.mods.Mod;
import me.liycxc.pvp.management.mods.ModCategory;
import me.liycxc.ui.minimap.XaeroMinimap;
import me.liycxc.ui.minimap.animation.MinimapAnimation;
import me.liycxc.ui.minimap.interfaces.InterfaceHandler;
import me.liycxc.utils.ClientUtils;
import me.liycxc.utils.render.RenderUtils;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;

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
				// Don't give crackers clues...
				if (NekoCat.instance.DEVELOPMENT_SWITCH)
					e.printStackTrace();
			}
		}
	}
}

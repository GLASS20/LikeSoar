package like.soar.management.mods.impl;

import java.awt.Color;
import java.io.IOException;

import like.soar.management.events.EventTarget;
import like.soar.management.events.impl.EventRender2D;
import like.soar.management.events.impl.EventRenderShadow;
import org.lwjgl.opengl.GL11;

import like.soar.management.mods.Mod;
import like.soar.management.mods.ModCategory;
import like.soar.ui.minimap.XaeroMinimap;
import like.soar.ui.minimap.animation.MinimapAnimation;
import like.soar.ui.minimap.interfaces.InterfaceHandler;
import like.soar.utils.ClientUtils;
import like.soar.utils.render.RenderUtils;
import net.minecraft.client.Minecraft;

public class MinimapMod extends Mod{

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

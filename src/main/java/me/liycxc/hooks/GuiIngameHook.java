package me.liycxc.hooks;

import me.liycxc.NekoCat;
import me.liycxc.events.impl.EventRender2D;
import me.liycxc.events.impl.EventRenderDamageTint;
import me.liycxc.events.impl.EventRenderShadow;
import me.liycxc.pvp.GuiEditHUD;
import me.liycxc.pvp.management.mods.impl.HUDMod;
import me.liycxc.pvp.management.mods.impl.MenuBlurMod;
import me.liycxc.ui.notification.NotificationManager;
import me.liycxc.utils.GlUtils;
import me.liycxc.utils.animation.simple.SimpleAnimation;
import me.liycxc.utils.color.ColorUtils;
import me.liycxc.utils.render.RoundedUtils;
import me.liycxc.utils.render.StencilUtils;
import me.liycxc.utils.shader.BlurUtils;
import me.liycxc.utils.shader.GaussianBlur;
import me.liycxc.utils.shader.ShadowUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.shader.Framebuffer;

public class GuiIngameHook {

	private static Framebuffer shadowFramebuffer = new Framebuffer(1, 1, false);
	private static SimpleAnimation opacityAnimation = new SimpleAnimation(0.0F);
	
	public static void renderGameOverlay(float partialTicks) {
		
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		
		opacityAnimation.setAnimation(Minecraft.getMinecraft().currentScreen instanceof GuiEditHUD ? 220 : 0, 16);
		
		RoundedUtils.drawRound(0, sr.getScaledHeight() / 2, sr.getScaledWidth(), 1, 0, ColorUtils.getBackgroundColor(4, (int) opacityAnimation.getValue()));
		RoundedUtils.drawRound(sr.getScaledWidth() / 2, 0, 1, sr.getScaledHeight(), 0, ColorUtils.getBackgroundColor(4, (int) opacityAnimation.getValue()));
		
		EventRenderDamageTint event3 = new EventRenderDamageTint();
		event3.call();
		
		if((!NekoCat.instance.settingsManager.getSettingByClass(HUDMod.class, "Hide Debug Menu").getValBoolean()) || (NekoCat.instance.settingsManager.getSettingByClass(HUDMod.class, "Hide Debug Menu").getValBoolean() && !Minecraft.getMinecraft().gameSettings.showDebugInfo)) {
			EventRender2D event = new EventRender2D(partialTicks);
			EventRenderShadow event2 = new EventRenderShadow(partialTicks);
			
	        shadowFramebuffer = GlUtils.createFrameBuffer(shadowFramebuffer);

	        shadowFramebuffer.framebufferClear();
	        shadowFramebuffer.bindFramebuffer(true);
	        event2.call();
	        shadowFramebuffer.unbindFramebuffer();

	        ShadowUtils.renderShadow(shadowFramebuffer.framebufferTexture, 8, 2);
	        
	        StencilUtils.initStencilToWrite();
	        if(NekoCat.instance.settingsManager.getSettingByClass(HUDMod.class, "Blur").getValBoolean()) {
	            event2.call();
	        }
	        
	        StencilUtils.readStencilBuffer(1);
	        GaussianBlur.renderBlur(NekoCat.instance.settingsManager.getSettingByClass(HUDMod.class, "Blur Radius").getValInt());
	        StencilUtils.uninitStencilBuffer();
	        
	        event.call();
		}
		
		if(Minecraft.getMinecraft().currentScreen instanceof Gui) {
	        if(NekoCat.instance.modManager.getModByClass(MenuBlurMod.class).isToggled()) {
	        	BlurUtils.drawBlurScreen();
	        }	
		}
		
		NotificationManager.render();
        
        GlStateManager.enableBlend();
	}
}

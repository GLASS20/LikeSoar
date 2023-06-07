package me.liycxc.ui.notification;

import me.liycxc.NekoCat;
import me.liycxc.api.events.EventTarget;
import me.liycxc.api.events.impl.EventRenderShadow;
import me.liycxc.utils.TimerUtils;
import me.liycxc.utils.animation.normal.Animation;
import me.liycxc.utils.animation.normal.Direction;
import me.liycxc.utils.animation.normal.impl.DecelerateAnimation;
import me.liycxc.utils.color.ColorUtils;
import me.liycxc.utils.font.FontUtils;
import me.liycxc.utils.render.RoundedUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;

public class Notification {
	
    private String title;
    private String message;

    private Animation animation;
    private final TimerUtils timer = new TimerUtils();
    
    private boolean loaded = false;
    
    public void setNotification(String title, String message) {
    	this.title = title;
    	this.message = message;
    }

    public void show() {
        animation = new DecelerateAnimation(250, FontUtils.regular_bold20.getStringWidth(message) + 13);
    	timer.reset();
    }

    public boolean isShown() {
        return !animation.isDone(Direction.BACKWARDS);
    }

    public void render() {
    	
    	ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
    	
    	if(NekoCat.instance.eventManager != null) {
            if(!loaded) {
            	loaded = true;
            	NekoCat.instance.eventManager.register(this);
            }
    	}
    	
        double offset = animation.getValue();

        if(timer.delay(3000)) {
        	animation.setDirection(Direction.BACKWARDS);
        }
        
    	RoundedUtils.drawGradientRound((float) (sr.getScaledWidth() - offset), sr.getScaledHeight() - 40, (float) (FontUtils.regular_bold20.getStringWidth(message) + 2), 29, 6, ColorUtils.getBackgroundColor(4), ColorUtils.getBackgroundColor(4), ColorUtils.getBackgroundColor(4), ColorUtils.getBackgroundColor(4));
        FontUtils.regular_bold26.drawString(title, (float) (sr.getScaledWidth() - offset + 3), sr.getScaledHeight() - 38, ColorUtils.getFontColor(2).getRGB());
        
        FontUtils.regular20.drawString(message, (float) (sr.getScaledWidth() - offset + 3), sr.getScaledHeight() - 22, ColorUtils.getFontColor(2).getRGB());
    }
    
    @EventTarget
    public void onRenderShadow(EventRenderShadow event) {
    	
    	ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
    	
    	if(animation != null) {
            double offset = animation.getValue();
    		RoundedUtils.drawRound((float) (sr.getScaledWidth() - offset), sr.getScaledHeight() - 40, (float) (FontUtils.regular_bold20.getStringWidth(message) + 2), 29, 6, new Color(0, 0, 0));
    	}
    }
}

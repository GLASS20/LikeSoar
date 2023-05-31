package me.liycxc.pvp.management.mods.impl;

import me.liycxc.NekoCat;
import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.EventRender2D;
import me.liycxc.pvp.management.image.Image;
import me.liycxc.pvp.management.mods.Mod;
import me.liycxc.pvp.management.mods.ModCategory;
import me.liycxc.utils.GlUtils;
import me.liycxc.utils.render.RenderUtils;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageDisplayMod extends Mod {

    private ResourceLocation image;
    private int imageWidth, imageHeight;
    private Image prevImage;
    
	public ImageDisplayMod() {
		super("Image Display", "Display image", ModCategory.HUD);
	}
	
	@Override
	public void setup() {
		ArrayList<String> options = new ArrayList<String>();
		
		if(NekoCat.instance.imageManager.getImages().isEmpty()) {
			options.add("None");
		}else {
			for(Image i : NekoCat.instance.imageManager.getImages()) {
				options.add(i.getName());
			}
		}
		
		this.addModeSetting("Image", this, "None", options);
		this.addSliderSetting("Opacity", this, 1.0, 0.1, 1.0, false);
		this.addSliderSetting("Scale", this, 1.0, 0.1, 3.0, false);
	}
	
	@EventTarget
	public void onRender2D(EventRender2D event) {
		
		String mode = NekoCat.instance.settingsManager.getSettingByName(this, "Image").getValString();
		float opacity = NekoCat.instance.settingsManager.getSettingByName(this, "Opacity").getValFloat();
		float scale = NekoCat.instance.settingsManager.getSettingByName(this, "Scale").getValFloat();
		
		if(mode.equals("None")) {
			return;
		}
		
		NekoCat.instance.imageManager.setCurrentImage(NekoCat.instance.imageManager.getImageByName(mode));
		
		if(prevImage != NekoCat.instance.imageManager.getCurrentImage()) {
			prevImage = NekoCat.instance.imageManager.getCurrentImage();
	        try {
	            BufferedImage t = ImageIO.read(NekoCat.instance.imageManager.getCurrentImage().getFile());
	            DynamicTexture nibt = new DynamicTexture(t);

	            imageWidth = t.getWidth();
	            imageHeight = t.getHeight();
	            
	            this.image = mc.getTextureManager().getDynamicTextureLocation("Image", nibt);
	        } catch (Throwable e) {
				// Don't give crackers clues...
				if (NekoCat.instance.DEVELOPMENT_SWITCH)
					e.printStackTrace();
	        }
		}
        
        if(image != null) {
        	GlUtils.startScale(this.getX(), this.getY(), scale);
        	RenderUtils.drawImage(image, this.getX(), this.getY(), imageWidth / 2, imageHeight / 2, opacity);
        	GlUtils.stopScale();
        }
        
        this.setWidth((int) ((imageWidth / 2) * (scale)));
        this.setHeight((int) ((imageHeight / 2) * (scale)));
	}
}

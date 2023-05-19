package me.liycxc.gui.clickgui.category.impl;

import me.liycxc.NekoCat;
import me.liycxc.gui.clickgui.category.Category;
import me.liycxc.gui.management.cosmetics.Cosmetic;
import me.liycxc.utils.animation.simple.SimpleAnimation;
import me.liycxc.utils.color.ColorUtils;
import me.liycxc.utils.font.FontUtils;
import me.liycxc.utils.mouse.MouseUtils;
import me.liycxc.utils.render.RoundedUtils;
import net.minecraft.client.renderer.GlStateManager;

public class CosmeticCategory extends Category {
	
	private double scrollY;
	private SimpleAnimation scrollAnimation = new SimpleAnimation(0.0F);
	private boolean canToggle;
	
	public CosmeticCategory() {
		super("Cosmetic");
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	int index = 0;
    	int index2 = 1;
    	int offsetX = 95;
    	int offsetY = 15;
    	
    	//�R�X�`���[���̕`��
    	for(Cosmetic c : NekoCat.instance.cosmeticManager.getCosmetics()) {
    		
    		//���߂̃A�j���[�V��������
    		c.opacityAnimation.setAnimation(NekoCat.instance.cosmeticManager.getCurrentCpae().equals(c.getName()) ? 255 : 0, 12);
    		
    		//�I�����Ă��邩�̕`��
    		RoundedUtils.drawRound(this.getX() + offsetX - 1, this.getY() + offsetY - 1 + scrollAnimation.getValue(), 60 + 2, 90 + 2, 6, ColorUtils.getClientColor(0, (int) c.opacityAnimation.getValue()));
    		
    		//�w�i�̕`��
        	RoundedUtils.drawRound(this.getX() + offsetX, this.getY() + offsetY + scrollAnimation.getValue(), 60, 90, 6, ColorUtils.getBackgroundColor(4));
        	
        	//�}���g�̃e�N�X�`�����蓖��
        	mc.getTextureManager().bindTexture(c.getSample());
        	
        	//None����Ȃ�������`��
        	if(!c.getName().equals("None")) {
        		GlStateManager.enableBlend();
            	RoundedUtils.drawRoundTextured(this.getX() + offsetX + 8, this.getY() + offsetY + 10 + scrollAnimation.getValue(), 44, 70, 4, 1.0F);
            	GlStateManager.disableBlend();
        	}
        	
    		index++;
    		offsetX+=70;
    		
    		//�\������Ă���̂�3��������Y���W���v���X
    		if(index == index2 * 3) {
    			index2++;
    			offsetX = 95;
    			offsetY+=100;
    		}
    	}
    	
    	//None�̕����`��
    	FontUtils.regular_bold36.drawString("None", this.getX() + 105, this.getY() + 52 + scrollAnimation.getValue(), ColorUtils.getFontColor(2).getRGB());
    	
        //�X�N���[������
        final MouseUtils.Scroll scroll = MouseUtils.scroll();

        if(scroll != null) {
        	switch (scroll) {
        	case DOWN:
        		if(scrollY > -((index2 + 1.9) * 70)) {
            		scrollY -=20;
        		}
        		break;
            case UP:
        		if(scrollY < -10) {
            		scrollY +=20;
        		}else {
            		if(index2 > 5) {
            			scrollY = 0;
            		}
        		}
        		break;
        	}
        }
        
        scrollAnimation.setAnimation((float) scrollY, 16);
        
        if(MouseUtils.isInside(mouseX, mouseY, this.getX(), this.getY(), this.getWidth(), this.getHeight())) {
        	canToggle = true;
        }else{
        	canToggle = false;
        }
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
    	int index = 0;
    	int index2 = 1;
    	int offsetX = 95;
    	int offsetY = 15;
    	
    	//�R�X�`���[���̏���
    	for(Cosmetic c : NekoCat.instance.cosmeticManager.getCosmetics()) {
    		
    		if(MouseUtils.isInside(mouseX, mouseY, this.getX() + offsetX, this.getY() + offsetY + scrollAnimation.getValue(), 60, 90) && canToggle) {
    			NekoCat.instance.cosmeticManager.setCurrentCpae(c.getName());
    		}
    		
    		index++;
    		offsetX+=70;
    		
    		//�\������Ă���̂�3��������Y���W���v���X
    		if(index == index2 * 3) {
    			index2++;
    			offsetX = 95;
    			offsetY+=100;
    		}
    	}
	}
}

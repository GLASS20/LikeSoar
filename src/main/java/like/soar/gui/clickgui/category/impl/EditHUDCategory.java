package like.soar.gui.clickgui.category.impl;

import like.soar.utils.animation.normal.Direction;
import like.soar.utils.color.ColorUtils;
import like.soar.utils.font.FontUtils;
import like.soar.utils.mouse.MouseUtils;
import like.soar.utils.render.RoundedUtils;
import like.soar.Soar;
import like.soar.gui.GuiEditHUD;
import like.soar.gui.clickgui.category.Category;

public class EditHUDCategory extends Category{

	private boolean edithud;
	
	public EditHUDCategory() {
		super("Edit HUD");
	}
	
	@Override
	public void initGui() {
		edithud = false;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	FontUtils.regular24.drawString("Can move the HUD on the screen", this.getX() + 95, this.getY() + 12, ColorUtils.getFontColor(1).getRGB());
    	
    	RoundedUtils.drawRound((float) this.getX() + 95, (float) this.getY() + 30, (float) 200, (float) 90, 6, ColorUtils.getBackgroundColor(4));
    	FontUtils.regular24.drawCenteredString("Click this to move", (float) (this.getX() + 95 + this.getX() + 95 + 200) / 2 - 1, (float) (this.getY() + 30 + this.getY() + 30 + 90) / 2 - 5, ColorUtils.getFontColor(2).getRGB());
    	
    	if(Soar.instance.guiManager.getClickGUI().introAnimation.isDone(Direction.BACKWARDS) && edithud == true) {
        	mc.displayGuiScreen(new GuiEditHUD(true));
    	}
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if(Soar.instance.guiManager.getClickGUI().selectedCategory.equals(Soar.instance.guiManager.getClickGUI().categoryManager.getCategoryByClass(EditHUDCategory.class)) && MouseUtils.isInside(mouseX, mouseY, this.getX() + 95, this.getY() + 30, 200, 90) && mouseButton == 0) {
        	Soar.instance.guiManager.getClickGUI().introAnimation.setDirection(Direction.BACKWARDS);
        	edithud = true;
        }
	}
}

package me.liycxc.gui.clickgui.category.impl;

import me.liycxc.NekoCat;
import me.liycxc.gui.clickgui.ClickGUI;
import me.liycxc.gui.clickgui.category.Category;
import me.liycxc.gui.clickgui.comp.Comp;
import me.liycxc.gui.clickgui.comp.impl.CompCheckBox;
import me.liycxc.gui.clickgui.comp.impl.CompCombo;
import me.liycxc.gui.clickgui.comp.impl.CompSlider;
import me.liycxc.gui.management.mods.Mod;
import me.liycxc.gui.management.mods.ModCategory;
import me.liycxc.gui.management.settings.Setting;
import me.liycxc.utils.GlUtils;
import me.liycxc.utils.animation.normal.Animation;
import me.liycxc.utils.animation.normal.Direction;
import me.liycxc.utils.animation.normal.impl.EaseInOutQuad;
import me.liycxc.utils.animation.simple.SimpleAnimation;
import me.liycxc.utils.color.ColorUtils;
import me.liycxc.utils.font.FontUtils;
import me.liycxc.utils.mouse.MouseUtils;
import me.liycxc.utils.render.RoundedUtils;
import me.liycxc.utils.render.StencilUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class FeatureCategory extends Category {

	public static double scrollY;
	public static SimpleAnimation scrollAnimation = new SimpleAnimation(0.0F);
	public static boolean openModSetting;
	private Mod selectedMod;
	public static Animation openSettingAnimation;

	private boolean canToggle;
	private ArrayList<Comp> comps = new ArrayList<>();
	public int modeIndex;

	public FeatureCategory(String name) {
		super(name);
	}

	public FeatureCategory() {
		super("Feature");
	}

	@Override
	public void initGui() {
		openModSetting = false;
		for(Mod m : NekoCat.instance.modManager.getMods()) {
			m.selectTimer.reset();
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		int offset = 15;
		int modIndex = 1;
		ClickGUI clickGUI = NekoCat.instance.guiManager.getClickGUI();

		GlUtils.startTranslate(openModSetting ? (float) -openSettingAnimation.getValue() : 0, 0);

		for(Mod m : NekoCat.instance.modManager.getMods()) {

			if(!m.isHide() && !m.getCategory().equals(ModCategory.HACK)) {
				if(clickGUI.searchMode ? (StringUtils.containsIgnoreCase(m.getName(), clickGUI.searchWord.getText()) || StringUtils.containsIgnoreCase(m.getDescription(), clickGUI.searchWord.getText())) : true) {
					RoundedUtils.drawRound(this.getX() + 95, this.getY() + offset + scrollAnimation.getValue(), 200, 26, 6, ColorUtils.getBackgroundColor(4));
					FontUtils.regular20.drawString(m.getName(), this.getX() + 105, this.getY() + 10.5F + offset + scrollAnimation.getValue(), ColorUtils.getFontColor(2).getRGB());

					m.buttonAnimation.setAnimation(m.isToggled() ? 14 : 0, 12);
					m.buttonOpacityAnimation.setAnimation(m.isToggled() ? 255 : 0, 12);

					RoundedUtils.drawRound(this.getX() + 242, this.getY() + offset + scrollAnimation.getValue() + 7F, 28, 12.5F, 6, ColorUtils.getBackgroundColor(1));

					RoundedUtils.drawGradientRoundLR(this.getX() + 242, this.getY() + offset + scrollAnimation.getValue() + 7F, 28, 12.5F, 6, ColorUtils.getClientColor(0, (int) m.buttonOpacityAnimation.getValue()), ColorUtils.getClientColor(90, (int) m.buttonOpacityAnimation.getValue()));

					RoundedUtils.drawRound(this.getX() + 244 + m.buttonAnimation.getValue(), this.getY() + offset + 7F + scrollAnimation.getValue() + 1.3F, 10, 10, 5, ColorUtils.getBackgroundColor(4));

					if (NekoCat.instance.settingsManager.getSettingsByMod(m) != null) {
						FontUtils.icon20.drawString("A", this.getX() + 278, this.getY() + offset + 11F + scrollAnimation.getValue(), ColorUtils.getFontColor(2).getRGB());
					}

					offset+=35;
					modIndex++;
				}
			}

			if(NekoCat.instance.guiManager.getClickGUI().close) {
				m.selectTimer.reset();
			}
		}

		GlUtils.stopTranslate();

		final MouseUtils.Scroll scroll = MouseUtils.scroll();

		if(scroll != null && openModSetting == false) {
			switch (scroll) {
				case DOWN:
					if(scrollY > -((modIndex - 6.5) * 35)) {
						scrollY -=20;
					}

					if(modIndex > 5) {
						if(scrollY < -((modIndex - 8) * 35)) {
							scrollY = -((modIndex - 7.1) * 35);
						}
					}
					break;
				case UP:
					if(scrollY < -10) {
						scrollY +=20;
					}else {
						if(modIndex > 5) {
							scrollY = 0;
						}
					}
					break;
			}
		}

		scrollAnimation.setAnimation((float) scrollY, 16);

		if(openSettingAnimation != null && selectedMod != null) {
			GlUtils.startTranslate(openModSetting ? (float) -openSettingAnimation.getValue() + 220 : 220, 0);

			RoundedUtils.drawRound(this.getX() + 95, this.getY() + 10, 200, 210, 6, ColorUtils.getBackgroundColor(4));
			FontUtils.regular24.drawString(selectedMod.getName(), this.getX() + 100, this.getY() + 19, ColorUtils.getFontColor(1).getRGB());

			for (Comp comp : comps) {
				comp.drawScreen(mouseX, mouseY);
			}

			if(openSettingAnimation.isDone(Direction.BACKWARDS)) {
				if(openModSetting == true) {
					openModSetting = false;
					comps.clear();
				}
			}

			GlUtils.stopTranslate();
		}

		if(MouseUtils.isInside(mouseX, mouseY, this.getX(), this.getY(), this.getWidth(), this.getHeight())) {
			canToggle = true;
		}else{
			canToggle = false;
		}

		StencilUtils.uninitStencilBuffer();
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {

		int offset = 15;
		ClickGUI clickGUI = NekoCat.instance.guiManager.getClickGUI();

		for(Mod m : NekoCat.instance.modManager.getMods()) {
			if(!m.isHide() && !m.getCategory().equals(ModCategory.HACK)) {
				if(clickGUI.searchMode ? (StringUtils.containsIgnoreCase(m.getName(), clickGUI.searchWord.getText()) || StringUtils.containsIgnoreCase(m.getDescription(), clickGUI.searchWord.getText())) : true) {

					if(MouseUtils.isInside(mouseX, mouseY, this.getX() + 270, this.getY() + offset + scrollAnimation.getValue(), 26, 26) && canToggle && !openModSetting) {
						if (mouseButton == 0) {

							int sOffset = 35;

							comps.clear();

							if (NekoCat.instance.settingsManager.getSettingsByMod(m) != null) {

								for (Setting setting : NekoCat.instance.settingsManager.getSettingsByMod(m)) {

									selectedMod = m;
									openModSetting = true;

									openSettingAnimation = new EaseInOutQuad(300, 220);

									if (setting.isCombo()) {
										comps.add(new CompCombo(175, sOffset, this, selectedMod, setting));
										sOffset += 15;
									}
									if (setting.isCheck()) {
										comps.add(new CompCheckBox(175, sOffset, this, selectedMod, setting));
										sOffset += 15;
									}
									if (setting.isSlider()) {
										comps.add(new CompSlider(175, sOffset, this, selectedMod, setting));
										sOffset += 25;
									}
								}
							}
						}
					}

					if(MouseUtils.isInside(mouseX, mouseY, this.getX() + 95, this.getY() + offset + scrollAnimation.getValue(), 175, 26) && canToggle) {
						if(mouseButton == 0 && !openModSetting) {
							m.toggle();
						}
					}
					offset+=35;
				}
			}
		}

		if(openModSetting) {
			for (Comp comp : comps) {
				comp.mouseClicked(mouseX, mouseY, mouseButton);
			}
		}
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
		for (Comp comp : comps) {
			comp.mouseReleased(mouseX, mouseY, mouseButton);
		}
	}

	@Override
	public void keyTyped(char typedChar, int keyCode) {
		for (Comp comp : comps) {
			comp.keyTyped(typedChar, keyCode);
		}
	}
}

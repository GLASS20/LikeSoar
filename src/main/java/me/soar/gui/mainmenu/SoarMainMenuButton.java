package me.soar.gui.mainmenu;

import me.soar.utils.animation.simple.SimpleAnimation;

public class SoarMainMenuButton {

	private String name;
	public SimpleAnimation opacityAnimation = new SimpleAnimation(0.0F);
	
	public SoarMainMenuButton(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

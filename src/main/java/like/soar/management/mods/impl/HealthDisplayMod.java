package like.soar.management.mods.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;

import like.soar.management.events.EventTarget;
import like.soar.management.events.impl.EventRender2D;
import like.soar.management.events.impl.EventRenderShadow;
import like.soar.Soar;
import like.soar.management.mods.HudMod;

public class HealthDisplayMod extends HudMod{

	private static final DecimalFormat healthFormat = new DecimalFormat("0.0");
	
	public HealthDisplayMod() {
		super("Health Display", "Display your health");
	}

	@Override
	public void setup() {
		
		ArrayList<String> options = new ArrayList<String>();
		
		options.add("Health");
		options.add("HP");
		
		this.addModeSetting("Type", this, "Health", options);
	}
	
	@EventTarget
	public void onRender2D(EventRender2D event) {
		super.onRender2D();
	}
	
	@EventTarget
	public void onRenderShadow(EventRenderShadow event) {
		super.onRenderShadow();
	}
	
	@Override
	public String getText() {
		
		String mode = Soar.instance.settingsManager.getSettingByName(this, "Type").getValString();
		
		return mode + ": " + healthFormat.format(mc.thePlayer.getHealth());
	}
}

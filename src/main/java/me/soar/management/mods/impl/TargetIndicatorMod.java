package me.soar.management.mods.impl;

import me.soar.management.events.EventTarget;
import me.soar.management.events.impl.EventRender3D;
import me.soar.management.mods.Mod;
import me.soar.management.mods.ModCategory;
import me.soar.utils.TargetUtils;
import me.soar.utils.render.RenderUtils;

public class TargetIndicatorMod extends Mod{

	public TargetIndicatorMod() {
		super("Target Indicator", "Indicates the current target", ModCategory.RENDER);
	}

	@EventTarget
	public void onRender3D(EventRender3D event) {
		if(TargetUtils.getTarget() != null) {
			
			if(TargetUtils.getTarget().equals(mc.thePlayer)) {
				return;
			}

			RenderUtils.drawTargetCapsule(TargetUtils.getTarget(), 0.67, true);
		}
	}
}

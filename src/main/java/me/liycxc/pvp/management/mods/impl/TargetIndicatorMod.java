package me.liycxc.pvp.management.mods.impl;

import me.liycxc.api.events.EventTarget;
import me.liycxc.api.events.impl.EventRender3D;
import me.liycxc.pvp.management.mods.Mod;
import me.liycxc.pvp.management.mods.ModCategory;
import me.liycxc.utils.TargetUtils;
import me.liycxc.utils.render.RenderUtils;

public class TargetIndicatorMod extends Mod {

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

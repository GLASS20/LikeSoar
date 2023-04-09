package like.soar.management.mods.impl;

import like.soar.management.events.EventTarget;
import like.soar.management.events.impl.EventRender3D;
import like.soar.management.mods.Mod;
import like.soar.management.mods.ModCategory;
import like.soar.utils.TargetUtils;
import like.soar.utils.render.RenderUtils;

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

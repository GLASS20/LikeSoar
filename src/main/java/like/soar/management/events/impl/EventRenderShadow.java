package like.soar.management.events.impl;

import like.soar.management.events.Event;

public class EventRenderShadow extends Event{

	private float partialTicks;
	
	public EventRenderShadow(float partialTicks) {
		this.partialTicks = partialTicks;
	}

	public float getPartialTicks() {
		return partialTicks;
	}
}

package me.liycxc.api.events.impl;

import me.liycxc.api.events.Event;

public class EventRenderShadow extends Event{

	private float partialTicks;
	
	public EventRenderShadow(float partialTicks) {
		this.partialTicks = partialTicks;
	}

	public float getPartialTicks() {
		return partialTicks;
	}
}

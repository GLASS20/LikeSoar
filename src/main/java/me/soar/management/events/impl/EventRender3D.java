package me.soar.management.events.impl;

import me.soar.management.events.Event;

public class EventRender3D extends Event{

	private float partialTicks;
	
	public EventRender3D(float partialTicks) {
		this.partialTicks = partialTicks;
	}

	public float getPartialTicks() {
		return partialTicks;
	}
}

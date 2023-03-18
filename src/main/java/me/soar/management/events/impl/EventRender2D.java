package me.soar.management.events.impl;

import me.soar.management.events.Event;

public class EventRender2D extends Event{

	private float partialTicks;
	
	public EventRender2D(float partialTicks) {}

	public float getPartialTicks() {
		return partialTicks;
	}

	public void setPartialTicks(float partialTicks) {
		this.partialTicks = partialTicks;
	}
}

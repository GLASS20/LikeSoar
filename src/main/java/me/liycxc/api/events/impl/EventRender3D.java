package me.liycxc.api.events.impl;

import me.liycxc.api.events.Event;

public class EventRender3D extends Event{

	private float partialTicks;
	
	public EventRender3D(float partialTicks) {
		this.partialTicks = partialTicks;
	}

	public float getPartialTicks() {
		return partialTicks;
	}
}

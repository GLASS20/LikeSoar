package me.liycxc.gui.management.events.impl;

import me.liycxc.gui.management.events.Event;

public class EventRender3D extends Event{

	private float partialTicks;
	
	public EventRender3D(float partialTicks) {
		this.partialTicks = partialTicks;
	}

	public float getPartialTicks() {
		return partialTicks;
	}
}
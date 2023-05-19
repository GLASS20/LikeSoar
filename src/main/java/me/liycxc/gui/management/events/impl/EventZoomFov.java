package me.liycxc.gui.management.events.impl;

import me.liycxc.gui.management.events.Event;

public class EventZoomFov extends Event{

	private float fov;
	
	public EventZoomFov(float fov) {
		this.fov = fov;
	}

	public float getFov() {
		return fov;
	}

	public void setFov(float fov) {
		this.fov = fov;
	}
}

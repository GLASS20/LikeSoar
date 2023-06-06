package me.liycxc.api.events.impl;

import me.liycxc.api.events.Event;

public class EventRenderSelectedItem extends Event{

	private int color;
	
	public EventRenderSelectedItem(int color) {
		this.color = color;
	}
	
	public int getColor() {
		return color;
	}
}

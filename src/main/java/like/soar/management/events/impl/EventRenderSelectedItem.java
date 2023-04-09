package like.soar.management.events.impl;

import like.soar.management.events.Event;

public class EventRenderSelectedItem extends Event{

	private int color;
	
	public EventRenderSelectedItem(int color) {
		this.color = color;
	}
	
	public int getColor() {
		return color;
	}
}

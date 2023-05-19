package me.liycxc.gui.management.events.impl;

import me.liycxc.gui.management.events.Event;

public class EventScrollMouse extends Event{

	private int amount;
	
	public EventScrollMouse(int amount) {
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}
}

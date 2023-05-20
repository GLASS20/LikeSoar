package me.liycxc.events.impl;

import me.liycxc.events.Event;

public class EventKey extends Event{
	
	private int key;
	
    public EventKey(int key) {
        this.key = key;
    }

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
}

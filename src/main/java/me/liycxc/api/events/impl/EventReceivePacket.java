package me.liycxc.api.events.impl;

import me.liycxc.api.events.Event;
import net.minecraft.network.Packet;

public class EventReceivePacket extends Event{

	private Packet<?> packet;
	
	public EventReceivePacket(Packet<?> packet) {
		this.packet = packet;
	}

	public Packet<?> getPacket() {
		return packet;
	}

	public void setPacket(Packet<?> packet) {
		this.packet = packet;
	}
}

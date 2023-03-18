package me.soar.management.events.impl;

import me.soar.management.events.Event;
import net.minecraft.entity.Entity;

public class EventDamageEntity extends Event{

	private Entity entity;
	
	public EventDamageEntity(Entity entity) {
		this.entity = entity;
	}

	public Entity getEntity() {
		return entity;
	}
}

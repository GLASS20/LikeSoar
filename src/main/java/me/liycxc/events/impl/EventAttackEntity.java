package me.liycxc.events.impl;

import me.liycxc.events.Event;
import net.minecraft.entity.Entity;

public class EventAttackEntity extends Event{

	private Entity entity;
	
	public EventAttackEntity(Entity entity) {
		this.entity = entity;
	}

	public Entity getEntity() {
		return entity;
	}
}

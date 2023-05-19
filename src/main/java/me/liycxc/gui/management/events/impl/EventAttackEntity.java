package me.liycxc.gui.management.events.impl;

import me.liycxc.gui.management.events.Event;
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

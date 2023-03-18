package me.soar.management.events.impl;

import me.soar.management.events.Event;
import net.minecraft.entity.EntityLivingBase;

public class EventLivingUpdate extends Event{
	
	private EntityLivingBase entity;
	
	public EventLivingUpdate(EntityLivingBase entity) {
		this.entity = entity;
	}

	public EntityLivingBase getEntity() {
		return entity;
	}
}
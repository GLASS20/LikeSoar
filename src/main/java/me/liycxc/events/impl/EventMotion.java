package me.liycxc.events.impl;

import lombok.Getter;
import lombok.Setter;
import me.liycxc.events.Event;

@Setter
@Getter
public class EventMotion extends Event {
    public double posX,posY,posZ,rotationYaw,rotationPitch;
    public boolean onGround;
    public EventState eventState;
    public EventMotion(double posX,double posY, double posZ,double rotationYaw,double rotationPitch,boolean onGround) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.rotationYaw = rotationYaw;
        this.rotationPitch =rotationPitch;
        this.onGround = onGround;
        eventState = EventState.PRE;
    }
}


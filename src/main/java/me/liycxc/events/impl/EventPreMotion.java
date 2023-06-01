package me.liycxc.events.impl;

import lombok.Getter;
import lombok.Setter;
import me.liycxc.events.Event;

@Getter
@Setter
public class EventPreMotion extends Event {
    private double posX;
    private double posY;
    private double posZ;
    private float yaw;
    private float pitch;
    private boolean onGround;

    public EventPreMotion(double posX, double posY, double posZ, float rotationYaw, float rotationPitch, boolean onGround) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.yaw = rotationYaw;
        this.pitch = rotationPitch;
        this.onGround = onGround;
    }
}

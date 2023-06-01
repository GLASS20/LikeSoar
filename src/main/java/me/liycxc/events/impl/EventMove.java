package me.liycxc.events.impl;

import lombok.Getter;
import lombok.Setter;
import me.liycxc.events.Event;

@Getter
@Setter
public class EventMove extends Event {
    private double posX, posY, posZ;
    public EventMove(double posX,double posY, double posZ) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }
}

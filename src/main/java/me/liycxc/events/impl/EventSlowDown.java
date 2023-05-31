package me.liycxc.events.impl;

import lombok.Getter;
import lombok.Setter;
import me.liycxc.events.Event;

@Getter
@Setter
public class EventSlowDown extends Event {
    private float strafeMultiplier;
    private float forwardMultiplier;
    public EventSlowDown(float strafeMultiplier,float forwardMultiplier) {
        this.strafeMultiplier = strafeMultiplier;
        this.forwardMultiplier = forwardMultiplier;
    }
}

package me.liycxc.events.impl;

import lombok.Getter;
import lombok.Setter;
import me.liycxc.events.Event;

@Getter
@Setter
public class EventJump extends Event {
    private float jumpMotion;
    private float yaw;

    public EventJump(float jumpMotion, float movementYaw) {
        this.jumpMotion = jumpMotion;
        this.yaw = movementYaw;
    }
}

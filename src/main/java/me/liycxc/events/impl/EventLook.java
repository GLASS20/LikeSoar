package me.liycxc.events.impl;

import lombok.Getter;
import lombok.Setter;
import me.liycxc.events.Event;
import me.liycxc.utils.vector.Vector2f;

public class EventLook extends Event {
    @Getter
    @Setter
    private Vector2f rotation;

    public EventLook(Vector2f rotation) {
        this.rotation = rotation;
    }
}

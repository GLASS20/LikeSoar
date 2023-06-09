package me.liycxc.api.events.impl;

import me.liycxc.api.events.Event;

public class EventServerJoin extends Event {
    public String ip;
    public int port;
    public EventServerJoin(String ip,int port) {
        this.ip = ip;
        this.port = port;
    }
}

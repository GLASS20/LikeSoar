package me.liycxc.manager;

import me.liycxc.NekoCat;
import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.EventLoadWorld;
import net.minecraft.entity.Entity;

import java.util.ArrayList;

public class BotManager extends ArrayList<Entity> {

    public void init() {
        NekoCat.instance.eventManager.register(this);
    }

    @EventTarget
    public void onLoadWorld(EventLoadWorld loadWorld) {
        this.clear();
    }

    public boolean add(Entity entity) {
        if (!this.contains(entity)) super.add(entity);
        return false;
    }
}
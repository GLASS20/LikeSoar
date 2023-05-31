package me.liycxc.manager.component;

import me.liycxc.NekoCat;
import me.liycxc.manager.component.impl.BadPacketsComponent;
import me.liycxc.manager.component.impl.InventoryDeSyncComponent;
import me.liycxc.manager.component.impl.RotationComponent;

import java.util.ArrayList;

public final class ComponentManager extends ArrayList<Component> {

    /**
     * Called on client start and when for some reason when we reinitialize
     */
    public void init() {
        this.add(new RotationComponent());
        this.add(new InventoryDeSyncComponent());
        this.add(new BadPacketsComponent());
        this.registerToEventBus();
    }

    public void registerToEventBus() {
        for (Component component : this) {
            NekoCat.instance.eventManager.register(component);
        }
    }
}

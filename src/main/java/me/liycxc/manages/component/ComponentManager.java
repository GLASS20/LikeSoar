package me.liycxc.manages.component;

import me.liycxc.NekoCat;
import me.liycxc.manages.component.impl.*;

import java.util.ArrayList;

public final class ComponentManager extends ArrayList<Component> {

    /**
     * Called on client start and when for some reason when we reinitialize
     */
    public void init() {
        this.add(new RotationComponent());
        this.add(new InventoryDeSyncComponent());
        this.add(new BadPacketsComponent());
        this.add(new KeybindComponent());
        this.add(new SlotComponent());
        this.add(new SelectorDetectionComponent());
        this.add(new BlinkComponent());
        this.registerToEventBus();
    }

    public void registerToEventBus() {
        for (Component component : this) {
            NekoCat.instance.eventManager.register(component);
        }
    }
}

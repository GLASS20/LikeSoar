package me.liycxc.modules.kinds.combat;

import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.EventMotion;
import me.liycxc.events.impl.EventMouseOver;
import me.liycxc.events.impl.EventState;
import me.liycxc.modules.FloatValue;
import me.liycxc.modules.Module;
import me.liycxc.modules.ModuleCategory;
import org.lwjgl.input.Mouse;

public class Reach extends Module {
    public Reach() {
        super("Reach","Like long arm monkeys", ModuleCategory.Combat);
    }
    public static FloatValue maxValue = new FloatValue("Reach value",4f,3f,5f);

    private int exempt = 0;

    @EventTarget
    public void onPreMotion (EventMotion eventMotion) {
        if (eventMotion.eventState == EventState.PRE) {
            exempt--;
        }
    }
    @EventTarget
    public void onMouseOver(EventMouseOver eventMouseOver) {
        if (Mouse.isButtonDown(1)) {
            exempt = 1;
        }

        if (exempt > 0) return;

        eventMouseOver.setRange(maxValue.get());
    }
}

package me.liycxc.modules.impl.utilty;

import me.liycxc.api.events.EventTarget;
import me.liycxc.api.events.impl.EventPreMotion;
import me.liycxc.api.value.impl.IntValue;
import me.liycxc.manages.component.impl.SlotComponent;
import me.liycxc.modules.Module;
import me.liycxc.modules.ModuleCategory;
import net.minecraft.item.ItemBlock;

public class FastPlace extends Module {
    public FastPlace() {
        super("FastPlace","FastPlace", ModuleCategory.Util);
    }

    private final IntValue delay = new IntValue("Delay", 0, 0, 3);

    @EventTarget
    public void onPreMotion(EventPreMotion event) {
        if (SlotComponent.getItemStack() != null && SlotComponent.getItemStack().getItem() instanceof ItemBlock) {
            mc.rightClickDelayTimer = Math.min(mc.rightClickDelayTimer, this.delay.get());
        }
    }
}

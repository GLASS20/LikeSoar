package me.liycxc.modules.impl.movement;

import me.liycxc.api.impl.BoolValue;
import me.liycxc.api.tags.ModuleTag;
import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.EventPreMotion;
import me.liycxc.manager.component.impl.SlotComponent;
import me.liycxc.modules.Module;
import me.liycxc.modules.ModuleCategory;
import net.minecraft.item.ItemBlock;
import org.lwjgl.input.Keyboard;

@ModuleTag
public class SafeWalk extends Module {
    public SafeWalk() {
        super("SafeWalk", "Safe Walk", ModuleCategory.Movement, Keyboard.KEY_X);
    }

    private final BoolValue blocksOnly = new BoolValue("Blocks Only", false);
    private final BoolValue backwardsOnly = new BoolValue("Backwards Only", false);

    @EventTarget
    public void onPreUpdate(EventPreMotion event){

        mc.thePlayer.safeWalk = mc.thePlayer.onGround && (!mc.gameSettings.keyBindForward.isKeyDown() || !backwardsOnly.getValue()) &&
                ((SlotComponent.getItemStack() != null && SlotComponent.getItemStack().getItem() instanceof ItemBlock) ||
                        !this.blocksOnly.getValue());
    };
}

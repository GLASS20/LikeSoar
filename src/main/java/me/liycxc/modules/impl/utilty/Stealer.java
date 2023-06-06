package me.liycxc.modules.impl.utilty;

import me.liycxc.api.events.EventTarget;
import me.liycxc.api.events.impl.EventPreMotion;
import me.liycxc.api.tags.ModuleTag;
import me.liycxc.api.value.impl.BoolValue;
import me.liycxc.api.value.impl.IntValue;
import me.liycxc.modules.Module;
import me.liycxc.modules.ModuleCategory;
import me.liycxc.utils.MathUtils;
import me.liycxc.utils.module.invs.ItemUtil;
import me.liycxc.utils.module.player.StopWatch;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;

@ModuleTag
public class Stealer extends Module {
    public Stealer() {
        super("Stealer","Chest Stealer", ModuleCategory.Util, Keyboard.KEY_B);
    }

    private final IntValue delayA = new IntValue("Delay A",  100, 0, 500);
    private final IntValue delayB = new IntValue("Delay B", 150, 0, 500);

    private final BoolValue ignoreTrash = new BoolValue("Ignore Trash", true);

    private final StopWatch stopwatch = new StopWatch();
    private long nextClick;
    private int lastClick;
    private int lastSteal;

    @EventTarget
    public void onPreMotion(EventPreMotion event) {

        if (mc.currentScreen instanceof GuiChest) {
            final ContainerChest container = (ContainerChest) mc.thePlayer.openContainer;

            if (!this.stopwatch.finished(this.nextClick)) {
                return;
            }

            this.lastSteal++;

            for (int i = 0; i < container.inventorySlots.size(); i++) {
                final ItemStack stack = container.getLowerChestInventory().getStackInSlot(i);

                if (stack == null || this.lastSteal <= 1) {
                    continue;
                }

                if (this.ignoreTrash.getValue() && !ItemUtil.useful(stack)) {
                    continue;
                }

                this.nextClick = Math.round(MathUtils.getRandom(delayA.get(), delayB.get()));
                mc.playerController.windowClick(container.windowId, i, 0, 1, mc.thePlayer);
                this.stopwatch.reset();
                this.lastClick = 0;
                if (this.nextClick > 0) return;
            }

            this.lastClick++;

            if (this.lastClick > 1) {
                mc.thePlayer.closeScreen();
            }
        } else {
            this.lastClick = 0;
            this.lastSteal = 0;
        }
    };
}
package me.liycxc.modules.impl.render;

import me.liycxc.api.events.EventTarget;
import me.liycxc.api.events.impl.EventCameraRotation;
import me.liycxc.api.events.impl.EventScrollMouse;
import me.liycxc.api.tags.ModuleTag;
import me.liycxc.api.value.impl.FloatValue;
import me.liycxc.modules.Module;
import me.liycxc.modules.ModuleCategory;
import me.liycxc.utils.animation.simple.SimpleAnimation;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.input.Keyboard;

@ModuleTag
public class FarCamera extends Module {

    private final SimpleAnimation animation = new SimpleAnimation(0.0F);
    private float currentFactor = 1.0F;

    private final FloatValue rangeOption = new FloatValue("Range", 20F, 1F, 50F);

    public FarCamera() {
        super("FarCamera", "Move the camera faraway", ModuleCategory.Render);
    }

    @EventTarget
    public void onCameraRotation(EventCameraRotation event) {
        animation.setAnimation(currentFactor, 20F);
        rangeOption.set(animation.getValue());

        float range = rangeOption.get();

        if(mc.gameSettings.thirdPersonView == 1) {
            GlStateManager.translate(0.0F, 0.0F, -range);
        }
    }

    @EventTarget
    public void onScrollMouse(EventScrollMouse event) {
        if (Keyboard.isKeyDown(Keyboard.KEY_LMENU)) {
            event.setCancelled(true);
            if(event.getAmount() < 0) {
                if(currentFactor < 50) {
                    currentFactor += 8.0F;
                }
            } else if(event.getAmount() > 0) {
                if(currentFactor > 1) {
                    currentFactor -= 6.0F;
                }
            }
        }
    }
}

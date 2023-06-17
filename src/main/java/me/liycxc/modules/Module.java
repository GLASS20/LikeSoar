package me.liycxc.modules;

import lombok.Getter;
import lombok.Setter;
import me.liycxc.NekoCat;
import me.liycxc.api.value.Value;
import me.liycxc.utils.PlayerUtils;
import me.liycxc.utils.TimerUtils;
import me.liycxc.utils.animation.simple.SimpleAnimation;
import net.minecraft.client.Minecraft;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Module {
    public String moduleName;
    public String description;
    public ModuleCategory moduleCategory;
    public int keybind = 0;
    public SimpleAnimation introAnimation = new SimpleAnimation(0.0f);
    public Minecraft mc = Minecraft.getMinecraft();
    public SimpleAnimation buttonAnimation = new SimpleAnimation(0.0f);
    public SimpleAnimation buttonOpacityAnimation = new SimpleAnimation(0.0f);
    public TimerUtils selectTimer = new TimerUtils();
    public boolean toggled = false;

    public Module(String moduleName, String description, ModuleCategory moduleCategory) {
        this.moduleName = moduleName;
        this.description = description;
        this.moduleCategory = moduleCategory;
        toggled = false;
    }

    public Module(String moduleName, String description, ModuleCategory moduleCategory, int keybind) {
        this.moduleName = moduleName;
        this.description = description;
        this.moduleCategory = moduleCategory;
        toggled = false;
        this.keybind = keybind;
    }

    public Module(String moduleName, String description, ModuleCategory moduleCategory, boolean toggled) {
        this.moduleName = moduleName;
        this.description = description;
        this.moduleCategory = moduleCategory;
        this.toggled = toggled;
    }

    public Module(String moduleName, String description, ModuleCategory moduleCategory, int keybind, boolean toggled) {
        this.moduleName = moduleName;
        this.description = description;
        this.moduleCategory = moduleCategory;
        this.toggled = toggled;
        this.keybind = keybind;
    }

    public void onInitialize() {}

//    @Deprecated
//    public void onWorld() {}

    public void onEnable() {}

    public void onDisable() {}

    public void toggle() {
        try {
            if (toggled) {
                toggled = false;
                NekoCat.instance.eventManager.unregister(this);
                onDisable();
            } else {
                toggled = true;
                NekoCat.instance.eventManager.register(this);
                onEnable();
            }
            PlayerUtils.tellPlayer("Module " + moduleName + " was " + (toggled ? "Enabled" : "Disabled"));
            mc.thePlayer.playSound("random.click",0.5F,1F);
            // ClientUtils.showNotification("Module", moduleName + " was " + (toggled ? "Enabled" : "Disabled"));
        } catch (Exception exception) {
            if (NekoCat.instance.DEVELOPMENT_SWITCH) {
                exception.printStackTrace();
            }
        }
    }

    public List<Value<?>> getValues() {
        List<Value<?>> values = new ArrayList<>();
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            if (Value.class.isAssignableFrom(field.getType())) {
                try {
                    field.setAccessible(true);
                    Value<?> value = (Value<?>) field.get(this);
                    values.add(value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return values;
    }

    public Value<?> getValueByName(String name) {
        return getValues().stream()
                .filter(value -> value.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public boolean getToggled() {
        return this.toggled;
    }
}

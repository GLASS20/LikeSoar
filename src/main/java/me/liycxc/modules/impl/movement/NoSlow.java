package me.liycxc.modules.impl.movement;

import me.liycxc.NekoCat;
import me.liycxc.api.tags.ModuleTag;
import me.liycxc.api.value.Function0;
import me.liycxc.api.value.Value;
import me.liycxc.api.value.impl.ListValue;
import me.liycxc.modules.Module;
import me.liycxc.modules.ModuleCategory;
import me.liycxc.modules.impl.movement.noslow.NoSlowMode;
import me.liycxc.modules.impl.movement.noslow.impl.WatchdogMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liycxc
 */
@ModuleTag
public class NoSlow extends Module {
    private final NoSlowMode none = new NoSlowMode("None");
    public List<NoSlowMode> modes = new ArrayList<>();
    public List<Value<?>> values = new ArrayList<>();
    public List<String> names = new ArrayList<>();
    public NoSlow() {
        super("NoSlowDown","Get blocking no slow", ModuleCategory.Movement);
    }

    @Override
    public void onEnable() {
        getMode().onEnable();
        NekoCat.instance.eventManager.register(getMode());
    }

    @Override
    public void onDisable() {
        getMode().onDisable();
        modes.forEach(mode -> NekoCat.instance.eventManager.unregister(mode));
    }

    @Override
    public void onInitialize() {
        modes.add(none);
        modes.add(new WatchdogMode());

        for (NoSlowMode mode : modes) {
            names.add(mode.modeName);
        }

        modes.forEach(mode -> NekoCat.instance.eventManager.unregister(mode));

        if (toggled) {
            NekoCat.instance.eventManager.register(getMode());
        }

        values.add(
                new ListValue("Mode",names.toArray(new String[]{}),"None") {
                    @Override
                    public void onChanged(String oldValue, String newValue) {
                        NekoCat.instance.eventManager.unregister(getModeByName(oldValue));
                        if (toggled) {
                            NekoCat.instance.eventManager.register(getModeByName(newValue));
                        }
                    }
                }
        );

        for (NoSlowMode mode : modes) {
            if (mode.getValues().size() > 0) {
                for (Value<?> value : mode.getValues()) {
                    Function0<Boolean> a = () -> values.get(0).get().equals(mode.modeName);
                    Function0<Boolean> b = value.getDisplayable();
                    value.setDisplayable(() -> (a.invoke() && b.invoke()));
                    values.add(value);
                }
            }
        }

        getMode().onInitialize();
    }

    @Override
    public List<Value<?>> getValues() {
        return values;
    }

    private NoSlowMode getMode() {
        return modes.stream()
                .filter(mode -> mode.modeName.equalsIgnoreCase(((ListValue) values.get(0)).get()))
                .findFirst()
                .orElse(none);
    }

    private NoSlowMode getModeByName(String modeName) {
        return modes.stream()
                .filter(mode -> mode.modeName.equalsIgnoreCase(modeName))
                .findFirst()
                .orElse(none);
    }
}

package me.liycxc.modules.impl.utilty;

import me.liycxc.NekoCat;
import me.liycxc.api.tags.ModuleTag;
import me.liycxc.api.value.Function0;
import me.liycxc.api.value.Value;
import me.liycxc.api.value.impl.ListValue;
import me.liycxc.modules.Module;
import me.liycxc.modules.ModuleCategory;
import me.liycxc.modules.impl.utilty.disabler.DisablerMode;
import me.liycxc.modules.impl.utilty.disabler.other.WatchdogMode;

import java.util.ArrayList;
import java.util.List;

@ModuleTag
public class Disabler extends Module {
    public Disabler() {
        super("Disabler","Disable anticheat", ModuleCategory.Util);
    }

    public List<DisablerMode> modes = new ArrayList<>();
    public List<Value<?>> values = new ArrayList<>();
    public List<String> names = new ArrayList<>();
    private final DisablerMode none = new DisablerMode("None");

    @Override
    public void onEnable() {
        getMode().onEnable();
    }

    @Override
    public void onDisable() {
        getMode().onDisable();
    }

    @Override
    public void onInitialize() {
        // Add DisablerModes xD
        modes.add(none);
        modes.add(new WatchdogMode());

        for (DisablerMode mode : modes) {
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

        for (DisablerMode mode : modes) {
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

    private DisablerMode getMode() {
        return modes.stream()
                .filter(mode -> mode.modeName.equalsIgnoreCase(((ListValue) values.get(0)).get()))
                .findFirst()
                .orElse(none);
    }

    private DisablerMode getModeByName(String modeName) {
        return modes.stream()
                .filter(mode -> mode.modeName.equalsIgnoreCase(modeName))
                .findFirst()
                .orElse(none);
    }
}
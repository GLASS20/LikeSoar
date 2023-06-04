package me.liycxc.modules;

import me.liycxc.NekoCat;
import me.liycxc.modules.impl.combat.AutoClick;
import me.liycxc.modules.impl.combat.KillAura;
import me.liycxc.modules.impl.combat.Reach;
import me.liycxc.modules.impl.movement.NoSlowDown;
import me.liycxc.modules.impl.render.Chams;
import me.liycxc.modules.impl.utilty.*;
import me.liycxc.utils.Logger;

import java.util.ArrayList;
import java.util.HashMap;

public class ModuleManager {
    public HashMap<Integer,Module> keyBinds = new HashMap<>();
    ArrayList<Module> modules = new ArrayList<Module>();

    public void registerModules() {
        registerModule(new IRC());
        registerModule(new AutoClick());
        registerModule(new AutoTool());
        registerModule(new Reach());
        registerModule(new RightClick());
        registerModule(new Chams());
        registerModule(new InvManager());
        registerModule(new Teams());
        registerModule(new KillAura());
        registerModule(new Scaffold());
        registerModule(new NoSlowDown());
    }

    public void registerModule(Module module) {
        modules.add(module);
        if (module.getKeybind() != 0) {
            keyBinds.put(module.getKeybind(),module);
        }

        Logger.log("Initialize module: " + module.moduleName);
        module.onInitialize();
        Logger.log("Finished initialize module: " + module.moduleName);

        if (module.getToggled()) {
            NekoCat.instance.eventManager.register(module);
            Logger.log("Event register module: " + module.moduleName);
        } else {
            NekoCat.instance.eventManager.unregister(module);
        }
    }

    public Module getModule(String name) {
        for (Module i : modules) {
            if (i.moduleName.equalsIgnoreCase(name)) {
                return i;
            }
        }
        return null;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }
}

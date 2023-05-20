package me.liycxc.modules;

import me.liycxc.modules.kinds.combat.AutoClick;
import me.liycxc.modules.kinds.combat.Test;
import me.liycxc.modules.kinds.utilty.irc.IRC;
import me.liycxc.utils.Logger;

import java.util.ArrayList;

public class ModuleManager {
    ArrayList<Module> modules = new ArrayList<Module>();

    public void onStart() {

    }

    public void registerModules() {
        registerModule(new Test());
        registerModule(new IRC());
        registerModule(new AutoClick());
    }

    public void registerModule(Module module) {
        modules.add(module);
        Logger.log("Initialize module: " + module.moduleName);
        module.onInitialize();
        Logger.log("Finished initialize module: " + module.moduleName);
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

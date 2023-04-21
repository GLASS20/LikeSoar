package me.glass20.modules;

import me.glass20.modules.combat.Test;
import me.glass20.utils.Logger;

import java.util.ArrayList;

public class ModuleManager {
    ArrayList<Module> modules = new ArrayList<Module>();

    public void onStart() {

    }

    public void registerModules() {
        registerModule(new Test());
    }

    public void registerModule(Module module) {
        modules.add(module);
        Logger.log("Initialize module: " + module.moduleName);
        module.onInitialize();
        Logger.log("Finished initialize module: " + module.moduleName);
    }

    public ArrayList<Module> getModules() {
        return modules;
    }
}

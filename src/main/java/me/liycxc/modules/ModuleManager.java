package me.liycxc.modules;

import me.liycxc.NekoCat;
import me.liycxc.modules.impl.combat.AntiBot;
import me.liycxc.modules.impl.combat.AutoClick;
import me.liycxc.modules.impl.combat.KillAura;
import me.liycxc.modules.impl.combat.Reach;
import me.liycxc.modules.impl.movement.NoSlowDown;
import me.liycxc.modules.impl.movement.SafeWalk;
import me.liycxc.modules.impl.render.Chams;
import me.liycxc.modules.impl.utilty.*;
import me.liycxc.utils.Logger;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModuleManager {
    public MultiValuedMap<Integer,Module> keyBinds = new ArrayListValuedHashMap<>();
    ArrayList<Module> modules = new ArrayList<Module>();
    List<Module> moduleList = new ArrayList<>();

    public void registerModules() {
        moduleList.add(new IRC());
        moduleList.add(new AutoClick());
        moduleList.add(new AutoTool());
        moduleList.add(new Reach());
        moduleList.add(new RightClick());
        moduleList.add(new Chams());
        moduleList.add(new Teams());
        moduleList.add(new KillAura());
        moduleList.add(new Scaffold());
        moduleList.add(new NoSlowDown());
        moduleList.add(new InvManager());
        moduleList.add(new InvMove());
        moduleList.add(new Stealer());
        moduleList.add(new SafeWalk());
        moduleList.add(new AntiBot());
        registerModuleByList(moduleList.stream().sorted(Comparator.comparingInt(module -> module.getModuleName().length())).sorted(Comparator.comparingInt(module -> module.getModuleCategory().ordinal())).collect(Collectors.toList()));
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

    public void registerModuleByList(List<Module> moduleList) {
        for (Module module : moduleList) {
            registerModule(module);
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

    public void setKeybind(Module module, int key) {
        MultiValuedMap<Integer, Module> newMap = new ArrayListValuedHashMap<>();
        for (Map.Entry<Integer, Module> entry : keyBinds.entries()) {
            if (entry.getValue().equals(module)) {
                newMap.put(key, entry.getValue());
            } else {
                newMap.put(entry.getKey(), entry.getValue());
            }
        }
        keyBinds = newMap;
    }
}

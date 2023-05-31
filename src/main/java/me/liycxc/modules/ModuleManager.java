package me.liycxc.modules;

import me.liycxc.NekoCat;
import me.liycxc.modules.kinds.combat.AutoClick;
import me.liycxc.modules.kinds.combat.Reach;
import me.liycxc.modules.kinds.combat.killAura.KillAura;
import me.liycxc.modules.kinds.render.Chams;
import me.liycxc.modules.kinds.utilty.AutoTool;
import me.liycxc.modules.kinds.utilty.RightClick;
import me.liycxc.modules.kinds.utilty.Scaffold;
import me.liycxc.modules.kinds.utilty.Teams;
import me.liycxc.modules.kinds.utilty.invManager.InvManager;
import me.liycxc.modules.kinds.utilty.irc.IRC;
import me.liycxc.utils.Logger;

import java.util.ArrayList;

public class ModuleManager {
    ArrayList<Module> modules = new ArrayList<Module>();

    public void onStart() {

    }

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
    }

    public void registerModule(Module module) {
        modules.add(module);
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

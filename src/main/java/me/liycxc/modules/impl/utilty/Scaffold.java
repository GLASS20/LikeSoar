package me.liycxc.modules.impl.utilty;

import me.liycxc.api.tags.ModuleTag;
import me.liycxc.modules.Module;
import me.liycxc.modules.ModuleCategory;

@ModuleTag
public class Scaffold extends Module {
    public Scaffold() {
        super("Scaffold","Blockfly", ModuleCategory.Util);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}

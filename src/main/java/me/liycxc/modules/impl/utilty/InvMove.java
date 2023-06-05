package me.liycxc.modules.impl.utilty;

import me.liycxc.api.tags.ModuleTag;
import me.liycxc.modules.Module;
import me.liycxc.modules.ModuleCategory;

@ModuleTag
public class InvMove extends Module {
    public InvMove() {
        super("InvMove","You can move when you open gui", ModuleCategory.Util);
    }
}

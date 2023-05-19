package me.liycxc.modules.combat;

import me.liycxc.modules.Module;
import me.liycxc.modules.ModuleCategory;

public class Test extends Module {
    public Test() {
        super("Test","test.description", ModuleCategory.Combat);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        System.out.println("Hello World!");
    }
}

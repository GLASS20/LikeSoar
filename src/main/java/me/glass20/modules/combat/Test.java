package me.glass20.modules.combat;

import me.glass20.modules.Module;
import me.glass20.modules.ModuleCategory;

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

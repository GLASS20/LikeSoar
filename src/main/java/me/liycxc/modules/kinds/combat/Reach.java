package me.liycxc.modules.kinds.combat;

import me.liycxc.modules.FloatValue;
import me.liycxc.modules.Module;
import me.liycxc.modules.ModuleCategory;

public class Reach extends Module {
    public Reach() {
        super("Reach","Like long arm monkeys", ModuleCategory.Combat);
    }
    public static FloatValue maxValue = new FloatValue("MaxValue",4f,3f,5f);
    public double getReach() {
        return (double) maxValue.get();
    }
}

package me.liycxc.pvp.management.mods.impl.hacks;

import me.liycxc.NekoCat;
import me.liycxc.pvp.management.mods.Mod;
import me.liycxc.pvp.management.mods.ModCategory;

public class ReachMod extends Mod {
    public ReachMod() {
        super("Reach","Hacker uuu", ModCategory.HACK);
    }
    @Override
    public void setup() {
        this.addSliderSetting("MaxValue",this,4,3,5,false);
    }

    public double getReach() {
        return NekoCat.instance.settingsManager.getSettingByName(this,"MaxValue").getValDouble();
    }
}
package me.soar.management.mods.impl.hacks;

import me.soar.Soar;
import me.soar.management.mods.Mod;
import me.soar.management.mods.ModCategory;

public class ReachHackMod extends Mod {
    public ReachHackMod() {
        super("Reach Hack","Hacker uuu", ModCategory.OTHER);
    }
    @Override
    public void setup() {
        this.addSliderSetting("MaxValue",this,4,3,5,false);
    }

    public double getReach() {
        return Soar.instance.settingsManager.getSettingByName(this,"MaxValue").getValDouble();
    }
}

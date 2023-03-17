package me.eldodebug.soar.management.mods.impl.hacks;

import me.eldodebug.soar.Soar;
import me.eldodebug.soar.management.mods.Mod;
import me.eldodebug.soar.management.mods.ModCategory;
import net.minecraft.client.renderer.entity.Render;

import java.util.ArrayList;

public class ReachHackMod extends Mod {
    public ReachHackMod() {
        super("ReachHack","Hacker uuu", ModCategory.OTHER);
    }
    @Override
    public void setup() {
        this.addSliderSetting("MaxValue",this,4,3,5,false);
    }

    public double getReach() {
        return Soar.instance.settingsManager.getSettingByName(this,"MaxValue").getValDouble();
    }
}

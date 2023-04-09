package like.soar.management.mods.impl.hacks;

import like.soar.management.mods.ModCategory;
import like.soar.Soar;
import like.soar.management.mods.Mod;

public class ReachMod extends Mod {
    public ReachMod() {
        super("Reach","Hacker uuu", ModCategory.HACK);
    }
    @Override
    public void setup() {
        this.addSliderSetting("MaxValue",this,4,3,5,false);
    }

    public double getReach() {
        return Soar.instance.settingsManager.getSettingByName(this,"MaxValue").getValDouble();
    }
}

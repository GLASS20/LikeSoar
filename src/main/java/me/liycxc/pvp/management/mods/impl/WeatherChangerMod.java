package me.liycxc.pvp.management.mods.impl;

import java.util.ArrayList;

import me.liycxc.pvp.management.mods.Mod;
import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.EventReceivePacket;
import me.liycxc.events.impl.EventUpdate;
import me.liycxc.NekoCat;
import me.liycxc.pvp.management.mods.ModCategory;
import net.minecraft.network.play.server.S2BPacketChangeGameState;

public class WeatherChangerMod extends Mod {

	public WeatherChangerMod() {
		super("Weather Changer", "Change world weather", ModCategory.RENDER);
	}

	@Override
	public void setup() {
		ArrayList<String> options = new ArrayList<>();
		
		options.add("Clear");
		options.add("Rain");
		options.add("Thunder");
		
		this.addModeSetting("Weather", this, "Clear", options);
	}
	
    @EventTarget
    public void onUpdate(EventUpdate event) {
    	
    	String mode = NekoCat.instance.settingsManager.getSettingByName(this, "Weather").getValString();
    	
    	switch(mode) {
	    	default:
	            mc.theWorld.setRainStrength(0);
	            mc.theWorld.setThunderStrength(0);
	    		break;
	    	case "Clear":
	            mc.theWorld.setRainStrength(0);
	            mc.theWorld.setThunderStrength(0);
	    		break;
	    	case "Rain":
	            mc.theWorld.setRainStrength(1);
	            mc.theWorld.setThunderStrength(0);
	    		break;
	    	case "Thunder":
	            mc.theWorld.setRainStrength(1);
	            mc.theWorld.setThunderStrength(1);
	    		break;
    	}
    }

    @EventTarget
    public void onReceivePacket(EventReceivePacket event) {
        if(event.getPacket() instanceof S2BPacketChangeGameState){
            S2BPacketChangeGameState S2BPacket=(S2BPacketChangeGameState) event.getPacket();
            if(S2BPacket.getGameState()==7||S2BPacket.getGameState()==8)
                event.setCancelled(true);
        }
    }
}

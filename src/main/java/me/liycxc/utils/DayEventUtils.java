package me.liycxc.utils;

import me.liycxc.NekoCat;
import me.liycxc.gui.management.mods.impl.HUDMod;

import java.util.Calendar;
import java.util.Date;

public class DayEventUtils {

	private static Calendar getCalendar() {
		
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        
        return calendar;
	}
	
	public static boolean isHalloween() {
		
		if (getCalendar().get(2) + 1 == 10 && (getCalendar().get(5) == 29 || getCalendar().get(5) == 30 || getCalendar().get(5) == 31)) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isChristmas() {
		
		if (getCalendar().get(2) + 1 == 12 && (getCalendar().get(5) == 23 || getCalendar().get(5) == 24 || getCalendar().get(5) == 25)) {
			return true;
		}
		
		return false;
	}
	
	public static void resetHudDesign() {
		
		if(!isHalloween()) {
			if(NekoCat.instance.settingsManager.getSettingByClass(HUDMod.class, "Design").getValString().equals("Halloween")) {
				NekoCat.instance.settingsManager.getSettingByClass(HUDMod.class, "Design").setValString("Color");
			}
		}
		
		if(!isChristmas()) {
			if(NekoCat.instance.settingsManager.getSettingByClass(HUDMod.class, "Design").getValString().equals("Christmas")) {
				NekoCat.instance.settingsManager.getSettingByClass(HUDMod.class, "Design").setValString("Color");
			}
		}
	}
}

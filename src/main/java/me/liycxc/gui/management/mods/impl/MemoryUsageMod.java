package me.liycxc.gui.management.mods.impl;

import me.liycxc.gui.management.events.EventTarget;
import me.liycxc.gui.management.events.impl.EventRender2D;
import me.liycxc.gui.management.events.impl.EventRenderShadow;
import me.liycxc.gui.management.mods.HudMod;

public class MemoryUsageMod extends HudMod{

	public MemoryUsageMod() {
		super("Memory Usage", "Display Minecraft memory usage");
	}

	@EventTarget
	public void onRender2D(EventRender2D event) {
		super.onRender2D();
	}
	
	@EventTarget
	public void onRenderShadow(EventRenderShadow event) {
		super.onRenderShadow();
	}
	
	@Override
	public String getText() {
		
		Runtime runtime = Runtime.getRuntime();
		String mem = "Mem: " + (runtime.totalMemory() - runtime.freeMemory()) * 100L / runtime.maxMemory() + "%";
		
		return mem;
	}
}

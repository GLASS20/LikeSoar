package me.soar.management.quickplay.impl;

import java.util.ArrayList;

import me.soar.management.quickplay.QuickPlay;
import me.soar.management.quickplay.QuickPlayCommand;

public class MegaWallsQuickPlay extends QuickPlay{

	public MegaWallsQuickPlay() {
		super("MegaWalls", "soar/mods/quickplay/MegaWalls.png");
	}

	@Override
	public void addCommands() {
		ArrayList<QuickPlayCommand> commands = new ArrayList<QuickPlayCommand>();
		
		commands.add(new QuickPlayCommand("Standard", "/play mw_standard"));
		commands.add(new QuickPlayCommand("Faceoff", "/play mw_face_off"));
		
		this.setCommands(commands);
	}
}

package me.soar.management.quickplay.impl;

import java.util.ArrayList;

import me.soar.management.quickplay.QuickPlay;
import me.soar.management.quickplay.QuickPlayCommand;

public class BlitzSGQuickPlay extends QuickPlay{

	public BlitzSGQuickPlay() {
		super("BlitzSG", "soar/mods/quickplay/Blitz.png");
	}

	
	@Override
	public void addCommands() {
		ArrayList<QuickPlayCommand> commands = new ArrayList<QuickPlayCommand>();
		
		commands.add(new QuickPlayCommand("Lobby", "/l hg"));
		commands.add(new QuickPlayCommand("Solo", "/play blitz_solo_normal"));
		commands.add(new QuickPlayCommand("Teams", "/play blitz_teams_normal"));
		
		this.setCommands(commands);
	}
}

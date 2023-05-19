package me.liycxc.gui.management.quickplay.impl;

import java.util.ArrayList;

import me.liycxc.gui.management.quickplay.QuickPlay;
import me.liycxc.gui.management.quickplay.QuickPlayCommand;

public class UHCQuickPlay extends QuickPlay{

	public UHCQuickPlay() {
		super("UHC", "soar/mods/quickplay/UHC.png");
	}

	@Override
	public void addCommands() {
		ArrayList<QuickPlayCommand> commands = new ArrayList<QuickPlayCommand>();
		
		commands.add(new QuickPlayCommand("Lobby", "/l hc"));
		commands.add(new QuickPlayCommand("Solo", "/play uhc_solo"));
		commands.add(new QuickPlayCommand("Teams", "/play uhc_teams"));
		commands.add(new QuickPlayCommand("Events Mode", "/play /play uhc_events"));
		
		this.setCommands(commands);
	}
}

package me.liycxc.gui.management.quickplay.impl;

import java.util.ArrayList;

import me.liycxc.gui.management.quickplay.QuickPlay;
import me.liycxc.gui.management.quickplay.QuickPlayCommand;

public class MainLobbyQuickPlay extends QuickPlay{

	public MainLobbyQuickPlay() {
		super("MainLobby", "soar/mods/quickplay/MainLobby.png");
	}

	@Override
	public void addCommands(){
		ArrayList<QuickPlayCommand> commands = new ArrayList<QuickPlayCommand>();
		
		commands.add(new QuickPlayCommand("Lobby", "/lobby main"));
		
		this.setCommands(commands);
	}
}

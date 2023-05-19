package me.liycxc.gui.management.quickplay.impl;

import me.liycxc.gui.management.quickplay.QuickPlay;
import me.liycxc.gui.management.quickplay.QuickPlayCommand;

import java.util.ArrayList;

public class BuildBattleQuickPlay extends QuickPlay{

	public BuildBattleQuickPlay() {
		super("BuildBattle", "soar/mods/quickplay/BuildBattle.png");
	}

	@Override
	public void addCommands() {
		ArrayList<QuickPlayCommand> commands = new ArrayList<QuickPlayCommand>();
		
		commands.add(new QuickPlayCommand("Lobby", "/l bb"));
		commands.add(new QuickPlayCommand("Solo", "/play build_battle_solo_normal"));
		commands.add(new QuickPlayCommand("Teams", "/play build_battle_teams_normal"));
		commands.add(new QuickPlayCommand("Pro Mode", "/play build_battle_solo_pro"));
		commands.add(new QuickPlayCommand("Guess The Build", "/play build_battle_guess_the_build"));
		
		this.setCommands(commands);
	}
}

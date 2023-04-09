package like.soar.management.quickplay.impl;

import java.util.ArrayList;

import like.soar.management.quickplay.QuickPlay;
import like.soar.management.quickplay.QuickPlayCommand;

public class SkyblockQuickPlay extends QuickPlay{

	public SkyblockQuickPlay() {
		super("Skyblock", "soar/mods/quickplay/Skyblock.png");
	}
	
	@Override
	public void addCommands() {
		ArrayList<QuickPlayCommand> commands = new ArrayList<QuickPlayCommand>();
		
		commands.add(new QuickPlayCommand("Skyblock", "/skyblock"));
		
		this.setCommands(commands);
	}

}

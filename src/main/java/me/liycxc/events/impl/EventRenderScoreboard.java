package me.liycxc.events.impl;

import me.liycxc.events.Event;
import net.minecraft.scoreboard.ScoreObjective;

public class EventRenderScoreboard extends Event{
	
	private ScoreObjective objective;
	
	public EventRenderScoreboard(ScoreObjective objective) {
		this.objective = objective;
	}

	public ScoreObjective getObjective() {
		return objective;
	}
}
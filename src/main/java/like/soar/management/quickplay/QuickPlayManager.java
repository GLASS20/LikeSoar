package like.soar.management.quickplay;

import java.util.ArrayList;

import like.soar.management.quickplay.impl.ArcadeQuickPlay;
import like.soar.management.quickplay.impl.BedwarsQuickPlay;
import like.soar.management.quickplay.impl.BlitzSGQuickPlay;
import like.soar.management.quickplay.impl.BuildBattleQuickPlay;
import like.soar.management.quickplay.impl.DuelsQuickPlay;
import like.soar.management.quickplay.impl.MainLobbyQuickPlay;
import like.soar.management.quickplay.impl.MegaWallsQuickPlay;
import like.soar.management.quickplay.impl.MurderMysteryQuickPlay;
import like.soar.management.quickplay.impl.SkyblockQuickPlay;
import like.soar.management.quickplay.impl.SkywarsQuickPlay;
import like.soar.management.quickplay.impl.TNTQuickPlay;
import like.soar.management.quickplay.impl.UHCQuickPlay;

public class QuickPlayManager {

	private ArrayList<QuickPlay> quickPlays = new ArrayList<QuickPlay>();

	public QuickPlayManager() {
		quickPlays.add(new ArcadeQuickPlay());
		quickPlays.add(new BedwarsQuickPlay());
		quickPlays.add(new BlitzSGQuickPlay());
		quickPlays.add(new BuildBattleQuickPlay());
		quickPlays.add(new DuelsQuickPlay());
		quickPlays.add(new MainLobbyQuickPlay());
		quickPlays.add(new MegaWallsQuickPlay());
		quickPlays.add(new MurderMysteryQuickPlay());
		quickPlays.add(new SkyblockQuickPlay());
		quickPlays.add(new SkywarsQuickPlay());
		quickPlays.add(new TNTQuickPlay());
		quickPlays.add(new UHCQuickPlay());
	}

	public ArrayList<QuickPlay> getQuickPlays() {
		return quickPlays;
	}
}
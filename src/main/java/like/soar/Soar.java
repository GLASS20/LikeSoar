package like.soar;

import java.util.Random;

import com.logisticscraft.occlusionculling.DataProvider;
import com.logisticscraft.occlusionculling.OcclusionCullingInstance;

import io.netty.buffer.Unpooled;
import like.soar.gui.GuiEditHUD;
import like.soar.management.account.AccountManager;
import like.soar.management.colors.ColorManager;
import like.soar.management.config.ConfigManager;
import like.soar.management.cosmetics.CosmeticManager;
import like.soar.management.discord.DiscordManager;
import like.soar.management.events.EventManager;
import like.soar.management.events.EventTarget;
import like.soar.management.file.FileManager;
import like.soar.management.gui.GuiManager;
import like.soar.management.image.ImageManager;
import like.soar.management.keybinds.KeyBindManager;
import like.soar.management.mods.ModManager;
import like.soar.management.mods.impl.ClientMod;
import like.soar.management.mods.impl.ForgeSpooferMod;
import like.soar.management.music.MusicManager;
import like.soar.management.quickplay.QuickPlayManager;
import like.soar.management.settings.SettingsManager;
import like.soar.mixin.SoarTweaker;
import like.soar.utils.DayEventUtils;
import like.soar.utils.OSType;
import like.soar.utils.TargetUtils;
import like.soar.utils.TimerUtils;
import like.soar.utils.culling.CullTask;
import like.soar.utils.font.FontUtils;
import like.soar.utils.server.HypixelUtils;
import like.soar.utils.server.ServerUtils;
import like.soar.management.events.impl.EventKey;
import like.soar.management.events.impl.EventPreMotionUpdate;
import like.soar.management.events.impl.EventReceivePacket;
import like.soar.management.events.impl.EventRespawn;
import like.soar.management.events.impl.EventSendPacket;
import like.soar.management.events.impl.EventTick;
import like.soar.management.events.impl.EventUpdate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.BlockPos;

public class Soar {

	public static Soar instance = new Soar();
	
	private String name = "Soar", version = "1145.14";

	public FileManager fileManager;
	public ImageManager imageManager;
	public DiscordManager discordManager;
	public KeyBindManager keyBindManager;
	public SettingsManager settingsManager;
	public EventManager eventManager;
	public ModManager modManager;
	public GuiManager guiManager;
	public ColorManager colorManager;
	public CosmeticManager cosmeticManager;
	public ConfigManager configManager;
	public MusicManager musicManager;
	public AccountManager accountManager;
	public QuickPlayManager quickPlayManager;
	
	private boolean loaded;
	private long playTime;
	private TimerUtils apiTimer = new TimerUtils();
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	public void startClient() {
		
		OSType os = OSType.getType();
		
		fileManager = new FileManager();
		imageManager = new ImageManager();
		keyBindManager = new KeyBindManager();
		settingsManager = new SettingsManager();
		eventManager = new EventManager();
		modManager = new ModManager();
		guiManager = new GuiManager();
		colorManager = new ColorManager();
		cosmeticManager = new CosmeticManager();
		configManager = new ConfigManager();
		musicManager = new MusicManager();
		accountManager = new AccountManager();
		quickPlayManager = new QuickPlayManager();
		
		FontUtils.init();
		
		eventManager.register(this);
		
		if(os == OSType.WINDOWS) {
			discordManager = new DiscordManager();
			discordManager.start();
			discordManager.update("Playing Soar Client v" + Soar.instance.getVersion(), "");
		}
		
		startCull();
		
		mc.gameSettings.loadOptions();
		DayEventUtils.resetHudDesign();
	}
	
	public void stopClient() {
		
		OSType os = OSType.getType();
		
		eventManager.unregister(this);
		
		if(os == OSType.WINDOWS) {
			discordManager = new DiscordManager();
			discordManager.update("Playing Soar Client v" + Soar.instance.getVersion(), "");
		}
	}
    
	@EventTarget
	public void onTick(EventTick event) {
		
    	boolean isRandom = Soar.instance.settingsManager.getSettingByClass(ClientMod.class, "Random").getValBoolean();
    	boolean isLoop = Soar.instance.settingsManager.getSettingByClass(ClientMod.class, "Loop").getValBoolean();

		if(isRandom || isLoop) {
			if(!loaded) {
				if(musicManager.getCurrentMusic() != null && musicManager.getCurrentMusic().mediaPlayer != null && musicManager.getCurrentMusic().mediaPlayer.getCurrentTime().toSeconds() >= musicManager.getCurrentMusic().mediaPlayer.getTotalDuration().toSeconds()) {
					loaded = true;
					new Thread() {
						@Override
						public void run() {
							loaded = false;
							musicManager.getCurrentMusic().mediaPlayer.stop();
							
							if(isRandom) {
								Random random = new Random();
								int randomIndex = random.nextInt(Soar.instance.musicManager.getMusics().size() + 1);
								musicManager.setCurrentMusic(Soar.instance.musicManager.getMusics().get(randomIndex == 10 ? randomIndex - 1 : randomIndex));
							}

							musicManager.getCurrentMusic().playMusic();
						}
					}.start();
				}
			}
		}else {
			loaded = false;
			if(musicManager.getCurrentMusic() != null && musicManager.getCurrentMusic().mediaPlayer != null && musicManager.getCurrentMusic().mediaPlayer.getCurrentTime().toSeconds() == musicManager.getCurrentMusic().mediaPlayer.getTotalDuration().toSeconds()) {
				musicManager.getCurrentMusic().mediaPlayer.stop();
			}
		}
		
		if(SoarTweaker.hasOptifine) {
//			try {
//				ClientUtils.gameSettings_ofFastRender.set(mc.gameSettings, false);
//			} catch (IllegalArgumentException | IllegalAccessException e) {}
		}
		
		mc.gameSettings.useVbo = true;
		mc.gameSettings.fboEnable = true;
	}
	
	@EventTarget
	public void onUpdate(EventUpdate event) {
		TargetUtils.onUpdate();
	}
	
	private void startCull() {
		CullTask cullingTask = new CullTask(new OcclusionCullingInstance(128, new DataProvider() {

			private WorldClient world;

			@Override
			public boolean prepareChunk(int x, int z) {
				return (world = mc.theWorld) != null;
			}

			@Override
			public boolean isOpaqueFullCube(int x, int y, int z) {
				return world.isBlockNormalCube(new BlockPos(x, y, z), false);
			}

		}));

		Thread generalUpdateThread = new Thread(() -> {
			while(mc.running) {
				try {
					Thread.sleep(10);
				}
				catch(InterruptedException error) {
					return;
				}

				cullingTask.run();
			}
		}, "Async Updates");
		generalUpdateThread.setUncaughtExceptionHandler((thread, error) -> {

		});
		generalUpdateThread.start();
	}
	
	@EventTarget
	public void onSendPacket(EventSendPacket event) {
        if (event.getPacket() instanceof C17PacketCustomPayload) {
        	
            C17PacketCustomPayload packet = (C17PacketCustomPayload) event.getPacket();
            
            if(modManager.getModByClass(ForgeSpooferMod.class).isToggled()) {
                (packet).setData(new PacketBuffer(Unpooled.buffer()).writeString("FML"));
            }else {
                (packet).setData(new PacketBuffer(Unpooled.buffer()).writeString("Soar Client v" + version));
            }
        }
	}
    
    @EventTarget
    public void onReceivePacket(EventReceivePacket event) {
    	if(ServerUtils.isHypixel()) {
            if (event.getPacket() instanceof S02PacketChat) {
                final S02PacketChat chatPacket = (S02PacketChat)event.getPacket();
                final String chatMessage = chatPacket.getChatComponent().getUnformattedText();
                if (chatMessage.matches("Your new API key is ........-....-....-....-............")) {
                    event.setCancelled(true);
                    HypixelUtils.setApiKey(chatMessage.replace("Your new API key is ", ""));
                }
            }
    	}
    }
    
    @EventTarget
    public void onRespwrn(EventRespawn event) {
    	if(ServerUtils.isHypixel()) {
        	HypixelUtils.setApiKey(null);
    	}
    }
    
    @EventTarget
    public void onPreUpdate(EventPreMotionUpdate event) {
        if (ServerUtils.isHypixel() && apiTimer.delay(3000) && HypixelUtils.getApiKey() == null) {
            mc.thePlayer.sendChatMessage("/api new");
            apiTimer.reset();
        }
    }
    
    @EventTarget
    public void onKey(EventKey event) {
    	
    	if(mc.thePlayer != null && mc.theWorld != null) {
        	if(event.getKey() == keyBindManager.EDITHUD.getKeyCode()) {
        		mc.displayGuiScreen(new GuiEditHUD(false));
        	}
    	}
    }
    
	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

	public long getPlayTime() {
		return playTime;
	}

	public void setPlayTime(long playTime) {
		this.playTime = playTime;
	}
}

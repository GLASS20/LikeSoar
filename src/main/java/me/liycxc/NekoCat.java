package me.liycxc;

import com.logisticscraft.occlusionculling.DataProvider;
import com.logisticscraft.occlusionculling.OcclusionCullingInstance;
import io.netty.buffer.Unpooled;
import me.liycxc.gui.GuiEditHUD;
import me.liycxc.gui.management.account.AccountManager;
import me.liycxc.gui.management.colors.ColorManager;
import me.liycxc.gui.management.config.ConfigManager;
import me.liycxc.gui.management.cosmetics.CosmeticManager;
import me.liycxc.gui.management.discord.DiscordManager;
import me.liycxc.gui.management.events.EventManager;
import me.liycxc.gui.management.events.EventTarget;
import me.liycxc.gui.management.events.impl.*;
import me.liycxc.gui.management.file.FileManager;
import me.liycxc.gui.management.gui.GuiManager;
import me.liycxc.gui.management.image.ImageManager;
import me.liycxc.gui.management.keybinds.KeyBindManager;
import me.liycxc.gui.management.mods.ModManager;
import me.liycxc.gui.management.mods.impl.ClientMod;
import me.liycxc.gui.management.mods.impl.ForgeSpooferMod;
import me.liycxc.gui.management.music.MusicManager;
import me.liycxc.gui.management.quickplay.QuickPlayManager;
import me.liycxc.gui.management.settings.SettingsManager;
import me.liycxc.ui.font.FontManager;
import me.liycxc.utils.*;
import me.liycxc.utils.culling.CullTask;
import me.liycxc.utils.font.FontUtils;
import me.liycxc.utils.server.HypixelUtils;
import me.liycxc.utils.server.ServerUtils;
import me.liycxc.modules.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.BlockPos;

import java.util.Random;

public class NekoCat {

	public static NekoCat instance = new NekoCat();

	private final String name = "NekoCat";
	private final String version = "2077";

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
	public ModuleManager moduleManager;
	
	private boolean loaded;
	private long playTime;
	private TimerUtils apiTimer = new TimerUtils();
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	public void startClient() {
		
		OSType os = OSType.getType();

		// Managers initializing
		{
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
			moduleManager = new ModuleManager();
		}

		// Fonts initializing
		{
			FontUtils.init();
			FontManager.init();
		}

		eventManager.register(this);
		
		if(os == OSType.WINDOWS) {
			discordManager = new DiscordManager();
			discordManager.start();
			discordManager.update("Playing NekoCat Client v" + NekoCat.instance.getVersion(), "");
		}
		
		startCull();

		mc.gameSettings.loadOptions();
		DayEventUtils.resetHudDesign();
		moduleManager.registerModules();
	}
	
	public void stopClient() {
		
		OSType os = OSType.getType();
		
		eventManager.unregister(this);
		
		if(os == OSType.WINDOWS) {
			discordManager = new DiscordManager();
			discordManager.update("Playing NekoCat Client v" + NekoCat.instance.getVersion(), "");
		}
	}
    
	@EventTarget
	public void onTick(EventTick event) {
		
    	boolean isRandom = NekoCat.instance.settingsManager.getSettingByClass(ClientMod.class, "Random").getValBoolean();
    	boolean isLoop = NekoCat.instance.settingsManager.getSettingByClass(ClientMod.class, "Loop").getValBoolean();

		if(isRandom || isLoop) {
			if(!loaded) {
				if(musicManager.getCurrentMusic() != null && musicManager.getCurrentMusic().mediaPlayer != null && musicManager.getCurrentMusic().mediaPlayer.getCurrentTime().toSeconds() >= musicManager.getCurrentMusic().mediaPlayer.getTotalDuration().toSeconds()) {
					loaded = true;
					new Thread(() -> {
						loaded = false;
						musicManager.getCurrentMusic().mediaPlayer.stop();

						if(isRandom) {
							Random random = new Random();
							int randomIndex = random.nextInt(NekoCat.instance.musicManager.getMusics().size() + 1);
							musicManager.setCurrentMusic(NekoCat.instance.musicManager.getMusics().get(randomIndex == 10 ? randomIndex - 1 : randomIndex));
						}

						musicManager.getCurrentMusic().playMusic();
					}).start();
				}
			}
		}else {
			loaded = false;
			if(musicManager.getCurrentMusic() != null && musicManager.getCurrentMusic().mediaPlayer != null && musicManager.getCurrentMusic().mediaPlayer.getCurrentTime().toSeconds() == musicManager.getCurrentMusic().mediaPlayer.getTotalDuration().toSeconds()) {
				musicManager.getCurrentMusic().mediaPlayer.stop();
			}
		}

		// try turn off optifine fast render
		try {
			ClientUtils.gameSettings_ofFastRender.set(mc.gameSettings, false);
		} catch (Exception ignored) {}
		
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
                (packet).setData(new PacketBuffer(Unpooled.buffer()).writeString("NekoCat Client v" + version));
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
    public void onRespawn(EventRespawn event) {
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

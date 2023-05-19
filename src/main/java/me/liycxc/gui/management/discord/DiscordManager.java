package me.liycxc.gui.management.discord;

import me.liycxc.NekoCat;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;

public class DiscordManager {
	
    private boolean running = true;

    private long created = 0L;

    public void start() {
        this.created = System.currentTimeMillis();
        DiscordEventHandlers handlers = (new DiscordEventHandlers.Builder()).setReadyEventHandler(new ReadyCallback() {
           public void apply(DiscordUser user) {
        	   update("Playing NekoCat Client v" + NekoCat.instance.getVersion(), "");
            }
        }).build();
        DiscordRPC.discordInitialize("977182001465536522", handlers, true);
        (new Thread(() -> {
            while (DiscordManager.this.running)
              DiscordRPC.discordRunCallbacks();
        })).start();
    }

    public void shutdown() {
        this.running = false;
        DiscordRPC.discordShutdown();
    }

    public void update(String firstLine, String secondLine) {
        DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(secondLine);
        b.setBigImage("icon", "");
        b.setDetails(firstLine);
        b.setStartTimestamps(this.created);
        DiscordRPC.discordUpdatePresence(b.build());
    }
}
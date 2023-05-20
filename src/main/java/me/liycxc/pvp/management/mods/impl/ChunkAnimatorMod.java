package me.liycxc.pvp.management.mods.impl;

import me.liycxc.NekoCat;
import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.EventPreRenderChunk;
import me.liycxc.events.impl.EventRenderChunkPosition;
import me.liycxc.pvp.management.mods.Mod;
import me.liycxc.pvp.management.mods.ModCategory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.chunk.RenderChunk;

import java.util.Map;
import java.util.WeakHashMap;

public class ChunkAnimatorMod extends Mod {

	private final Map<RenderChunk, Long> chunks = new WeakHashMap<>();
	
	public ChunkAnimatorMod() {
		super("Chunk Animator", "Animate when loading chunks", ModCategory.OTHER);
	}

	@Override
	public void setup() {
		this.addSliderSetting("Duration", this, 1, 0, 5, true);
	}
	
	@EventTarget
	public void preRenderChunk(EventPreRenderChunk event) {
		if(chunks.containsKey(event.getRenderChunk())) {
			long time = chunks.get(event.getRenderChunk());
			long now = System.currentTimeMillis();

			if(time == -1L) {
				chunks.put(event.getRenderChunk(), now);
				time = now;
			}

			long passedTime = now - time;

			if(passedTime < (int) (NekoCat.instance.settingsManager.getSettingByName(this, "Duration").getValDouble() * 1000)) {
				int chunkY = event.getRenderChunk().getPosition().getY();
				GlStateManager.translate(0, -chunkY + this.easeOut(passedTime, 0, chunkY, (int) (NekoCat.instance.settingsManager.getSettingByName(this, "Duration").getValDouble() * 1000)), 0);
			}
		}
	}
	
	@EventTarget
	public void setPosition(EventRenderChunkPosition event) {
		if(mc.thePlayer != null) {
			chunks.put(event.getRenderChunk(), -1L);
		}
	}

	private float easeOut(float t,float b , float c, float d) {
		return c * (float)Math.sin(t/d * (Math.PI/2)) + b;	
	}
}

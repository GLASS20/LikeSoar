package me.liycxc.pvp.management.mods.impl;

import me.liycxc.NekoCat;
import me.liycxc.api.events.EventTarget;
import me.liycxc.api.events.impl.EventTransformFirstPersonItem;
import me.liycxc.pvp.management.mods.Mod;
import me.liycxc.pvp.management.mods.ModCategory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;

public class OldAnimationsMod extends Mod {

	public OldAnimationsMod() {
		super("Old Animations", "Return animation to 1.7", ModCategory.RENDER);
	}

	@Override
	public void setup() {
		this.addBooleanSetting("Armor Damage", this, false);
		this.addBooleanSetting("Block Hit", this, true);
		this.addBooleanSetting("Health", this, true);
		this.addBooleanSetting("Rod", this, false);
		this.addBooleanSetting("Bow", this, false);
		this.addBooleanSetting("Sneak", this, true);
	}
	
	@EventTarget
	public void onItemTransform(EventTransformFirstPersonItem event) {
		
		boolean bow = NekoCat.instance.settingsManager.getSettingByName(this, "Bow").getValBoolean();
		boolean rod = NekoCat.instance.settingsManager.getSettingByName(this, "Rod").getValBoolean();
		
		if(!(bow || rod)) {
			return;
		}

		if(mc.thePlayer.isUsingItem() && event.getItemToRender().getItem() instanceof ItemBow) {
			if(bow) {
				GlStateManager.translate(-0.01f, 0.05f, -0.06f);
			}
		}
		else if(event.getItemToRender().getItem() instanceof ItemFishingRod) {
			if(rod) {
				GlStateManager.translate(0.08f, -0.027f, -0.33f);
				GlStateManager.scale(0.93f, 1.0f, 1.0f);
			}
		}
	}
}

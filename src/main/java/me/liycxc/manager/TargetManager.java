package me.liycxc.manager;

import me.liycxc.NekoCat;
import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.EventTick;
import me.liycxc.modules.impl.combat.KillAura;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

import static me.liycxc.NekoCat.mc;

public class TargetManager extends ConcurrentLinkedQueue<EntityLivingBase> {

    boolean players = true;
    boolean invisibles = false;
    boolean animals = false;
    boolean mobs = false;
    boolean teams = false;

    private int loadedEntitySize;

    public void init() {
        NekoCat.instance.eventManager.register(this);
    }

    @EventTarget
    public void onTick(EventTick eventTick) {
        try {
            if (mc.thePlayer.ticksExisted % 150 == 0 || loadedEntitySize != mc.theWorld.loadedEntityList.size()) {
                this.updateTargets();
                loadedEntitySize = mc.theWorld.loadedEntityList.size();
            }
        } catch (Exception e) {

        }
    }

    public void updateTargets() {
        try {
            KillAura killAura = (KillAura) NekoCat.instance.moduleManager.getModule("KillAura");
            players = killAura.player.get();
            invisibles = killAura.invisibles.get();
            animals = killAura.animals.get();
            mobs = killAura.mobs.get();
            teams = killAura.teams.get();
        } catch (Exception ignored) {
            // Don't give crackers clues...
            if (NekoCat.instance.DEVELOPMENT_SWITCH)
                ignored.printStackTrace();
        }
    }

    public List<Entity> getTargets(final double range) {
        List<Entity> list = mc.theWorld.loadedEntityList.stream()
                .filter(entity -> isSelected(entity))
                .filter(entity -> mc.thePlayer.getDistanceToEntity(entity) < range)
                .sorted(Comparator.comparingDouble(entity -> mc.thePlayer.getDistanceSqToEntity(entity)))
                .collect(Collectors.toList());
        return list;
    }

    public boolean isSelected(final Entity entity) {
        boolean toNext = entity instanceof EntityLivingBase && entity.isEntityAlive() && entity != mc.thePlayer;
        if (toNext) {
            if (invisibles || !entity.isInvisible()) {
                if (players && entity instanceof EntityPlayer) {
                    final EntityPlayer entityPlayer = (EntityPlayer) entity;
                    if(NekoCat.instance.botManager.contains(entity)) {
                        return false;
                    }
                    if(entityPlayer.isSpectator()) {
                        return false;
                    }
                    return true;
                }
                return mobs && isMob(entity) || animals && isAnimal(entity);
            }
        }
        return false;
    }

    public boolean isAnimal(final Entity entity) {
        return entity instanceof EntityAnimal || entity instanceof EntitySquid || entity instanceof EntityGolem ||
                entity instanceof EntityBat;
    }

    public boolean isMob(final Entity entity) {
        return entity instanceof EntityMob || entity instanceof EntityVillager || entity instanceof EntitySlime ||
                entity instanceof EntityGhast || entity instanceof EntityDragon;
    }

    public String getName(final NetworkPlayerInfo networkPlayerInfoIn) {
        return networkPlayerInfoIn.getDisplayName() != null ? networkPlayerInfoIn.getDisplayName().getFormattedText() :
                ScorePlayerTeam.formatPlayerName(networkPlayerInfoIn.getPlayerTeam(), networkPlayerInfoIn.getGameProfile().getName());
    }

    public int getPing(final EntityPlayer entityPlayer) {
        if(entityPlayer == null) {
            return 0;
        }
        final NetworkPlayerInfo networkPlayerInfo = mc.getNetHandler().getPlayerInfo(entityPlayer.getUniqueID());
        return networkPlayerInfo == null ? 0 : networkPlayerInfo.getResponseTime();
    }
}

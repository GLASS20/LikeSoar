package me.liycxc.manager;

import me.liycxc.NekoCat;
import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.EventTick;
import me.liycxc.modules.kinds.combat.killAura.KillAura;
import me.liycxc.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

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
        PlayerUtils.tellPlayer("l " + mc.theWorld.loadedEntityList.size() + " " + range);
        List<Entity> list = mc.theWorld.loadedEntityList.stream()
                .filter(entity -> entity != mc.thePlayer && entity != null)
                .filter(entity -> entity instanceof EntityLivingBase)
                .filter(entity -> mc.thePlayer.getDistanceToEntity(entity) < range)
                .filter(entity -> mc.theWorld.loadedEntityList.contains(entity))
                .filter(entity -> !NekoCat.instance.botManager.contains(entity))
                .sorted(Comparator.comparingDouble(entity -> mc.thePlayer.getDistanceSqToEntity(entity)))
                .collect(Collectors.toList());
        PlayerUtils.tellPlayer("Targets: " + list.size());
        return list;
    }
}

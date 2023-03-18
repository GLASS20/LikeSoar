package me.soar.management.mods.impl.hacks;

import me.soar.Soar;
import me.soar.management.events.EventTarget;
import me.soar.management.events.impl.EventRendererLivingEntity;
import me.soar.management.mods.Mod;
import me.soar.management.mods.ModCategory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;

public class ChamsMod extends Mod {
    public ChamsMod() {
        super("ChamsMod","Render", ModCategory.HACK);
    }

    @Override
    public void setup() {
        ArrayList<String> opti = new ArrayList<>();
        opti.add("Normal");
        opti.add("Colored");
        this.addModeSetting("Mode", this, "Normal", opti);
        this.addBooleanSetting("TeamColor", this, false);
        this.addBooleanSetting("Flat", this, false);
    }

    @EventTarget
    public void onRenderLivingEntity(EventRendererLivingEntity evt) {
        Color color = Color.RED;
        String mode;
        mode = Soar.instance.settingsManager.getSettingByName(this,"Mode").getValString();
        boolean flat;
        flat = Soar.instance.settingsManager.getSettingByName(this,"Flat").getValBoolean();
        boolean teamCol;
        teamCol = Soar.instance.settingsManager.getSettingByName(this,"TeamColor").getValBoolean();

        if (evt.getEntity() != mc.thePlayer) {
            if (evt.isPre()) {
                if (mode.equals("Colored")) {
                    evt.setCancelled(true);
                    try {
                        Render renderObject = mc.getRenderManager().getEntityRenderObject(evt.getEntity());
                        if (renderObject != null && mc.getRenderManager().renderEngine != null && renderObject instanceof RendererLivingEntity) {
                            GL11.glPushMatrix();
                            GL11.glDisable(GL11.GL_DEPTH_TEST);
                            GL11.glBlendFunc(770, 771);
                            GL11.glDisable(GL11.GL_TEXTURE_2D);
                            GL11.glEnable(GL11.GL_BLEND);
                            Color teamColor = null;

                            if (flat) {
                                GlStateManager.disableLighting();
                            }

                            if (teamCol) {
                                String text = evt.getEntity().getDisplayName().getFormattedText();
                                for (int i = 0; i < text.length(); i++) {
                                    if ((text.charAt(i) == (char) 0x00A7) && (i + 1 < text.length())) {
                                        char oneMore = Character.toLowerCase(text.charAt(i + 1));
                                        int colorCode = "0123456789abcdefklmnorg".indexOf(oneMore);
                                        if (colorCode < 16) {
                                            try {
                                                Color newCol = teamColor = new Color(mc.fontRendererObj.colorCode[colorCode]);
                                                GL11.glColor4f(newCol.getRed() / 255f, newCol.getGreen() / 255f, newCol.getBlue() / 255f, 1f);
                                            } catch (ArrayIndexOutOfBoundsException exception) {
                                                GL11.glColor4f(1, 0, 0, 1f);
                                            }
                                        }
                                    }
                                }
                            } else {
                                Color c = color;
                                GL11.glColor4f(c.getRed() / 255f, c.getGreen() / 500f, c.getBlue() / 500f, 1f);
                            }

                            ((RendererLivingEntity) renderObject).renderModel(evt.getEntity(), evt.getLimbSwing(), evt.getLimbSwingAmount(), evt.getAgeInTicks(), evt.getRotationYawHead(), evt.getRotationPitch(), evt.getOffset());
                            GL11.glEnable(GL11.GL_DEPTH_TEST);

                            if (teamCol && teamColor != null) {
                                GL11.glColor4f(teamColor.getRed() / 255f, teamColor.getGreen() / 255f, teamColor.getBlue() / 255f, 1f);
                            } else {
                                Color c = color;
                                GL11.glColor4f(c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f, 1f);
                            }

                            ((RendererLivingEntity) renderObject).renderModel(evt.getEntity(), evt.getLimbSwing(), evt.getLimbSwingAmount(), evt.getAgeInTicks(), evt.getRotationYawHead(), evt.getRotationPitch(), evt.getOffset());
                            GL11.glEnable(GL11.GL_TEXTURE_2D);
                            GL11.glDisable(GL11.GL_BLEND);
                            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

                            if (flat) {
                                GlStateManager.enableLighting();
                            }

                            GL11.glPopMatrix();
                            ((RendererLivingEntity) renderObject).renderLayers(evt.getEntity(), evt.getLimbSwing(), evt.getLimbSwingAmount(), mc.timer.renderPartialTicks, evt.getAgeInTicks(), evt.getRotationYawHead(), evt.getRotationPitch(), evt.getOffset());
                            GL11.glPopMatrix();
                        }
                    } catch (Exception ex) {}
                } else {
                    GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
                    GL11.glPolygonOffset(1.0F, -1100000.0F);
                }
            } else if (!mode.equals("Colored") && evt.isPost()) {
                GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
                GL11.glPolygonOffset(1.0F, 1100000.0F);
            }
        }
    }
}

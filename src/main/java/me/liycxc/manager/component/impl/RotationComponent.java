package me.liycxc.manager.component.impl;

import me.liycxc.events.EventTarget;
import me.liycxc.events.impl.*;
import me.liycxc.manager.component.Component;
import me.liycxc.modules.kinds.combat.killAura.utils.MoveUtil;
import me.liycxc.modules.kinds.combat.killAura.utils.MovementFix;
import me.liycxc.modules.kinds.combat.killAura.utils.RotationUtil;
import me.liycxc.utils.vector.Vector2f;

import static me.liycxc.NekoCat.mc;

public final class RotationComponent extends Component {
    private static boolean active = false, smoothed;
    public static Vector2f rotations, lastRotations, targetRotations, lastServerRotations;
    private static double rotationSpeed;
    private static MovementFix correctMovement;

    /*
     * This method must be called on Pre Update Event to work correctly
     */
    public static void setRotations(final Vector2f rotations, final double rotationSpeed, final MovementFix correctMovement) {
        RotationComponent.targetRotations = rotations;
        RotationComponent.rotationSpeed = rotationSpeed * 18;
        RotationComponent.correctMovement = correctMovement;
        active = true;

        smooth();
    }

    // @EventLink(value = Priorities.VERY_LOW)
    @EventTarget
    public void onUpdate (EventUpdate eventUpdate) {

        if (!active || rotations == null || lastRotations == null || targetRotations == null || lastServerRotations == null) {
            rotations = lastRotations = targetRotations = lastServerRotations = new Vector2f(mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
        }

        if (active) {
            smooth();
        }

//        mc.thePlayer.rotationYaw = rotations.x;
//        mc.thePlayer.rotationPitch = rotations.y;

        if (correctMovement == MovementFix.BACKWARDS_SPRINT && active) {
            if (Math.abs(rotations.x - Math.toDegrees(MoveUtil.direction())) > 45) {
                mc.gameSettings.keyBindSprint.setPressed(false);
                mc.thePlayer.setSprinting(false);
            }
        }
    }

//    @EventTarget
//    public void onMove (EventStrafe event) {
//        if (active && correctMovement == MovementFix.NORMAL && rotations != null) {
//            /*
//             * Calculating movement fix
//             */
//            final float yaw = rotations.x;
//            // MoveUtil.fixMovement(event, yaw);
//        }
//    }

    @EventTarget
    public void onLook (EventLook event) {
        if (active && rotations != null) {
            event.setRotation(rotations);
        }
    }

    @EventTarget
    public void onStrafe (EventStrafe event) {
        if (active && (correctMovement == MovementFix.NORMAL || correctMovement == MovementFix.TRADITIONAL) && rotations != null) {
            event.setYaw(rotations.x);
        }
    }

//    @EventTarget
//    public void onJump (EventJump event) {
//        if (active && (correctMovement == MovementFix.NORMAL || correctMovement == MovementFix.TRADITIONAL || correctMovement == MovementFix.BACKWARDS_SPRINT) && rotations != null) {
//            event.setYaw(rotations.x);
//        }
//    }

    @EventTarget
    public void onPreMotionEvent (EventMotion event) {
        if (event.eventState != EventState.PRE) {
            return;
        }
        if (active && rotations != null) {
            final float yaw = rotations.x;
            final float pitch = rotations.y;

            event.setRotationYaw(yaw);
            event.setRotationPitch(pitch);

//            mc.thePlayer.rotationYaw = yaw;
//            mc.thePlayer.rotationPitch = pitch;

            mc.thePlayer.renderYawOffset = yaw;
            mc.thePlayer.rotationYawHead = yaw;
            // mc.thePlayer.renderPitchHead = pitch;

            lastServerRotations = new Vector2f(yaw, pitch);

            if (Math.abs((rotations.x - mc.thePlayer.rotationYaw) % 360) < 1 && Math.abs((rotations.y - mc.thePlayer.rotationPitch)) < 1) {
                active = false;

                this.correctDisabledRotations();
            }

            lastRotations = rotations;
        } else {
            lastRotations = new Vector2f(mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
        }

        targetRotations = new Vector2f(mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
        smoothed = false;
    }

    private void correctDisabledRotations() {
        final Vector2f rotations = new Vector2f(mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
        final Vector2f fixedRotations = RotationUtil.resetRotation(RotationUtil.applySensitivityPatch(rotations, lastRotations));

        mc.thePlayer.rotationYaw = fixedRotations.x;
        mc.thePlayer.rotationPitch = fixedRotations.y;
    }

    public static void smooth() {
        if (!smoothed) {
            final float lastYaw = lastRotations.x;
            final float lastPitch = lastRotations.y;
            final float targetYaw = targetRotations.x;
            final float targetPitch = targetRotations.y;

            rotations = RotationUtil.smooth(new Vector2f(lastYaw, lastPitch), new Vector2f(targetYaw, targetPitch),
                    rotationSpeed + Math.random());

            if (correctMovement == MovementFix.NORMAL || correctMovement == MovementFix.TRADITIONAL) {
                mc.thePlayer.movementYaw = rotations.x;
            }

            mc.thePlayer.velocityYaw = rotations.x;
        }

        smoothed = true;

        /*
         * Updating MouseOver
         */
        mc.entityRenderer.getMouseOver(1);
    }
}

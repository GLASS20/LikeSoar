/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/SkidderMC/FDPClient/
 */
package net.ccbluex.liquidbounce.utils

import me.liycxc.utils.MSTimer
import net.minecraft.block.Block
import net.minecraft.client.Minecraft
import net.minecraft.init.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemPotion
import net.minecraft.item.ItemStack
import net.minecraft.network.play.client.C0DPacketCloseWindow
import net.minecraft.network.play.client.C16PacketClientStatus
import net.minecraft.potion.Potion

object InventoryUtils {
    val mc = Minecraft.getMinecraft()
    @JvmField
    var CLICK_TIMER = MSTimer()
    @JvmField
    var INV_TIMER = MSTimer()
    val BLOCK_BLACKLIST = listOf(Blocks.enchanting_table, Blocks.chest, Blocks.ender_chest, Blocks.trapped_chest,
        Blocks.anvil, Blocks.sand, Blocks.web, Blocks.torch, Blocks.crafting_table, Blocks.furnace, Blocks.waterlily,
        Blocks.dispenser, Blocks.stone_pressure_plate, Blocks.wooden_pressure_plate, Blocks.red_flower, Blocks.flower_pot, Blocks.yellow_flower,
        Blocks.noteblock, Blocks.dropper, Blocks.standing_banner, Blocks.wall_banner)

    fun findItem(startSlot: Int, endSlot: Int, item: Item): Int {
        for (i in startSlot until endSlot) {
            val stack = mc.thePlayer.inventoryContainer.getSlot(i).stack
            if (stack != null && stack.item === item) {
                return i
            }
        }
        return -1
    }

    fun hasSpaceHotbar(): Boolean {
        for (i in 36..44) {
            mc.thePlayer.inventoryContainer.getSlot(i).stack ?: return true
        }
        return false
    }

    fun findAutoBlockBlock(): Int {
        for (i in 36..44) {
            val itemStack = mc.thePlayer.inventoryContainer.getSlot(i).stack
            if (itemStack != null && itemStack.item is ItemBlock) {
                val itemBlock = itemStack.item as ItemBlock
                val block = itemBlock.getBlock()
                if (canPlaceBlock(block) && itemStack.stackSize > 0) {
                    return i
                }
            }
        }
        return -1
    }

    fun canPlaceBlock(block: Block): Boolean {
        return block.isFullCube && !BLOCK_BLACKLIST.contains(block)
    }

    fun isBlockListBlock(itemBlock: ItemBlock): Boolean {
        val block = itemBlock.getBlock()
        return BLOCK_BLACKLIST.contains(block) || !block.isFullCube
    }

    fun openPacket() {
        mc.netHandler.addToSendQueue(C16PacketClientStatus(C16PacketClientStatus.EnumState.OPEN_INVENTORY_ACHIEVEMENT))
    }

    fun closePacket() {
        mc.netHandler.addToSendQueue(C0DPacketCloseWindow())
    }

    fun isPositivePotionEffect(id: Int): Boolean {
        if (id == Potion.regeneration.id || id == Potion.moveSpeed.id ||
            id == Potion.heal.id || id == Potion.nightVision.id ||
            id == Potion.jump.id || id == Potion.invisibility.id ||
            id == Potion.resistance.id || id == Potion.waterBreathing.id ||
            id == Potion.absorption.id || id == Potion.digSpeed.id ||
            id == Potion.damageBoost.id || id == Potion.healthBoost.id ||
            id == Potion.fireResistance.id) {
            return true
        }
        return false
    }

    fun isPositivePotion(item: ItemPotion, stack: ItemStack): Boolean {
        item.getEffects(stack).forEach {
            if (isPositivePotionEffect(it.potionID)) {
                return true
            }
        }

        return false
    }

    fun getItemDurability(stack: ItemStack): Float {
        if (stack.isItemStackDamageable && stack.maxDamage> 0) {
            return (stack.maxDamage - stack.itemDamage) / stack.maxDamage.toFloat()
        }
        return 1f
    }

    fun swap(slot: Int, hotBarNumber: Int) {
        mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, slot, hotBarNumber, 2, mc.thePlayer)
    }
}
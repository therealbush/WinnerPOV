package me.meowcher.winnerpov.control.utils

import me.meowcher.winnerpov.Central
import net.minecraft.network.Packet

/**
 * @since      10.1-Helsinki
 * @author     мяучер (meowcher)
 */

object InteractUtils : Central {

    fun sendPackets(
        vararg packets : Packet<*>
    ) = packets.forEach { minecraft().player!!.networkHandler.sendPacket(it) }
}
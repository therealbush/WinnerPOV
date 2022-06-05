package me.meowcher.winnerpov.control.utils

import me.meowcher.winnerpov.Main
import net.minecraft.network.Packet

/**
 * @since      10.1-Helsinki
 * @author     мяучер (meowcher)
 */

object InteractUtils
{
    fun sendPacket(packet : Packet<*>)
    {
        Main.minecraft().player!!.networkHandler.sendPacket(packet)
    }

    fun sendPackets(vararg packets : Packet<*>)
    {
        for (ind in packets)
            sendPacket(ind)
    }
}
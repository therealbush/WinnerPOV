package winnerpov.utilities.player

import net.minecraft.network.Packet
import winnerpov.Global

/**
 * @version     10.0-Warshaw
 * @author      GitHub : mjaucher
 */

class UInteract
{
    companion object : Global
    {
        fun doPacketSend(Packet : Packet<*>)
        {
            player.networkHandler.sendPacket(Packet)
        }
    }
}
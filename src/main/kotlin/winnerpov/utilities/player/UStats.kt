package winnerpov.utilities.player

import com.mojang.authlib.GameProfile
import net.minecraft.client.gui.hud.DebugHud
import net.minecraft.util.math.Direction
import winnerpov.Global
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

/**
 * @version     10.1-Helsinki
 * @author      GitHub : mjaucher
 */

object UStats : Global
{
    private val me = minecraft.networkHandler?.getPlayerListEntry(player.uuid)!!
    private val profile : GameProfile = player.gameProfile

    fun getName() : String
    {
        return profile.name
    }

    fun getUUID() : String
    {
        return player.uuid.toString()
    }

    fun getDimension() : String
    {
        return UDimension.getName(UDimension.getID())
    }

    fun getPosition() : String
    {
        return "X: ${player.blockX}, Y: ${player.blockY}, Z: ${player.blockZ}"
    }

    fun getDirection() : String
    {
        return when (player.horizontalFacing) {
            Direction.NORTH -> "Z-"
            Direction.SOUTH -> "Z+"
            Direction.WEST -> "X-"
            else -> "X+"
        }
    }

    fun getTime() : String
    {
        return LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM))
    }

    fun getPing() : Int
    {
        return me.latency
    }

    fun getMode() : String
    {
        return when (me.gameMode!!.id)
        {
            1 -> "Creative"
            2 -> "Adventure"
            3 -> "Spectator"
            else -> "Survival"
        }
    }
}
package winnerpov.command.commands

import winnerpov.Global
import winnerpov.command.AbstractCommand
import winnerpov.utilities.player.UDimension
import winnerpov.utilities.screen.UChat

/**
 * Player info command.
 *
 * @version     10.1-Helsinki
 * @author      GitHub : mjaucher
 */

class InfoCommand : Global, AbstractCommand("info")
{
    override fun onCommand(Value : ArrayList<Double>)
    {
        if (Value.size == 1 && Value[0] == 1.0)
        {
            var info : ArrayList<String>

            var index = 0
            do {
                index++

                info = when (index)
                {
                    1 -> arrayListOf("Position", "X: ${player.blockX}, Y: ${player.blockY}, Z: ${player.blockZ}")
                    2 -> arrayListOf("Dimension", UDimension.getName(UDimension.getID()))
                    3 -> arrayListOf("UUID", player.uuid.toString())
                    else -> arrayListOf("Name", player.gameProfile.name)
                }

                UChat.doClientSideMessage("§a${info[0]}: ${info[1]}.", false)
                if (index == 4) break
            } while (true)
        } else error()
    }
}
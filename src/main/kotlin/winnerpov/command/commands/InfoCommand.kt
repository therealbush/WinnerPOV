package winnerpov.command.commands

import winnerpov.Global
import winnerpov.command.AbstractCommand
import winnerpov.utilities.player.UStats
import winnerpov.utilities.screen.UChat

/**
 * Player info command.
 *
 * @version     10.1-Helsinki
 * @author      GitHub : mjaucher
 */

class InfoCommand : Global, AbstractCommand(false, "info", 1)
{
    override fun onDoubleCommand(doubleValue : ArrayList<Double>)
    {
        if (doubleValue[0] == 1.0)
        {
            var info : ArrayList<String>

            var index = 0
            do {
                index++

                info = when (index)
                {
                    1 -> arrayListOf("Position", UStats.getPosition())
                    2 -> arrayListOf("Dimension", UStats.getDimension())
                    3 -> arrayListOf("UUID", UStats.getUUID())
                    else -> arrayListOf("Name", UStats.getName())
                }

                UChat.doClientSideMessage("§a${info[0]}: ${info[1]}.", false)
                if (index == 4) break
            } while (true)
        } else error()
    }
}
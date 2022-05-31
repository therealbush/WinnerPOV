package winnerpov.command.commands

import winnerpov.Global
import winnerpov.command.BooleanCommand
import winnerpov.utilities.misc.UValues
import winnerpov.utilities.player.UStats
import winnerpov.utilities.screen.UChat

/**
 * Player info command.
 *
 * @since     10.1-Helsinki
 * @author    GitHub : mjaucher
 */

class InfoCommand : Global, BooleanCommand("info", "Player info command.")
{
    override val initialize : Boolean = true

    override fun onCommand(value : Boolean)
    {
        if (value)
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
        }
    }

    override fun getHelpText() : Array<String>
    {
        return arrayOf(
            "If value equals true, some information about your player is written in the chat.",
            "${UValues.upperFirstLetter(name)} example: <$name true>"
        )
    }
}
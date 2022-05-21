package winnerpov

import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.core.config.Configurator
import winnerpov.features.command.AbstractCommand
import winnerpov.features.command.commands.*

import java.awt.Color

/**
 * Winner POV initializer.
 *
 * @version     10.0-Warshaw
 * @author      GitHub : mjaucher
 */

class WinnerPOV : ModInitializer
{
    override fun onInitialize()
    {
        Configurator.setRootLevel(Level.ALL)

        implInitialize(
            HelpCommand(), TestCommand(), VClipCommand()
        )
    }

    private fun implInitialize(vararg commands : AbstractCommand)
    {
        for (command in commands)
            commandsList.add(command)
    }

    companion object
    {
        var modColor = Color(155, 200, 255, 255)

        var commandsList : ArrayList<AbstractCommand> = ArrayList()

        fun getModID(statusID : Int) : String
        {
            val name = "Winner POV"
            val ver = "10.1-Helsinki"
            val git = "https://github.com/mjaucher/WinnerPOV"

            return when (statusID)
            {
                0 -> name
                1 -> ver
                2 -> git
                3 -> "$name - $ver"
                else -> "null!!!"
            }
        }
    }
}

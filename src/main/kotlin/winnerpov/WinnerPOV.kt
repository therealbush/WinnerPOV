package winnerpov

import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.core.config.Configurator
import winnerpov.features.command.AbstractCommand
import winnerpov.features.command.commands.HelpCommand
import winnerpov.features.command.commands.TestCommand
import winnerpov.features.command.commands.VClipCommand
import java.awt.Color
import java.util.*

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

        fun getModID(withVersion : Boolean) : String
        {
            return "Winner POV" + if (withVersion) " - 10.1-Helsinki" else ""
        }
    }
}

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

        featuresInit(
            TestCommand(),
            VClipCommand(),
            HelpCommand()
        )
    }

    private fun featuresInit(vararg commands : AbstractCommand)
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
            return "Winner POV" + if (withVersion) " - 10.0-Warshaw" else ""
        }
    }
}
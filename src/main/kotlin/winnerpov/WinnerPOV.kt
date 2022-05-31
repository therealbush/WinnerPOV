package winnerpov

import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.core.config.Configurator
import winnerpov.command.Commands
import winnerpov.utilities.system.UFile

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

        Commands.initializeCommands()

        UFile.initialize()
    }

    companion object
    {
        var modColor = Color(155, 200, 255, 255)

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
                4 -> "$name - $ver"
                else -> "null!!!"
            }
        }
    }
}

package me.meowcher.winnerpov

import me.meowcher.winnerpov.control.FileManager
import me.meowcher.winnerpov.impl.command.CmdManager
import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.core.config.Configurator

/**
 * @since      10.1-Helsinki
 * @author     мяучер (meowcher)
 */

class Initializer : ModInitializer
{
    override fun onInitialize()
    {
        Configurator.setRootLevel(Level.ALL)

        FileManager.initialize()
        CmdManager.initialize()
    }
}
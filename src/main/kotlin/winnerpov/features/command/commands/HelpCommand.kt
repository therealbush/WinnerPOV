package winnerpov.features.command.commands

import winnerpov.Global
import winnerpov.WinnerPOV
import winnerpov.features.command.AbstractCommand
import winnerpov.utilities.screen.UChat

/**
 * @version     10.0-Warshaw
 * @author      GitHub : mjaucher
 */

class HelpCommand : Global, AbstractCommand("help")
{
    private var commandsName = this.name

    override fun onCommand(Value : ArrayList<Double>)
    {
        if (Value.size == 1 && Value[0] == 1.0)
        {
            UChat.doClientSideMessage("§aCommands should start and end with \"<\" and \">\" inside the command there should be a command name and after a space value.", false)
            UChat.doClientSideMessage("§aExample: <COMMAND_NAME VALUE>.", false)

            for (command in WinnerPOV.commandsList)
                commandsName += ", ${command.name}"

            UChat.doClientSideMessage("§aCommands list: $commandsName.", false)
        } else UChat.doClientSideMessage(
            "§cCommand was written incorrectly! Example: <${this.name} 1>", true
        )
    }
}
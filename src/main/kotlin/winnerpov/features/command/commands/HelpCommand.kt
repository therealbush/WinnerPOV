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
    private var commandNames = ""

    override fun onCommand(Value : ArrayList<Double>)
    {
        if (Value.size == 1)
        {
            when (Value[0])
            {
                1.0 -> {
                    var init = 0

                    for (command in WinnerPOV.commandsList)
                        commandNames += (if (command != WinnerPOV.commandsList[0]) ", " else " ") + command.name

                    do {
                        init++

                        val message = when (init)
                        {
                            1 -> "Commands should start and end with \"<\" and \">\" inside the command there should be a command name and after a space value"
                            2 -> "Example: <COMMAND_NAME VALUE>"
                            else -> "Commands list:$commandNames"
                        }

                        UChat.doClientSideMessage("§a$message.", false)
                        if (init == 3) break
                    } while (true)
                }
                2.0 -> UChat.doClientSideMessage("§aModules Help", false)
                3.0 -> UChat.doClientSideMessage("§aHUD Help", false)
                else -> error()
            }
        } else error()
    }
}
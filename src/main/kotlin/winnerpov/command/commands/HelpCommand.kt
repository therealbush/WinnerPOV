package winnerpov.command.commands

import winnerpov.Global
import winnerpov.WinnerPOV
import winnerpov.command.AbstractCommand
import winnerpov.utilities.misc.Language
import winnerpov.utilities.screen.UChat

/**
 * @version     10.1-Helsinki
 * @author      GitHub : mjaucher
 */

class HelpCommand : Global, AbstractCommand(true, "help", 1)
{
    private val languagesArray : ArrayList<String> = ArrayList()
    private var commandNames = ""

    override fun onStringCommand(stringValue : String)
    {
        for (command in WinnerPOV.commandsList)
            commandNames += (if (command != WinnerPOV.commandsList[0]) ", " else " ") + command.name

        for (language in Language.values())
        {
            val text = "$language: ${language.id}."
            languagesArray.add(text)
        }

        val array = when (stringValue)
        {
            "head" -> arrayOf(
                    "To help with:",
                    "Translator: <help translator>.",
                    "Commands: <help commands>.",
                    "Modules: <help modules>.",
                    "HUD: <help hud>."
                )

            "translator" -> arrayOf(
                "Translator is a command that automatically translates and sends text in your chosen language.",
                "Command example: <translator LANGUAGE1 LANGUAGE2 TEXT>, <translator ru en Hello World!>.",
                "LANGUAGE1 - Source Language, LANGUAGE2 - Target Language.",
                "Here you will find all the languages id's: <help languages>."
            )

            "languages" -> languagesArray.toArray()

            "commands" -> arrayOf(
                    "Commands should start and end with \"<\" and \">\" inside the command there should be a command name and after a space value.",
                    "Example: <COMMAND_NAME VALUE>.",
                    "Commands list:$commandNames."
                )

            "modules" -> arrayOf("Modules Help.")

            "hud" -> arrayOf("HUD Help.")

            else -> arrayOf("§cCommand was written incorrectly! Example: <${this.name} head>.")
        }

        doSendMessages(array as Array<String>)
    }

    private fun doSendMessages(array : Array<String>)
    {
        for (message in array)
            UChat.doClientSideMessage("§a$message", false)
    }
}
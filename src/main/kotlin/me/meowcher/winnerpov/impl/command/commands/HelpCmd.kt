package me.meowcher.winnerpov.impl.command.commands

import me.meowcher.winnerpov.impl.command.*

/**
 * @since      10.1-Helsinki
 * @author     мяучер (meowcher)
 */

@AbstractCmd.Info(
    name = "help",
    description = "This command gives information about WinnerPOV!",
    valuesCount = 2,
    status = CmdStatus.String
)

object HelpCmd : AbstractCmd()
{
    override fun runStringCommand(values : ArrayList<String>)
    {
        var commandListString = "Command list:"

        for (command in CmdManager.cmdArrayList)
            commandListString += (
                    if (command != CmdManager.cmdArrayList[0]) ", " else " ") + command.name()

        when (values[0])
        {
            "commands" -> {
                var findAttempts = 0

                for (command in CmdManager.cmdArrayList)
                {
                    if (values[1] == command.name())
                    {
                        var helpComment = ""

                        for (ind in command.helpComments())
                            helpComment += "$ind\n"

                        success(false,
                            "*---------------------------------------------*",
                            "Name: ${command.name()}.",
                            "Description: ${command.description()}",
                            "Max number of values: ${command.valuesCount()}.",
                            "Status: ${
                                if (command.status() == CmdStatus.String) "String" 
                                else if (command.status() == CmdStatus.Numeric) "Numeric"
                                else "Boolean"
                            }.",
                            helpComment,
                            "*---------------------------------------------*"
                        )
                    } else findAttempts++
                }

                if (findAttempts == CmdManager.cmdArrayList.size)
                    error("${values[1]} does not exist!")
            }

            "get" -> {
                if (values[1] == "list")
                {
                    success(false, commandListString)
                } else error("Value ${values[1]} is not in the list of values!")
            }

            else -> error("Value ${values[0]} is not in the list of values!")
        }
    }

    override fun helpComments() : Array<String>
    {
        return arrayOf(
            "The first value is the task for the command: get, modules.",
            "From the \"get\" value you can get: list.",
            "From the \"modules\" value you will get information about the command you entered in the second value.",
            "Examples: <${this.name()} get list> or <${this.name()} commands scripts>."
        )
    }
}
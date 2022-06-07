package me.meowcher.winnerpov.command.commands

import me.meowcher.winnerpov.command.AbstractCmd
import me.meowcher.winnerpov.command.CmdManager
import me.meowcher.winnerpov.command.CmdStatus

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

object HelpCmd : AbstractCmd() {

    override fun runStringCommand(
        values : ArrayList<String>
    ) {
        var commandListString = "Command list:"

        CmdManager.cmdArrayList.forEach {
            commandListString +=
                (if (it != CmdManager.cmdArrayList[0]) ", " else " ") + it.name()
        }

        when (values[0]) {

            "commands" -> {

                var findAttempts = 0

                CmdManager.cmdArrayList.forEach {

                    if (values[1] == it.name()) {

                        var helpComment = ""

                        it.helpComments().forEach { h ->
                            helpComment += "$h\n"
                        }

                        success(
                            false,
                            "*---------------------------------------------*",
                            "Name: ${it.name()}.",
                            "Description: ${it.description()}",
                            "Max number of values: ${it.valuesCount()}.",
                            "Status: ${
                                if (it.status() == CmdStatus.String) 
                                    "String" 
                                else if (it.status() == CmdStatus.Numeric) 
                                    "Numeric"
                                else 
                                    "Boolean"
                            }.",
                            helpComment,
                            "*---------------------------------------------*"
                        )
                    } else
                        findAttempts++
                }

                if (findAttempts == CmdManager.cmdArrayList.size)
                    error("${values[1]} does not exist!")
            }

            "get" -> {
                if (values[1] == "list")
                    success(false, commandListString)
                else
                    error("Value ${values[1]} is not in the list of values!")
            }

            else ->
                error("Value ${values[0]} is not in the list of values!")
        }
    }

    override fun helpComments() : Array<String> =
        arrayOf(
            "The first value is the task for the command: get, modules.",
            "From the \"get\" value you can get: list.",
            "From the \"modules\" value you will get information about the command you entered in the second value.",
            "Examples: <${name()} get list> or <${name()} commands scripts>."
        )
}
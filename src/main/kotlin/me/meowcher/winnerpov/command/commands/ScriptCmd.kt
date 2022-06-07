package me.meowcher.winnerpov.command.commands

import me.meowcher.winnerpov.control.managers.FileManager
import me.meowcher.winnerpov.control.utils.*
import me.meowcher.winnerpov.command.AbstractCmd
import me.meowcher.winnerpov.command.CmdStatus
import me.meowcher.winnerpov.control.utils.ArrayListUtils
import java.nio.file.*

/**
 * @since      10.1-Helsinki
 * @author     мяучер (meowcher)
 */

@AbstractCmd.Info(
    name = "scripts",
    description = "Command for interacting with scripts.",
    valuesCount = 2,
    status = CmdStatus.String
)

object ScriptCmd : AbstractCmd()
{
    var scriptsArrayList = ArrayList<String>()

    override fun runStringCommand(
        values : ArrayList<String>
    ) {
        val scriptPathString =
            "${FileManager.scriptsStringPath}/${values[1]}"

        when (values[0]) {

            "run", "out" -> {

                if (values[1].endsWith(".lua")) {

                    if (FileUtils.fileExists(scriptPathString)) {

                        try {
                            if (values[0] == "run") {

                                if (!ArrayListUtils.listElementExists(scriptsArrayList, values[1])) {

                                    scriptsArrayList.add(values[1])
                                    success(
                                        true,
                                        "${values[1]} was successfully run!"
                                    )
                                } else
                                    error("\"${values[1]}\" is already running!")
                            } else {

                                if (ArrayListUtils.listElementExists(scriptsArrayList, values[1])) {

                                    scriptsArrayList = ArrayListUtils.deleteListElement(scriptsArrayList, values[1])
                                    success(
                                        true,
                                        "${values[1]} disabled!"
                                    )
                                } else
                                    error("\"${values[1]}\" is not running!")
                            }
                        } catch (exception : Exception) {

                            ChatUtils.clientMessage(
                                "§c$exception",
                                true
                            )
                        }

                    } else
                        error("\"${values[1]}\" does not exist!")
                } else
                    error("This file extension is not available!")
            }

            "create" -> {

                if (!FileUtils.fileExists(scriptPathString)) {

                    FileUtils.createFile(FileUtils.path(scriptPathString))
                    success(
                        true,
                        "${values[1]} was created successfully!"
                    )
                } else
                    error("A file already exists!")
            }

            "del", "delete" -> {

                if (FileUtils.fileExists(scriptPathString)) {

                    FileUtils.deleteFile(FileUtils.path(scriptPathString))
                    success(
                        true,
                        "File ${values[1]} has been deleted!"
                    )
                } else
                    error("\"${values[1]}\" does not exist!")
            }

            "get" -> {

                if (values[1] == "list") {

                    var scriptsListString = "Scripts list:"

                    Files.walk(Paths.get(FileManager.scriptsStringPath)).forEach {

                        if (it.toFile().isFile && it.toFile().path.endsWith(".lua"))
                            scriptsListString += (if (scriptsListString != "Scripts list:") ", " else " ") + it.fileName
                    }

                    success(
                        true,
                        "$scriptsListString."
                    )

                } else
                    error("Value ${values[1]} is not in the list of values!")
            }

            else ->
                error("Command was written incorrectly, example: <${name()} run, script.lua>")
        }
    }

    override fun helpComments() : Array<String> =
        arrayOf(
            "The first value must be task that this command will perform: run, create or delete.",
            "The second value is the name and extension of the script you want to run.",
            "Command should look like this: <${name()} run, script.lua>."
        )
}
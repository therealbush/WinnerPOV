package winnerpov.command.commands

import winnerpov.Global
import winnerpov.command.StringCommand
import winnerpov.utilities.system.UFile
import winnerpov.utilities.system.ULua
import java.util.ArrayList

/**
 * @since     10.1-Helsinki
 * @author    GitHub : mjaucher
 */

class ScriptCommand : Global, StringCommand("script", "Runs or creates a script.", 2)
{
    override val initialize : Boolean = true

    override fun onCommand(value : ArrayList<String>)
    {
        if (value[0] == "run")
        {
            if (UFile.exist(UFile.scripts + value[1]))
            {
                if (value[1].endsWith(".lua"))
                    ULua.run(value[1])
                else error("This file extension is not available!")
            } else error("\"${value[1]}\" does not exist!")
        } else if (value[0] == "create") {
            if (!UFile.exist(UFile.scripts + value[1]))
            {
                ULua.create(value[1])
            } else error("A file already exists!")
        } else error("Command was written incorrectly! Example: <${this.name} run script.lua>")
    }

    override fun getHelpText() : Array<String>
    {
        return arrayOf("kot!")
    }
}
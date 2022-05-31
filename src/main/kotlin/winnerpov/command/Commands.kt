package winnerpov.command

import winnerpov.command.commands.*

object Commands
{
    var numberCommandList : ArrayList<NumberCommand> = ArrayList()
    var stringCommandList : ArrayList<StringCommand> = ArrayList()
    var booleanCommandList : ArrayList<BooleanCommand> = ArrayList()

    fun initializeCommands()
    {
        numberCommandList =
            arrayListOf<NumberCommand>(VClipCommand())

        stringCommandList =
            arrayListOf<StringCommand>(TestCommand(), ScriptCommand())

        booleanCommandList =
            arrayListOf<BooleanCommand>(InfoCommand())
    }
}
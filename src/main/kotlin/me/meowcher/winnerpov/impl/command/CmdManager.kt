package me.meowcher.winnerpov.impl.command

import me.meowcher.winnerpov.impl.command.commands.HelpCmd
import me.meowcher.winnerpov.impl.command.commands.ScriptCmd

object CmdManager
{
    var cmdArrayList : ArrayList<AbstractCmd> = ArrayList()

    fun initialize()
    {
        arrayListOf(HelpCmd, ScriptCmd).forEach {
            cmdArrayList.add(it)
        }
    }
}

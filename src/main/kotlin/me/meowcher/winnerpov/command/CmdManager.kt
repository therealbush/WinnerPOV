package me.meowcher.winnerpov.command

import me.meowcher.winnerpov.command.commands.*

object CmdManager {

    val cmdArrayList : ArrayList<AbstractCmd> =
        arrayListOf(
            HelpCmd,
            ScriptCmd,
            TranslateCmd
        )
}

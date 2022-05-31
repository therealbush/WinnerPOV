package winnerpov.command

import winnerpov.utilities.screen.UChat

/**
 * @since     10.1-Helsinki
 * @author    GitHub : mjaucher
 */

abstract class BooleanCommand(val name : String, val description : String)
{
    abstract val initialize : Boolean

    abstract fun onCommand(value : Boolean)

    abstract fun getHelpText() : Array<String>

    open fun error()
    {
        UChat.doClientSideMessage(
            "§cCommand was written incorrectly! Example: <${this.name} true>",
            true
        )
    }

    open fun error(reason : String)
    {
        UChat.doClientSideMessage("§c$reason", true)
    }
}
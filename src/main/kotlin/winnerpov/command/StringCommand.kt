package winnerpov.command

import winnerpov.utilities.screen.UChat
import java.util.ArrayList

/**
 * @since     10.1-Helsinki
 * @author    GitHub : mjaucher
 */

abstract class StringCommand(val name : String, val description : String, val valuesCount : Int)
{
    abstract val initialize : Boolean

    abstract fun onCommand(value : ArrayList<String>)

    abstract fun getHelpText() : Array<String>

    open fun error()
    {
        UChat.doClientSideMessage(
            "§cCommand was written incorrectly! Example: <${this.name} TEXT>",
            true
        )
    }

    open fun error(reason : String)
    {
        UChat.doClientSideMessage("§c$reason", true)
    }
}
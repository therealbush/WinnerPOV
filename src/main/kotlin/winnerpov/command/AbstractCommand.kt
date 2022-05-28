package winnerpov.command

import winnerpov.utilities.screen.UChat
import java.util.ArrayList

/**
 * @version     10.0-Warshaw
 * @author      GitHub : mjaucher
 */

abstract class AbstractCommand(val name : String)
{
    abstract fun onCommand(Value : ArrayList<Double>)

    fun error()
    {
        UChat.doClientSideMessage(
            "§cCommand was written incorrectly! Example: <${this.name} 1>", true
        )
    }

    fun error(errorText : String)
    {
        UChat.doClientSideMessage(
            "§c$errorText", true
        )
    }
}
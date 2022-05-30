package winnerpov.command

import winnerpov.utilities.screen.UChat
import java.util.ArrayList
import kotlin.random.Random

/**
 * @version     10.0-Warshaw
 * @author      GitHub : mjaucher
 */

abstract class AbstractCommand(val stringCommand : Boolean,
                               val name : String,
                               val valuesCount : Int)
{
    open fun onDoubleCommand(doubleValue : ArrayList<Double>) {}

    open fun onStringCommand(stringValue : String) {}

    fun error()
    {
        var values = ""
        for (ind in 1..valuesCount)
            values += " ${Random.nextInt(-10, 10)}"

        UChat.doClientSideMessage(
            "§cCommand was written incorrectly! Example: <${this.name}${values}>",
            true
        )
    }

    fun error(errorText : String)
    {
        UChat.doClientSideMessage(
            "§c$errorText", true
        )
    }
}
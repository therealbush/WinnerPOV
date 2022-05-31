package winnerpov.command

import winnerpov.utilities.screen.UChat
import java.util.ArrayList
import kotlin.random.Random

/**
 * @since     10.1-Helsinki
 * @author    GitHub : mjaucher
 */

abstract class NumberCommand(val name : String, val description : String, val valuesCount : Int)
{
    abstract val initialize : Boolean

    abstract fun onCommand(value : ArrayList<Double>)

    abstract fun getHelpText() : Array<String>

    open fun error()
    {
        var values = ""
        for (ind in 1..valuesCount)
            values += " ${Random.nextInt(-10, 10)}"

        UChat.doClientSideMessage(
            "§cCommand was written incorrectly! Example: <${this.name}${values}>",
            true
        )
    }

    open fun error(reason : String)
    {
        UChat.doClientSideMessage("§c$reason", true)
    }
}
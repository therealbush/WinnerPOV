package winnerpov.command.commands

import winnerpov.Global
import winnerpov.command.AbstractCommand
import winnerpov.utilities.screen.UChat

/**
 * This command was created to test the Command System on how it copes with the search for values.
 * We have a conditional command : <test 10 20 30>. From beginning,
 * the system checks whether the message begins with the prefix "<" and simultaneously checks for the suffix ">" at the end,
 * after which the aliases of the command begin to be found as in the message
 * in our case, the system found the alias "test"
 * then there is an analysis of everything that is after the alias of the command and the space
 * the system found 3 digits - these are 10, 20, 30
 * (and I also want to clarify that it is not necessary to make a space after each value,
 * you can write anything and if there are numbers in this mess, then they will be considered the values of the command,
 * for example: <test vn92oufk290eje2> there are numbers 92, 290 and 2 here.)
 * these three digits will be counted as values unless their number exceeds the norm,
 * as for example in the command <vclip> one value is used there, if the number of values exceeds the permissible norm,
 * the command will not work and an error will appear.
 *
 * @version     10.0-Warshaw
 * @author      GitHub : mjaucher
 */

class TestCommand : Global, AbstractCommand("test")
{
    override fun onCommand(Value : ArrayList<Double>)
    {
        UChat.doClientSideMessage("finding value in command test:", true)

        var numberValue = 0

        if (Value.size == 0) UChat.doClientSideMessage("Value is null!", true)
        else {
            for (value in Value)
            {
                numberValue++
                UChat.doClientSideMessage("Value $numberValue = $value", true)
            }
        }
    }
}
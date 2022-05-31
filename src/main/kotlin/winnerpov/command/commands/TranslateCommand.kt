package winnerpov.command.commands

import winnerpov.Global
import winnerpov.command.AbstractCommand
import winnerpov.utilities.misc.UTranslate
import winnerpov.utilities.screen.UChat

/**
 * @version     10.1-Helsinki
 * @author      GitHub : mjaucher
 */

class TranslateCommand : Global, AbstractCommand(true, "translate", 1)
{
    override fun onStringCommand(stringValue : String)
    {
        val stringArray : Array<String> = stringValue.split(" ").toTypedArray()

        if (stringArray.size == 3)
        {
            val translateResult = UTranslate.translate(stringArray[0], stringArray[1], stringArray[2])
            UChat.doSendMessage(translateResult)
        } else error("Command was written incorrectly! Example: <${this.name} en ru Hello!>")
    }
}
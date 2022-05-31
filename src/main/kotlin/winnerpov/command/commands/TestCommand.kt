package winnerpov.command.commands

import winnerpov.Global
import winnerpov.command.StringCommand
import winnerpov.utilities.screen.UChat
import java.util.ArrayList

/**
 * Dev thinks...
 *
 * @since     10.1-Helsinki
 * @author    GitHub : mjaucher
 */

class TestCommand : Global, StringCommand("test", "test description.", 1)
{
    override val initialize : Boolean = true

    override fun onCommand(value: ArrayList<String>)
    {
        UChat.doClientSideMessage("Value: \"${value[0]}\"", true)
    }

    override fun getHelpText() : Array<String>
    {
        return arrayOf("There should be instructions for use!")
    }
}
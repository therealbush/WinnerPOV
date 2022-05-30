package winnerpov.command.commands

import winnerpov.Global
import winnerpov.command.AbstractCommand
import winnerpov.utilities.screen.UChat
import java.util.ArrayList

/**
 * Dev thinks...
 *
 * @version     10.1-Helsinki
 * @author      GitHub : mjaucher
 */

class TestCommand : Global, AbstractCommand(true, "test", 1)
{
    override fun onStringCommand(stringValue : String)
    {
        UChat.doClientSideMessage("Value: \"${stringValue}\"", true)
    }
}
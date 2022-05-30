package winnerpov.command.commands

import winnerpov.Global
import winnerpov.command.AbstractCommand
import java.util.ArrayList

/**
 * @version     10.0-Warshaw
 * @author      GitHub : mjaucher
 */

class VClipCommand : Global, AbstractCommand(false, "vclip", 1)
{
    override fun onDoubleCommand(doubleValue : ArrayList<Double>)
    {
        player.setPosition(player.x, player.y + doubleValue[0], player.z)
    }
}
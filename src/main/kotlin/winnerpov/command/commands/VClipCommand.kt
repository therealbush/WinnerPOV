package winnerpov.command.commands

import winnerpov.Global
import winnerpov.command.AbstractCommand

/**
 * @version     10.0-Warshaw
 * @author      GitHub : mjaucher
 */

class VClipCommand : Global, AbstractCommand("vclip")
{
    override fun onCommand(Value : ArrayList<Double>)
    {
        if (Value.size == 1)
        {
            player.setPosition(player.x, player.y + Value[0], player.z)
        } else error()
    }
}
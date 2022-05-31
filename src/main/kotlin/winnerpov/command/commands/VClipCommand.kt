package winnerpov.command.commands

import winnerpov.Global
import winnerpov.command.NumberCommand
import winnerpov.utilities.misc.UValues
import java.util.ArrayList

/**
 * @version     10.0-Warshaw
 * @author      GitHub : mjaucher
 */

class VClipCommand : Global, NumberCommand("vclip", "Changes your position on the Y-axis.", 1)
{
    override val initialize : Boolean = true

    override fun onCommand(value : ArrayList<Double>)
    {
        player.setPosition(player.x, player.y + value[0], player.z)
    }

    override fun getHelpText() : Array<String>
    {
        return arrayOf(
            "Value in this command has no limit.",
            "${UValues.upperFirstLetter(name)} example: <$name -10>"
        )
    }
}
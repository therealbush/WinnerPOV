package winnerpov.command.commands

import winnerpov.Global
import winnerpov.command.AbstractCommand
import java.util.ArrayList

/**
 * This command changes your yaw and pitch.
 *
 * @version     10.1-Helsinki
 * @author      GitHub : mjaucher
 */

class RotationCommand : Global, AbstractCommand(false, "rotation", 2)
{
    override fun onDoubleCommand(doubleValue : ArrayList<Double>)
    {
        if (doubleValue[0] in 0.0..360.0)
        {
            if (doubleValue[1] in 0.0..180.0)
            {
                player.pitch = doubleValue[1].toFloat() - 90.0F
            } else valueError(180.0)

            val yaw = doubleValue[0].toFloat() - 180.0F

            player.headYaw = yaw
            player.bodyYaw = yaw
            player.yaw = yaw

        } else valueError(360.0)
    }

    private fun valueError(maxValue : Double)
    {
        error("Value cannot be greater than ${maxValue.toInt()} or less than 0!")
    }
}
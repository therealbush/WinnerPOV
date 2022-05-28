package winnerpov.command.commands

import winnerpov.Global
import winnerpov.command.AbstractCommand

/**
 * This command changes your yaw and pitch.
 *
 * @version     10.1-Helsinki
 * @author      GitHub : mjaucher
 */

class RotationCommand : Global, AbstractCommand("rotation")
{
    override fun onCommand(Value : ArrayList<Double>)
    {
        if (Value.size == 2)
        {
            if (Value[0] in 0.0..360.0)
            {
                if (Value[1] in 0.0..180.0)
                {
                    player.pitch = Value[1].toFloat() - 90.0F
                } else valueError(180.0)

                val yaw = Value[0].toFloat() - 180.0F

                player.headYaw = yaw
                player.bodyYaw = yaw
                player.yaw = yaw
            } else valueError(360.0)
        } else error("Command was written incorrectly! Example: <${this.name} 180 90>")
    }

    private fun valueError(maxValue : Double)
    {
        error("Value cannot be greater than ${maxValue.toInt()} or less than 0!")
    }
}
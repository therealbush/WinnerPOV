package winnerpov.utilities.player

import winnerpov.Global

/**
 * @version     10.1-Helsinki
 * @author      GitHub : mjaucher
 */

object UDimension : Global
{
    fun getID() : Int
    {
        return if (world.dimension.isBedWorking) 1
        else if (world.dimension.isRespawnAnchorWorking) 2
        else 3
    }

    fun getName(id : Int) : String
    {
        return when (id)
        {
            3 -> "The End"
            2 -> "The Nether"
            1 -> "Overworld"
            else -> "Wrong ID"
        }
    }
}
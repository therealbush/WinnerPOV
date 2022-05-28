package winnerpov.utilities.player

import winnerpov.Global

class UDimension
{
    companion object : Global
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
}
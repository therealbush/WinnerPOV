package winnerpov.utilities.empty

/**
 * @version     10.1-Helsinki
 * @author      GitHub : mjaucher
 */


object UEmpty
{
    const val space = " "

    fun getNull() : Unit?
    {
        return null
    }

    fun getEmpty() : String
    {
        return ""
    }

    fun getSpace(count : Int) : String
    {
        var result = ""

        for (ind in 1..count)
            result += " "

        return result
    }
}
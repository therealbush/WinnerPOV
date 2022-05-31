package winnerpov.utilities.misc

object UValues
{
    fun upperFirstLetter(string : String) : String
    {
        val firstLetter = string.substring(0, 1).uppercase()

        return firstLetter + string.substring(1)
    }
}
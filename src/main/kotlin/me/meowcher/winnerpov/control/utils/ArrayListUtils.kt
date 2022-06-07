package me.meowcher.winnerpov.control.utils

object ArrayListUtils {

    fun deleteListElement(
        arrayList : ArrayList<String>,
        element : String
    ) : ArrayList<String> {

        val result : ArrayList<String> = ArrayList()

        arrayList.forEach {
            if (it != element)
                result.add(it)
        }

        return result
    }

    fun listElementExists(
        arrayList : ArrayList<String>,
        element : String
    ) : Boolean {

        var result = false

        arrayList.forEach {
            if (it == element)
                result = true
        }

        return result
    }
}
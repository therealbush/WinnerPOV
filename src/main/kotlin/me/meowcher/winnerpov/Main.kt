package me.meowcher.winnerpov

import net.minecraft.client.MinecraftClient
import java.awt.Color

/**
 * @since      10.1-Warshaw
 * @author     мяучер (meowcher)
 */

object Main
{
    fun minecraft() : MinecraftClient
    {
        return MinecraftClient.getInstance()
    }

    /*----------------------------------------------------------*/

    fun defaultColor() : Color
    {
        return Color(155, 200, 255, 255)
    }

    fun modInfo(id : Int) : String
    {
        val modName = "Winner POV"
        val modVersion = "10.09-Tampere"
        val gitHubLink = "https://github.com/mjaucher/WinnerPOV"

        return when (id)
        {
            0 -> modName
            1 -> modVersion
            2 -> gitHubLink
            4 -> "$modName - $modVersion"
            else -> "NIL"
        }
    }

    /*----------------------------------------------------------*/

    fun deleteListElement(arrayList : ArrayList<String>, element : String) : ArrayList<String>
    {
        val result : ArrayList<String> = ArrayList()

        for (ind in arrayList)
            if (ind != element)
                result.add(ind)

        return result
    }

    fun listElementExists(arrayList : ArrayList<String>, element : String) : Boolean
    {
        var result = false

        for (ind in arrayList)
            if (ind == element)
                result = true

        return result
    }
}
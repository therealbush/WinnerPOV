package me.meowcher.winnerpov.control.utils

import me.meowcher.winnerpov.Main
import net.minecraft.text.Text
import net.minecraft.text.TextColor
import net.minecraft.util.Formatting

/**
 * @since      10.1-Helsinki
 * @author     мяучер (meowcher)
 */

object ChatUtils
{
    fun clientMessage(message : String, withPrefix : Boolean)
    {
        val result = Text.literal("")

        val prefix = Text.literal("[WinnerPOV] ")
        prefix.style = prefix.style.withColor(TextColor.fromRgb(Main.defaultColor().rgb))

        val chatMessage = Text.literal(message)

        if (withPrefix) result.append(prefix)
        result.append(chatMessage).style = chatMessage.style.withFormatting(Formatting.RESET)

        Main.minecraft().inGameHud.chatHud.addMessage(result)
    }

    fun serverMessage(text : String)
    {
        Main.minecraft().player!!.sendMessage(Text.literal(text))
    }
}
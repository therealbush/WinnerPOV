package me.meowcher.winnerpov.control.utils

import me.meowcher.winnerpov.Central
import net.minecraft.text.Text
import net.minecraft.text.TextColor
import net.minecraft.util.Formatting

/**
 * @since      10.1-Helsinki
 * @author     мяучер (meowcher)
 */

object ChatUtils : Central {

    fun clientMessage(
        message : String,
        withPrefix : Boolean
    ) {
        val result = Text.literal("")

        val prefix = Text.literal("[WinnerPOV] ")

        prefix.style =
            prefix.style.withColor(TextColor.fromRgb(modColorRGB))

        val chatMessage = Text.literal(message)

        if (withPrefix)
            result.append(prefix)

        result.append(chatMessage).style =
            chatMessage.style.withFormatting(Formatting.RESET)

        minecraft().inGameHud.chatHud.addMessage(result)
    }

    fun serverMessage(
        text : String
    ) = minecraft().player!!.sendChatMessage(text)

    fun lastMessage() : String =
        minecraft().inGameHud.chatHud.messageHistory.last()
}
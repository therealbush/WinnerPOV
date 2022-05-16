package winnerpov.utilities.screen

import net.minecraft.text.LiteralText
import net.minecraft.text.TextColor
import net.minecraft.util.Formatting
import winnerpov.Global
import winnerpov.WinnerPOV

class UChat
{
    companion object : Global
    {
        fun doClientSideMessage(message : String, withPrefix : Boolean)
        {
            val result = LiteralText("")

            val prefix = LiteralText("[WinnerPOV] ")
            prefix.style = prefix.style.withColor(TextColor.fromRgb(WinnerPOV.modColor.rgb))

            val chatMessage = LiteralText(message)

            if (withPrefix) result.append(prefix)
            result.append(chatMessage).style = chatMessage.style.withFormatting(Formatting.RESET)

            minecraft.inGameHud.chatHud.addMessage(result)
        }
    }
}
package winnerpov.utilities.screen

import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket
import net.minecraft.text.LiteralText
import net.minecraft.text.TextColor
import net.minecraft.util.Formatting
import winnerpov.Global
import winnerpov.WinnerPOV
import winnerpov.utilities.player.UInteract

/**
 * @version     10.1-Helsinki
 * @author      GitHub : mjaucher
 */

object UChat : Global
{
    fun doClientSideMessage(message: String, withPrefix: Boolean)
    {
        val result = LiteralText("")

        val prefix = LiteralText("[WinnerPOV] ")
        prefix.style = prefix.style.withColor(TextColor.fromRgb(WinnerPOV.modColor.rgb))

        val chatMessage = LiteralText(message)

        if (withPrefix) result.append(prefix)
        result.append(chatMessage).style = chatMessage.style.withFormatting(Formatting.RESET)

        minecraft.inGameHud.chatHud.addMessage(result)
    }

    fun doSendMessage(text : String)
    {
        UInteract.doPacketSend(ChatMessageC2SPacket(text))
    }
}
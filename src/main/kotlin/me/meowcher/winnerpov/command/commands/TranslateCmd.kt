package me.meowcher.winnerpov.command.commands

import me.bush.translator.*
import me.meowcher.winnerpov.Central
import me.meowcher.winnerpov.command.*
import me.meowcher.winnerpov.control.utils.ChatUtils

/**
 * @since      10.1-Helsinki
 * @author     мяучер (meowcher), bush <333
 */

@AbstractCmd.Info(
    name = "translate",
    description = "Allows you to translate and send text in a chat!",
    valuesCount = 2,
    status = CmdStatus.String
)

object TranslateCmd : AbstractCmd(), Central {
    private val translator = Translator()

    override fun runStringCommand(
        values : ArrayList<String>
    ) {
        languageOf(values[0])?.let { language ->
            translator.translateBlockingCatching(values[1], language).onFailure {
                error("Could not process translation request!")
                it.printStackTrace()
            }.getOrNull()?.run {
                ChatUtils.serverMessage(translatedText)
            }
        }
    }

    override fun helpComments() : Array<String> =
        arrayOf(
            "The first value must be a language code, language, or part of a language (ru, english, hawaiia, spani).",
            "The second value is the text you want to translate.",
            "Command example: <${name()} ru, Hello World!>."
        )
}

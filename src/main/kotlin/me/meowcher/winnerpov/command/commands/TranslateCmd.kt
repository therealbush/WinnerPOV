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

    override fun runStringCommand(
        values : ArrayList<String>
    ) {
        val translator = Translator()

        try {
            var language : Language? = null

            Language.values().forEach {
                if (it.code == values[0])
                    language = it
            }

            if (language != null) {

                val translation = translator.translateBlocking(
                    values[1],
                    language!!,
                    Language.AUTO
                )

                ChatUtils.serverMessage(translation.translatedText)
            } else
                error("The language code is typed incorrectly!")
        } catch (exception : Exception) {
            exception.printStackTrace()
        }
    }

    override fun helpComments() : Array<String> =
        arrayOf(
            "The first value must be the language code (ru - Russian, en - English, es - Spanish).",
            "The second value is the text you want to translate.",
            "Command example: <${name()} ru, Hello World!>."
        )
}
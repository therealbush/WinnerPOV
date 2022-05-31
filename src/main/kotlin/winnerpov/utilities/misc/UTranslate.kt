package winnerpov.utilities.misc

import com.google.cloud.translate.Translate
import com.google.cloud.translate.TranslateOptions

object UTranslate
{
    fun translate(sourceLanguage : String, targetLanguage : String, text : String) : String
    {
        val translation = translateOptions.translate(text,
            Translate.TranslateOption.sourceLanguage(sourceLanguage),
            Translate.TranslateOption.targetLanguage(targetLanguage)
        )

        return translation.translatedText
    }

    private val translateOptions : Translate = TranslateOptions.getDefaultInstance().service
}
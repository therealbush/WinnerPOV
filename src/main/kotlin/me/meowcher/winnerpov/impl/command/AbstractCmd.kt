package me.meowcher.winnerpov.impl.command

import me.meowcher.winnerpov.control.utils.ChatUtils

/**
 * @since      10.1-Helsinki
 * @author     мяучер (meowcher)
 */

abstract class AbstractCmd
{
    @Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Info(
        val name : String,
        val description : String,
        val valuesCount : Int,
        val status : CmdStatus
    )

    private val annotation : Info =
        javaClass.getAnnotation(Info::class.java)

    open fun runStringCommand(values : ArrayList<String>) {}

    open fun runNumericCommand(values : ArrayList<Double>) {}

    open fun runBooleanCommand(values : Boolean) {}

    abstract fun helpComments() : Array<String>

    fun name() : String
    {
        return annotation.name
    }

    fun description() : String
    {
        return annotation.description
    }

    fun valuesCount() : Int
    {
        return annotation.valuesCount
    }

    fun status() : CmdStatus
    {
        return annotation.status
    }

    open fun error(reason : String)
    {
        ChatUtils.clientMessage("§c$reason", true)
    }

    open fun success(prefix : Boolean, vararg text : String)
    {
        var count = 1

        for (ind in text) {
            ChatUtils.clientMessage("§${if (count % 2 == 0) "a" else "2"}$ind", prefix)
            count++
        }
    }
}
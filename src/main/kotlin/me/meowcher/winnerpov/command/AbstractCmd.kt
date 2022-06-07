package me.meowcher.winnerpov.command

import me.meowcher.winnerpov.control.utils.ChatUtils

/**
 * @since      10.1-Helsinki
 * @author     мяучер (meowcher)
 */

abstract class AbstractCmd {

    @Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Info(
        val name : String,
        val description : String,
        val valuesCount : Int,
        val status : CmdStatus
    )

    private val annotation =
        javaClass.getAnnotation(Info::class.java)

    open fun runStringCommand(
        values : ArrayList<String>
    ) {}

    open fun runNumericCommand(
        values : ArrayList<Double>
    ) {}

    open fun runBooleanCommand(
        values : Boolean
    ) {}

    abstract fun helpComments() : Array<String>

    fun name() = annotation.name
    fun description() = annotation.description
    fun valuesCount() = annotation.valuesCount
    fun status() = annotation.status

    open fun error(
        reason : String
    ) = ChatUtils.clientMessage("§c$reason", true)

    open fun success(
        prefix : Boolean,
        vararg text : String
    ) {
        var count = 1

        text.forEach {
            ChatUtils.clientMessage("§${if (count % 2 == 0) "a" else "2"}$it", prefix)
            count++
        }
    }
}
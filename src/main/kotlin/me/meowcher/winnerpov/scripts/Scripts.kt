package me.meowcher.winnerpov.scripts

import me.meowcher.winnerpov.Central
import me.meowcher.winnerpov.control.managers.FileManager
import me.meowcher.winnerpov.control.utils.ClassUtils
import org.luaj.vm2.*
import org.luaj.vm2.lib.jse.*

/**
 * @since      10.1-Helsinki
 * @author     мяучер (meowcher)
 */

object Scripts : Central {

    private val globals = JsePlatform.standardGlobals()

    fun run(
        script : String // "script.lua"
    ) {
        coerce(globals)

        ClassUtils.arrayListPackets.forEach {
            globals.set(it.simpleName, CoerceJavaToLua.coerce(it))
        }

        globals.loadfile(
            "${FileManager.scriptsStringPath}/$script"
        ).call(LuaValue.valueOf(script))
    }

    private fun coerce(
        globals : Globals
    ) {
        globals.set("mc", CoerceJavaToLua.coerce(minecraft()))
    }
}
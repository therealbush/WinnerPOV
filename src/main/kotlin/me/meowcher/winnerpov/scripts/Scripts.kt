package me.meowcher.winnerpov.scripts

import me.meowcher.winnerpov.Main
import me.meowcher.winnerpov.control.FileManager
import me.meowcher.winnerpov.control.utils.ClassUtils
import org.luaj.vm2.LuaValue
import org.luaj.vm2.lib.jse.*

/**
 * @since      10.1-Helsinki
 * @author     мяучер (meowcher)
 */

object Scripts
{
    private val globals = JsePlatform.standardGlobals()

    fun run(script : String) // "script.lua"
    {
        globals.set("wpov", CoerceJavaToLua.coerce(Main))

        ClassUtils.arrayListPackets().forEach {
            globals.set(it.simpleName, CoerceJavaToLua.coerce(it))
        }

        globals.loadfile(
            "${FileManager.scriptsStringPath}/$script"
        ).call(LuaValue.valueOf(script))
    }
}
package winnerpov.utilities.system

import org.luaj.vm2.*
import org.luaj.vm2.lib.jse.JsePlatform

object ULua
{
    fun run(script : String) // "script.lua"
    {
        val globals : Globals = JsePlatform.standardGlobals()

        val chunk : LuaValue = globals.loadfile("${UFile.scripts}/$script")

        chunk.call(LuaValue.valueOf(script))
    }

    fun create(name : String)
    {
        UFile.create(UFile.scripts + name + ".lua")
    }
}
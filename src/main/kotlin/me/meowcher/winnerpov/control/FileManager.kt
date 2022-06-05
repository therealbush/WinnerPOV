package me.meowcher.winnerpov.control

import me.meowcher.winnerpov.Main
import me.meowcher.winnerpov.control.utils.FileUtils

/**
 * @since      10.1-Helsinki
 * @author     мяучер (meowcher)
 */

object FileManager
{
    private val initStringPath = "${Main.minecraft().runDirectory.path}/winnerpov"

    val scriptsStringPath = "$initStringPath/scripts"
    val configsStringPath = "$initStringPath/configs"

    fun initialize()
    {
        FileUtils.createFolder(FileUtils.path(initStringPath))
        FileUtils.createFolder(FileUtils.path(scriptsStringPath))

        FileUtils.createFolder(FileUtils.path(configsStringPath))
        FileUtils.createFile(FileUtils.path("$configsStringPath/scripts.json"))
    }
}
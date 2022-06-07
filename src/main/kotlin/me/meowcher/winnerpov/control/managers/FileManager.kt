package me.meowcher.winnerpov.control.managers

import me.meowcher.winnerpov.Central
import me.meowcher.winnerpov.control.utils.FileUtils

/**
 * @since      10.1-Helsinki
 * @author     мяучер (meowcher)
 */

object FileManager : Central {

    private val initStringPath = "${minecraft().runDirectory.path}/winnerpov"
    val scriptsStringPath = "$initStringPath/scripts"
    val configsStringPath = "$initStringPath/configs"

    fun initialize() {

        FileUtils.createFolder(FileUtils.path(initStringPath))
        FileUtils.createFolder(FileUtils.path(scriptsStringPath))
        FileUtils.createFolder(FileUtils.path(configsStringPath))
        FileUtils.createFile(FileUtils.path("$configsStringPath/scripts.json"))
    }
}
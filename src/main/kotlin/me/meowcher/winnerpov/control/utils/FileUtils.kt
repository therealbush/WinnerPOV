package me.meowcher.winnerpov.control.utils

import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

/**
 * @since      10.1-Helsinki
 * @author     мяучер (meowcher)
 */

object FileUtils {

    fun path(
        textPath : String
    ) : Path = Paths.get(textPath)

    fun fileExists(
        textPath : String
    ) : Boolean = try {
        File(textPath).exists()
    } catch (exception : Exception) {
        false
    }

    fun folderExists(
        path : Path
    ) : Boolean = path.toFile().exists()

    fun createFolder(
        path : Path
    ) {
        if (!folderExists(path)) path.toFile().mkdirs()
    }

    fun createFile(
        path : Path
    ) {
        try {

            if (path.toFile().createNewFile())
                println("File has been created!")
            else
                println("File cannot be created!")

        } catch (exception : Exception) {
            exception.printStackTrace()
        }
    }

    fun deleteFile(
        path : Path
    ) {
        try {

            if (path.toFile().delete())
                println("File has been deleted!")
            else
                println("File not found!")

        } catch (exception : Exception) {
            exception.printStackTrace()
        }
    }
}
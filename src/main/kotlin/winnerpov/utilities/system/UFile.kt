package winnerpov.utilities.system

import winnerpov.Global
import java.io.File
import java.io.IOException
import java.nio.file.Path
import java.nio.file.Paths

object UFile : Global
{
    private val directory = "${minecraft.runDirectory.path}/winnerpov/"
    val scripts = "${directory}scripts/"

    fun initialize()
    {
        createFolder(directory)
        createFolder(scripts)
    }

    fun exist(textPath : String) : Boolean
    {
        return try {
            File(textPath).exists()
        } catch (exception : IOException) {
            false
        }
    }

    fun delete(textPath : String)
    {
        try {
            val file = File(textPath)

            if (file.delete()) {
                println("File has been deleted!")
            } else println("File not found!")
        } catch (exception : Exception) {
            exception.printStackTrace()
        }
    }

    fun create(textPath : String)
    {
        try {
            val file = File(textPath)

            if (file.createNewFile())
                println("File has been created!")
            else println(">:(")
        } catch (exception : Exception) {
            exception.printStackTrace()
        }
    }

    fun createFolder(textPath : String)
    {
        val path = getPath(textPath)

        if (!path.toFile().exists())
            path.toFile().mkdirs()
    }

    fun getPath(textPath : String) : Path
    {
        return Paths.get(textPath)
    }
}
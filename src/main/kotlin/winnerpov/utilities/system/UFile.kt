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
        create(directory)
        create(scripts)
    }

    fun exist(textPath : String) : Boolean
    {
        return try {
            File(textPath).exists()
        } catch (exception : IOException) {
            false
        }
    }

    fun create(textPath : String)
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
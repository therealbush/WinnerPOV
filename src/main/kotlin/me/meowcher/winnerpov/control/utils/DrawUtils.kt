package me.meowcher.winnerpov.control.utils

import me.meowcher.winnerpov.Main
import net.minecraft.client.gui.DrawableHelper
import net.minecraft.client.util.math.MatrixStack
import java.awt.Color

/**
 * @since      10.1-Helsinki
 * @author     мяучер (meowcher)
 */

object DrawUtils
{
    fun drawText(text : String, color : Color, posX : Float, posY : Float)
    {
        Main.minecraft().textRenderer.drawWithShadow(MatrixStack(), text, posX, posY, color.rgb)
    }

    fun drawFill(color : Color, x1 : Int, y1 : Int, x2 : Int, y2 : Int)
    {
        DrawableHelper.fill(MatrixStack(), x1, y1, x2, y2, color.rgb)
    }
}
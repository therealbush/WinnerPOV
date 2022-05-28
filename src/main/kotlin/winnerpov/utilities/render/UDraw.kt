package winnerpov.utilities.render

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.render.*
import net.minecraft.util.math.Vec3d
import java.awt.Color

/**
 * @version     10.1-Helsinki
 * @author      GitHub : mjaucher
 */

class UDraw
{
    companion object
    {
        private fun texture(enable : Boolean)
        {
            if (enable) {
                RenderSystem.enableTexture()
                RenderSystem.disableBlend()
            } else {
                RenderSystem.disableTexture()
                RenderSystem.enableBlend()
            }
        }

        fun rect(x1 : Double, y1 : Double, x2 : Double, y2 : Double, color : Color)
        {
            val buffer = Tessellator.getInstance().buffer

            texture(false)

            RenderSystem.blendFuncSeparate(770, 771, 1, 0)

            RenderSystem.setShader {
                GameRenderer.getPositionColorShader()
            }

            buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR)

            for (init in 1..4)
            {
                val vec = when (init)
                {
                    1 -> Vec3d(x1 + y2, y1, 0.0)
                    2 -> Vec3d(x1 + y2, y1 + x2, 0.0)

                    3 -> Vec3d(x1, y1 + x2, 0.0)
                    else -> Vec3d(x1, y1, 0.0)
                }

                fill(buffer, vec.x, vec.y, color)
            }

            Tessellator.getInstance().draw()

            texture(true)
        }

        fun fill(buffer : BufferBuilder, posX : Double, posY : Double, color : Color)
        {
            buffer.vertex(posX, posY, 0.0).color(color.rgb).next()
        }
    }
}
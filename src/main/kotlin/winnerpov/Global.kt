package winnerpov

import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.client.world.ClientWorld

/**
 * @version     10.0-Warshaw
 * @author      GitHub : mjaucher
 */

interface Global
{
    val minecraft : MinecraftClient get() = MinecraftClient.getInstance()

    val player : ClientPlayerEntity get() = minecraft.player!!

    val world : ClientWorld get() = minecraft.world!!
}
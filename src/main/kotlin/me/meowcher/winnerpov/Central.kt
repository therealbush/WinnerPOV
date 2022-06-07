package me.meowcher.winnerpov

import net.fabricmc.loader.api.FabricLoader
import net.fabricmc.loader.api.Version
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.client.world.ClientWorld
import java.awt.Color

interface Central {

    fun minecraft() : MinecraftClient = MinecraftClient.getInstance()

    val player : ClientPlayerEntity get() = minecraft().player!!
    val world : ClientWorld get() = minecraft().world!!

    val modName : String get() = "Winner POV"
    val modVersion : String get() =
        FabricLoader.getInstance().getModContainer("winner-pov").get().metadata.version.toString()
    val modGitHub : String get() = "https://github.com/mjaucher/WinnerPOV"

    val modColor : Color get() = Color(155, 200, 255, 255)
    val modColorRGB : Int get() = modColor.rgb
}
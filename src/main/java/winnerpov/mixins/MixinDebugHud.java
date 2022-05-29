package winnerpov.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import winnerpov.WinnerPOV;
import winnerpov.utilities.empty.UEmpty;
import winnerpov.utilities.player.UStats;

import java.util.List;

/**
 * {@code renderLeftText()}, {@code getLeftText()} - adds new info to the debug hud.
 *
 * @version     10.1-Helsinki
 * @author      GitHub : mjaucher
 */

@Mixin({DebugHud.class}) public class MixinDebugHud
{
    @Inject(
            method = {"getLeftText"},
            at = @At(
                    value = "RETURN"
    ))

    public void getLeftText(@NotNull CallbackInfoReturnable<List<String>> cir)
    {
        for (int indB = 0; indB < 8; indB++)
        {
            cir.getReturnValue().add(indB, switch (indB) {
                case 0 -> UEmpty.INSTANCE.getSpace(WinnerPOV.Companion.getModID(4).length() + 7);
                case 2 -> "UUID: " + UStats.INSTANCE.getUUID();
                case 3 -> "Your Name: " + UStats.INSTANCE.getName();
                case 4 -> "Game Mode: " + UStats.INSTANCE.getMode();
                case 5 -> "World Time: " + UStats.INSTANCE.getTime();
                case 6 -> "Ping: " + UStats.INSTANCE.getPing();
                default -> "";
            });
        }
    }

    @Inject(
            method = {"renderLeftText"},
            at = @At(
                    value = "TAIL"
            )
    )

    public void renderLeftText(MatrixStack matrices, CallbackInfo ci)
    {
        String id = WinnerPOV.Companion.getModID(4);

        MinecraftClient.getInstance().textRenderer.draw(
                matrices, id, 2.0F, 2.0F, WinnerPOV.Companion.getModColor().getRGB()
        );
    }
}
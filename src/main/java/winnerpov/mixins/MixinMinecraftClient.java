package winnerpov.mixins;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import winnerpov.WinnerPOV;

/**
 * {@code getWindowTitle()} - changing window title.
 *
 * @version     10.0-Warshaw
 * @author      GitHub : mjaucher
 */

@Mixin({MinecraftClient.class}) public class MixinMinecraftClient
{
    @Inject(
            method = {"getWindowTitle"},
            at = @At(
                    value = "RETURN"
            ),
            cancellable = true
    )

    public void getWindowTitle(CallbackInfoReturnable<String> cir)
    {
        cir.setReturnValue(WinnerPOV.Companion.getModID(true));
    }
}
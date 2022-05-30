package winnerpov.mixins;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @version     10.1-Helsinki
 * @author      GitHub : mjaucher
 */

@Mixin({InGameHud.class}) public class MixinInGameHud
{
    @Inject(
            method = {"render"},
            at = @At(
                    value = "TAIL"
            )
    )

    public void render(MatrixStack matrices, float tickDelta, CallbackInfo ci)
    {
    }
}
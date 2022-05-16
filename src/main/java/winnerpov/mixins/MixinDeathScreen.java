package winnerpov.mixins;

import net.minecraft.client.gui.screen.*;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * {@code init()} - removes score after death.
 *
 * @version     10.0-Warshaw
 * @author      GitHub : mjaucher
 */

@Mixin({DeathScreen.class}) public class MixinDeathScreen
{
    @Shadow private Text scoreText;

    @Redirect(
            method = {"init"},
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/gui/screen/DeathScreen;scoreText:Lnet/minecraft/text/Text;"
            )
    )

    public void init(DeathScreen instance, Text value)
    {
        this.scoreText = new LiteralText("");
    }
}
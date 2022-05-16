package winnerpov.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import winnerpov.WinnerPOV;

import java.awt.*;

/**
 * {@code render} - Winner POV name and version have been added in the upper left corner of the screen.
 *
 * @version     10.0-Warshaw
 * @author      GitHub : mjaucher
 */

@Mixin({TitleScreen.class}) public class MixinTitleScreen
{
    @Final @Shadow public static final Text COPYRIGHT = new LiteralText("");
    @Shadow private String splashText = "";

    @Inject(
            method = {"render"},
            at = @At(
                    value = "TAIL"
            )
    )

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci)
    {
        Color color = WinnerPOV.Companion.getModColor();

        MinecraftClient.getInstance().textRenderer.drawWithShadow(
                matrices, WinnerPOV.Companion.getModID(true), 2, 2, color.getRGB()
        );
    }
}
package winnerpov.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.DoubleOption;
import net.minecraft.client.option.Option;
import net.minecraft.text.LiteralText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * {@code FOV} - custom parameters of the FOV slider in the game options.
 *
 * @version     10.0-Warshaw
 * @author      GitHub : mjaucher
 */

@Mixin({Option.class}) public class MixinOption
{
    @Shadow @Final public static DoubleOption FOV =
            new DoubleOption("options.fov", 0.0D, 180.0D, 1.0F,

                    (gameOptions) -> gameOptions.fov,

                    (gameOptions, fov) -> {
                        MinecraftClient.getInstance().worldRenderer.scheduleTerrainUpdate();
                        gameOptions.fov = fov;
                    },

                    (gameOptions, option) ->
                    {
                        double percent = option.getMax() / 100;

                        String text = switch ((int) (option.get(gameOptions) / percent)) {
                            case 0 -> "Minimal";
                            case 20 -> "Small";
                            case 40 -> "Medium";
                            case 60 -> "Large";
                            case 80 -> "Massive";
                            case 100 -> "Maximum";
                            default -> Integer.toString((int) option.get(gameOptions));
                        };

                        return new LiteralText("FOV: " + text);
                    });
}

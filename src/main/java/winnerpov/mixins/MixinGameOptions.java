package winnerpov.mixins;

import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * {@code fov} - default FOV value.
 *
 * @version     10.0-Warshaw
 * @author      GitHub : mjaucher
 */

@Mixin({GameOptions.class}) public class MixinGameOptions
{
    @Shadow public double fov = 90.0D;
}

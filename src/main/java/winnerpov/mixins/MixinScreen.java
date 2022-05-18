package winnerpov.mixins;

import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * {@code render} - Winner POV name and version have been added in the upper left corner of the screen.
 *
 * @version     10.1-Helsinki
 * @author      GitHub : mjaucher
 */

@Mixin({Screen.class}) public class MixinScreen
{
    @Inject(
            method = {"init(Lnet/minecraft/client/MinecraftClient;II)V"},
            at = @At(
                    value = "RETURN"
            )
    )

    private void init(MinecraftClient client, int width, int height, CallbackInfo ci)
    {
        String[] keys = {"narrator.button.language", "narrator.button.accessibility", "menu.online", "menu.options", "menu.quit"};

        Screen screen = (Screen)(Object)this;

        if (screen instanceof TitleScreen)
        {
            for (String key : keys)
            {
                for (ClickableWidget widget : Screens.getButtons(screen))
                {
                    if (widget.getMessage() instanceof TranslatableText &&
                            ((TranslatableText) widget.getMessage()).getKey() == key)
                    {
                        if (key == keys[3] || key == keys[4]) widget.y = 231;
                        else widget.visible = false;
                    }
                }
            }
        }
    }
}
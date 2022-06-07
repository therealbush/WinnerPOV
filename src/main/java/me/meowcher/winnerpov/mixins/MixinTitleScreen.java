package me.meowcher.winnerpov.mixins;

import me.meowcher.winnerpov.control.utils.DrawUtils;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.awt.*;

/**
 * @since      10.1-Helsinki
 * @author     мяучер (meowcher)
 */

@Mixin({TitleScreen.class})
public class MixinTitleScreen {

    @Inject(
            method = {"render"},
            at = @At(
                    value = "TAIL"
            )
    )

    public void render(
            MatrixStack matrices,
            int mouseX,
            int mouseY,
            float delta,
            CallbackInfo ci
    ) {
        Color color = new Color(155, 200, 255, 255);

        DrawUtils.INSTANCE.drawText(JavaCentral.modName + " - " + JavaCentral.modVersion,
                color,
                2.0F,
                2.0F
        );
    }
}

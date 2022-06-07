package me.meowcher.winnerpov.mixins;

import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @since      10.1-Helsinki
 * @author     мяучер (meowcher)
 */

@Mixin({ChatScreen.class})
public class MixinChatScreen {

    @Inject(
            method = {"keyPressed"},
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )

    public void keyPressed(
            int i,
            int j,
            int k,
            CallbackInfoReturnable<Boolean> cir
    ) {

    }
}

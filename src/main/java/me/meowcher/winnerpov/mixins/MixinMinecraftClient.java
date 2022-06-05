package me.meowcher.winnerpov.mixins;

import me.bush.eventbuskotlin.EventBus;
import me.meowcher.winnerpov.Main;
import me.meowcher.winnerpov.control.FileManager;
import me.meowcher.winnerpov.control.utils.*;
import me.meowcher.winnerpov.events.Event;
import me.meowcher.winnerpov.events.TickEvent;
import me.meowcher.winnerpov.impl.command.commands.ScriptCmd;
import me.meowcher.winnerpov.scripts.Scripts;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.profiler.Profiler;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @since      10.1-Helsinki
 * @author     мяучер (meowcher)
 */

@Mixin({MinecraftClient.class}) public class MixinMinecraftClient
{
    @Shadow private Profiler profiler;

    @Inject(
            method = {"tick()V"},
            at = @At(
                    value = "TAIL"
            ),
            cancellable = true
    )

    public void tick(CallbackInfo info)
    {
        EventBus bus = Event.INSTANCE.getBushBus();

        bus.subscribe(this);

        profiler.push("Ticks"); // --onkot

        TickEvent event = new TickEvent();
        bus.post(event);

        profiler.pop();

        if (event.getCancelled())
            info.cancel();

        bus.unsubscribe(this);

        for (String script : ScriptCmd.INSTANCE.getScriptsArrayList())
        {
            try {
                if (FileUtils.INSTANCE.fileExist(FileManager.INSTANCE.getScriptsStringPath() + "/" + script))
                    Scripts.INSTANCE.run(script);
                else
                    ScriptCmd.INSTANCE.setScriptsArrayList(
                            Main.INSTANCE.deleteListElement(ScriptCmd.INSTANCE.getScriptsArrayList(), script)
                    );

            } catch (Exception exception) {

                ChatUtils.INSTANCE.clientMessage("§c" + exception, true);
            }
        }
    }

    @Inject(
            method = {"getWindowTitle"},
            at = @At(
                    value = "RETURN"
            ),
            cancellable = true
    )

    public void getWindowTitle(@NotNull CallbackInfoReturnable<String> cir)
    {
        cir.setReturnValue(Main.INSTANCE.modInfo(4));
    }
}

package winnerpov.mixins;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import winnerpov.WinnerPOV;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import winnerpov.features.command.AbstractCommand;
import winnerpov.utilities.screen.UChat;

import java.util.ArrayList;
import java.util.regex.*;

/**
 * {@code send()} - command system. ; )
 *
 * @version     10.0-Warshaw
 * @author      GitHub : mjaucher
 */

@Mixin({ClientConnection.class}) public class MixinClientConnection
{
    @Inject(
            method = {"send(Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;)V"},
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )

    public void send(Packet<?> packet, GenericFutureListener<? extends Future<? super Void>> callback, CallbackInfo ci)
    {
        if (packet instanceof GameJoinS2CPacket)
            UChat.Companion.doClientSideMessage("For information: <help 1>.", true);

        if (packet instanceof ChatMessageC2SPacket Packet)
        {
            for (AbstractCommand command : WinnerPOV.Companion.getCommandsList())
            {
                if (Packet.getChatMessage().startsWith("<") && Packet.getChatMessage().endsWith(">"))
                {
                    String getMessage = Packet.getChatMessage();

                    ArrayList<Double> values = new ArrayList();

                    Pattern pattern = Pattern.compile("\\d+");

                    Matcher matcher = pattern.matcher(getMessage);

                    int start = 0;

                    while (matcher.find(start))
                    {
                        String value = getMessage.substring(matcher.start(), matcher.end());
                        values.add(Double.parseDouble(value));
                        start = matcher.end();
                    }

                    if (Packet.getChatMessage().startsWith("<" + command.getName()))
                    {
                        command.onCommand(values);
                    }

                    values.clear();
                    ci.cancel();
                }
            }
        }
    }
}

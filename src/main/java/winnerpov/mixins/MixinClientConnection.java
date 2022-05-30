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
import winnerpov.command.AbstractCommand;
import winnerpov.utilities.screen.UChat;

import java.util.ArrayList;

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
            UChat.INSTANCE.doClientSideMessage("For information: <help 1>.", true);

        if (packet instanceof ChatMessageC2SPacket)
        {
            String message = ((ChatMessageC2SPacket) packet).getChatMessage();

            if (message.startsWith("<") && message.endsWith(">"))
            {
                for (AbstractCommand command : WinnerPOV.Companion.getCommandsList())
                {
                    ArrayList<Double> values = new ArrayList();

                    String head = "<" + command.getName() + " ";

                    if (message.startsWith(head))
                    {
                        String valueString = message.substring(head.length(), message.length() - 1);

                        if (!command.getStringCommand())
                        {
                            try {
                                String[] valueArray = valueString.split(" ");

                                for (String value : valueArray)
                                    values.add(Double.parseDouble(value));

                                if (command.getValuesCount() == values.size())
                                    command.onDoubleCommand(values);
                                else command.error();

                            } catch (NumberFormatException e) {
                                command.error();
                            }
                        } else {
                            command.onStringCommand(valueString);
                        }
                    }

                    values.clear();
                }

                ci.cancel();
            }
        }
    }
}

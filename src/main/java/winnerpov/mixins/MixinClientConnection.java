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
import winnerpov.command.BooleanCommand;
import winnerpov.command.Commands;
import winnerpov.command.NumberCommand;
import winnerpov.command.StringCommand;
import winnerpov.utilities.screen.UChat;

import java.util.ArrayList;
import java.util.Arrays;

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
            UChat.INSTANCE.doClientSideMessage("For information: <help head>.", true);

        if (packet instanceof ChatMessageC2SPacket)
        {
            String message = ((ChatMessageC2SPacket) packet).getChatMessage();

            if (message.startsWith("<") && message.endsWith(">"))
            {
                for (NumberCommand numCommand : Commands.INSTANCE.getNumberCommandList())
                {
                    ArrayList<Double> values = new ArrayList();

                    String head = "<" + numCommand.getName() + " ";

                    if (message.startsWith(head))
                    {
                        String valueString = message.substring(head.length(), message.length() - 1);

                        try {
                            String[] valueArray = valueString.split(" ");

                            for (String value : valueArray)
                                values.add(Double.parseDouble(value));

                            if (numCommand.getInitialize())
                            {
                                if (numCommand.getValuesCount() == values.size())
                                    numCommand.onCommand(values);
                                else numCommand.error();
                            }
                        } catch (NumberFormatException e) {
                            numCommand.error();
                        }
                    }

                    values.clear();
                }

                for (StringCommand stringCommand : Commands.INSTANCE.getStringCommandList())
                {
                    ArrayList<String> values = new ArrayList();

                    String head = "<" + stringCommand.getName() + " ";

                    if (message.startsWith(head))
                    {
                        String valueString = message.substring(head.length(), message.length() - 1);

                        String[] valueArray = valueString.split(" ");

                        values.addAll(Arrays.asList(valueArray));

                        if (stringCommand.getValuesCount() == values.size()
                                && stringCommand.getInitialize())
                            stringCommand.onCommand(values);
                    }

                    values.clear();
                }

                for (BooleanCommand booleanCommand : Commands.INSTANCE.getBooleanCommandList())
                {
                    String head = "<" + booleanCommand.getName() + " ";

                    if (message.startsWith(head))
                    {
                        String valueString = message.substring(head.length(), message.length() - 1);

                        if (booleanCommand.getInitialize())
                        {
                            if (valueString.equals("true"))
                                booleanCommand.onCommand(true);
                            else if (valueString.equals("false"))
                                booleanCommand.onCommand(true);
                            else booleanCommand.error();
                        }
                    }
                }

                ci.cancel();
            }
        }
    }
}

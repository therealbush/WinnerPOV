package me.meowcher.winnerpov.mixins;

import io.netty.util.concurrent.GenericFutureListener;
import me.meowcher.winnerpov.Main;
import me.meowcher.winnerpov.impl.command.AbstractCmd;
import me.meowcher.winnerpov.impl.command.CmdManager;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.Future;
import java.util.*;

/**
 * @since      10.1-Helsinki
 * @author     мяучер (meowcher)
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
        /*
        if (packet instanceof GameJoinS2CPacket)
            Main.INSTANCE.clientMessage("For information: <help head>.", true);
         */

        if (packet instanceof ChatMessageC2SPacket pt)
        {
            String message = pt.getChatMessage();

            if (message.startsWith("<") && message.endsWith(">"))
            {
                for (AbstractCmd command : CmdManager.INSTANCE.getCmdArrayList())
                {
                    String head = "<" + command.name() + " ";

                    String valueText = message.substring(head.length(), message.length() - 1);

                    if (message.startsWith(head))
                    {
                        switch (command.status()) {
                            case String -> {
                                String[] valueArray = valueText.split(", ");

                                ArrayList<String> stringValues =
                                        new ArrayList<>(Arrays.asList(valueArray));

                                if (command.valuesCount() == stringValues.size())
                                    command.runStringCommand(stringValues);
                                else command.error("Command must have " + command.valuesCount() + " values!");

                                stringValues.clear();
                            }

                            case Numeric -> {
                                try {
                                    ArrayList<Double> numericValues = new ArrayList<>();

                                    String[] valueArray = valueText.split(" ");

                                    for (String value : valueArray)
                                        numericValues.add(Double.parseDouble(value));

                                    if (command.valuesCount() == numericValues.size())
                                        command.runNumericCommand(numericValues);
                                    else command.error("Command must have " + command.valuesCount() + " values!");

                                    numericValues.clear();
                                } catch (NumberFormatException e) {
                                    String error =
                                            "Command was written incorrectly, example: <" + command.name();

                                    for (int ind = 1; ind <= command.valuesCount(); ind++)
                                    {
                                        if (ind <= 3)
                                            error += " Value" + ind;

                                        else if (ind == command.valuesCount())
                                            error += " ... Value" + ind;
                                    }

                                    command.error(error + ">!");
                                }
                            }

                            default -> {
                                if (valueText.equals("true"))
                                {
                                    command.runBooleanCommand(true);

                                } else if (valueText.equals("false")) {
                                    command.runBooleanCommand(false);

                                } else command.error("Value can only be false or true!");
                            }
                        }
                    }
                }

                ci.cancel();
            }
        }
    }
}

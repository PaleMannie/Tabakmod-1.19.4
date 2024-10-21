package mett.palemannie.tabakmod.networking.packets;

import mett.palemannie.tabakmod.packetierung.ServerAbspieler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpuckenC2SPacket {
    public SpuckenC2SPacket(){
    }
    public SpuckenC2SPacket(FriendlyByteBuf buf){
    }
    public void toBytes(FriendlyByteBuf buf){
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){

        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()-> {

            ServerPlayer player = context.getSender();
            if(player == null) return;
            if(player.isSpectator()) return;

            ServerAbspieler.handliereSpucken(player);

        });
        return true;
    }
}

package mett.palemannie.tabakmod.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpuckTonS2CPacket{
    public SpuckTonS2CPacket(){
    }
    public SpuckTonS2CPacket(FriendlyByteBuf buf){
    }
    public void toBytes(FriendlyByteBuf buf){
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()-> {

            //a

        });
        return true;
    }
}

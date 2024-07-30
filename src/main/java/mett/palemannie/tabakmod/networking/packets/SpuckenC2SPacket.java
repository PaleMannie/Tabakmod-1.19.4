package mett.palemannie.tabakmod.networking.packets;

import mett.palemannie.tabakmod.networking.ModMessages;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.projectile.Snowball;
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
            ServerLevel sevel = player.getLevel();
            RandomSource rdm = RandomSource.create();

            Snowball snowball = new Snowball(sevel, player);
            float r = (float)rdm.nextInt(2500,6000)/10000;
            float y = player.getYRot();
            float x = player.getXRot();
            float z = 0f;
            snowball.shootFromRotation(player, x, y, z, r, 1f);
            sevel.addFreshEntity(snowball);

            //ModMessages.sendToPlayer(new SpuckEffektS2CPacket(), player);

        });
        return true;
    }
}

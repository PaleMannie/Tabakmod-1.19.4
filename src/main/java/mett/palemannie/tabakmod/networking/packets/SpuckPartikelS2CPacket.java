package mett.palemannie.tabakmod.networking.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpuckPartikelS2CPacket{
    public SpuckPartikelS2CPacket(){
    }

    public SpuckPartikelS2CPacket(FriendlyByteBuf buf){
    }
    public void toBytes(FriendlyByteBuf buf){
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()-> {

            ServerPlayer sPlayer = context.getSender();
            Player player = Minecraft.getInstance().player; //Attempted to load class net/minecraft/client/player/LocalPlayer for invalid dist DEDICATED_SERVER
            Level level = Minecraft.getInstance().level;

            RandomSource rdm = RandomSource.create();
            float r = rdm.nextInt(80, 120) / 100f;
            //player.playSound(SoundEvents.LLAMA_SPIT, 1f, r);

            //Minecraft.getInstance().getSoundManager().play(new SimpleSoundInstance(SoundEvents.LLAMA_SPIT, SoundSource.PLAYERS, 1f, 1f, rdm, sPlayer.getX(), sPlayer.getY(), sPlayer.getZ()));
            //Attempted to load class net/minecraft/client/resources/sounds/SoundInstance for invalid dist DEDICATED_SERVER

            Vec3 MausPos = player.getEyePosition();
                Vec3 SchauWinkel = player.getLookAngle();
                level.addParticle(ParticleTypes.SPIT, true,
                        MausPos.x, MausPos.y, MausPos.z,
                        SchauWinkel.x/4, SchauWinkel.y/4, SchauWinkel.z/4);



        });
        return true;
    }

}

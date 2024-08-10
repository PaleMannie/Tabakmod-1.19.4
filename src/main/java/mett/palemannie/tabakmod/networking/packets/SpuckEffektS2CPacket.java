package mett.palemannie.tabakmod.networking.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpuckEffektS2CPacket {
    public SpuckEffektS2CPacket(){
    }
    public SpuckEffektS2CPacket(FriendlyByteBuf buf){
    }
    public void toBytes(FriendlyByteBuf buf){
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()-> {

            //Attempted to load class net/minecraft/client/player/LocalPlayer for invalid dist DEDICATED_SERVER

            Player player = Minecraft.getInstance().player;
            Level level = Minecraft.getInstance().level;

                RandomSource rdm = RandomSource.create();
                float r = rdm.nextInt(80, 120) / 100f;

                Vec3 MausPos = player.getEyePosition();
                Vec3 SchauWinkel = player.getLookAngle();
                level.addParticle(ParticleTypes.SPIT, true,
                        MausPos.x, MausPos.y - 0.15d, MausPos.z,
                        SchauWinkel.x / 10, SchauWinkel.y / 10, SchauWinkel.z / 10);
                player.playSound(SoundEvents.LLAMA_SPIT, r, r);

        });
        return true;
    }
}

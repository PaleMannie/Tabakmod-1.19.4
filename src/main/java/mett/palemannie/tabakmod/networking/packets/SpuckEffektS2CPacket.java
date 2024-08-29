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

            //Player pl = context.getSender();
            Player pl = Minecraft.getInstance().player; //Attempted to load class net/minecraft/client/player/LocalPlayer for invalid dist DEDICATED_SERVER
            Level lvl = Minecraft.getInstance().level;
            //Level lvl = pl.getLevel();


                RandomSource rdm = RandomSource.create();
                float r = rdm.nextInt(80, 120) / 100f;
                pl.playSound(SoundEvents.LLAMA_SPIT, 1f, r);
                Vec3 MausPos = pl.getEyePosition();
                Vec3 SchauWinkel = pl.getLookAngle();
                lvl.addParticle(ParticleTypes.SPIT, true,
                        MausPos.x, MausPos.y, MausPos.z,
                        SchauWinkel.x/4, SchauWinkel.y/4, SchauWinkel.z/4);

        });
        return true;
    }
}

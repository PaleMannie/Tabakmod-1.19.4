package mett.palemannie.tabakmod.packetierung;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ServerAbspieler {

    public static void handliereSpucken(ServerPlayer player){

        if(player.isSpectator()) return;

        Level lvl = player.level;

        //Sounds
        double posX = player.getX();
        double posY = player.getY() + player.getEyeHeight();
        double posZ = player.getZ();
        float r = 0.8f + lvl.random.nextFloat() * 0.3f;

        lvl.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.LLAMA_SPIT, SoundSource.BLOCKS, 1f, r);

        //Particles
        Vec3 vec3 = player.getViewVector(1f);
        Vec3 MausPos = player.getEyePosition();
        Vec3 SchauWinkel = player.getLookAngle();

        double x = player.getX() + vec3.x/4;
        double y = MausPos.y + vec3.y/4;
        double z = player.getZ() + vec3.z/4;

        if(lvl instanceof ServerLevel slevel) {
            slevel.sendParticles(ParticleTypes.SPIT, x, y, z, 3, 0d, 0d, 0d,0.15d);
        }

    }

}

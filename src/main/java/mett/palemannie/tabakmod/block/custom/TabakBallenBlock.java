package mett.palemannie.tabakmod.block.custom;

import mett.palemannie.tabakmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class TabakBallenBlock extends Block {
    public TabakBallenBlock(Properties p_49795_) {
        super(p_49795_);
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 20;
    }
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 60;
    }

    @Override
    public void fallOn(Level level, BlockState blockState, BlockPos blockPos, Entity entity, float v) {
        entity.causeFallDamage(v, 0.6f, level.damageSources().fall());
    }
    //------------------------------------------------------------------------------------------------------------------,

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        float chance = 0.4f;
        double dx = random.nextGaussian() * 0.055d;
        double dy = random.nextGaussian() * 0.06d;
        double dz = random.nextGaussian() * 0.055d;
        if(chance >= random.nextFloat() && level.getRawBrightness(pos.above(),0)<=5 &&
                level.getRawBrightness(pos.north(),0)<=5 &&
                level.getRawBrightness(pos.east(),0)<=5 &&
                level.getRawBrightness(pos.south(),0)<=5 &&
                level.getRawBrightness(pos.west(),0)<=5 &&
                level.getRawBrightness(pos.below(),0)<=5){
        level.addParticle(ParticleTypes.SMOKE, pos.getX()+0.5d, pos.getY()+0.5d, pos.getZ()+0.5d, dx, dy, dz);
        }
        super.animateTick(state, level, pos, random);
    }

    @Override
    public boolean isRandomlyTicking(BlockState p_49921_) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        float chance = 0.05f;
        if(chance >= random.nextFloat() && level.getRawBrightness(pos.above(),0)<=5 &&
                level.getRawBrightness(pos.north(),0)<=5 &&
                level.getRawBrightness(pos.east(),0)<=5 &&
                level.getRawBrightness(pos.south(),0)<=5 &&
                level.getRawBrightness(pos.west(),0)<=5 &&
                level.getRawBrightness(pos.below(),0)<=5) {
            level.setBlockAndUpdate(pos, ModBlocks.HELLER_TABAKBALLEN.get().defaultBlockState());
            level.playSound(null,pos, SoundEvents.COMPOSTER_READY, SoundSource.BLOCKS,1f, 1.5f);
            }
            super.randomTick(state, level, pos, random);
    }
    //------------------------------------------------------------------------------------------------------------------
}
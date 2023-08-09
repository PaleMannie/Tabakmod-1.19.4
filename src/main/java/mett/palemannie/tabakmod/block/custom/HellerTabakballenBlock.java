package mett.palemannie.tabakmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class HellerTabakballenBlock extends Block {
    public HellerTabakballenBlock(Properties p_49795_) {
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
        entity.causeFallDamage(v, 0.5f, level.damageSources().fall());
    }
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public void animateTick(BlockState p_220827_, Level level, BlockPos p_220829_, RandomSource p_220830_) {
        
        super.animateTick(p_220827_, level, p_220829_, p_220830_);
    }

    @Override
    public void tick(BlockState p_222945_, ServerLevel p_222946_, BlockPos p_222947_, RandomSource p_222948_) {
        super.tick(p_222945_, p_222946_, p_222947_, p_222948_);
    }

    //------------------------------------------------------------------------------------------------------------------

}
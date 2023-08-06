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

import java.util.Random;

public class ModTabakballenGaerungUndFlammbarkeitBlock extends Block implements GaerenderTabak {
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
    private final GaerStatus gaerStatus;
    public ModTabakballenGaerungUndFlammbarkeitBlock(GaerStatus gaerStatus, Properties properties) {
        super(properties);
        this.gaerStatus = gaerStatus;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return GaerenderTabak.getNext(state.getBlock()).isPresent();
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);
    }

    public GaerStatus getAge(){
        return gaerStatus;
    }
    //------------------------------------------------------------------------------------------------------------------

}
package mett.palemannie.tabakmod.block.custom;

import mett.palemannie.tabakmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TabakblaetterStadium0Block extends Block {
    //------------------------------------------------------------------------------------------------------------------
    public TabakblaetterStadium0Block(Properties pProperties) {
        super(pProperties);
    }
    public static final VoxelShape SHAPE = Block.box(0d,0d,0d, 16d, 4d, 16d);
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return !pLevel.isEmptyBlock(pPos.below());
    }
    @Override
    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
        return true;
    }
    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 50;
    }
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) { return 120; }
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean isRandomlyTicking(BlockState pState) { return true; }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        float chance = 0.05f;
        if(chance >= pRandom.nextFloat() && pLevel.isDay() && pLevel.canSeeSky(pPos) && !pLevel.isRaining()){
            pLevel.setBlockAndUpdate(pPos, ModBlocks.TABAKBLAETTER_TEIL_GETROCKNET.get().defaultBlockState());
            pLevel.playSound(null, pPos, SoundEvents.LEASH_KNOT_BREAK, SoundSource.BLOCKS,1f,1f);
        }
        super.randomTick(pState, pLevel, pPos, pRandom);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        float chance = 0.2f;
        float px = pRandom.nextFloat();
        float pz = pRandom.nextFloat();
        if(chance >= pRandom.nextFloat() && pLevel.isDay() && pLevel.canSeeSky(pPos) && !pLevel.isRaining()){
            pLevel.addParticle(ParticleTypes.CRIT, pPos.getX()+px, pPos.getY()+0.15f, pPos.getZ()+pz, 0, 0.2d, 0);
        }
        super.animateTick(pState, pLevel, pPos, pRandom);
    }

}

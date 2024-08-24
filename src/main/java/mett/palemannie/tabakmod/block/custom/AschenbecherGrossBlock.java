package mett.palemannie.tabakmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AschenbecherGrossBlock extends Block {
    public AschenbecherGrossBlock(Properties pProperties) {
        super(pProperties);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final VoxelShape SHAPE = Block.box(2.75d,0d,2.75d, 13.25d, 1d, 13.25d);
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) { return SHAPE; }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    void raucheAmbiente(Level level, BlockPos pos, RandomSource rnd){
        float chance = 0.25f;
        double rx=rnd.nextGaussian()/10;
        double rz=rnd.nextGaussian()/10;
        if(chance >= rnd.nextFloat()){
        level.addParticle(ParticleTypes.SMOKE,
                pos.getX()+0.5d+rx, pos.getY()+0.1d, pos.getZ()+0.5d+rz,
                0d, 0.0001d, 0d);}
    }
    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
            raucheAmbiente(pLevel, pPos,pRandom);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return !pLevel.isEmptyBlock(pPos.below());
    }
    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
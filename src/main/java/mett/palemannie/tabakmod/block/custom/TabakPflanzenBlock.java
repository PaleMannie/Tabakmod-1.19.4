package mett.palemannie.tabakmod.block.custom;

import mett.palemannie.tabakmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TabakPflanzenBlock extends CropBlock {
    public TabakPflanzenBlock(Properties pProperties) { super(pProperties); }
        private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(5.0D, 0.0D, 5.0D, 11.0D, 4.0D, 11.0D),
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 8.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 10.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};



    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_BY_AGE[pState.getValue(this.getAgeProperty())];
    }
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 6);


    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.TABAKSAMEN.get();
    }

    @Override
    protected int getBonemealAgeIncrease(Level pLevel) { return Mth.nextInt(pLevel.random, 2, 4); }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 6;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(BlockTags.DIRT);
    }
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 40;
    }
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 80;
    }
}
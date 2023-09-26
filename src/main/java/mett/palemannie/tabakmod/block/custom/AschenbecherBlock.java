package mett.palemannie.tabakmod.block.custom;

import mett.palemannie.tabakmod.block.ModBlocks;
import mett.palemannie.tabakmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AschenbecherBlock extends Block {
    public AschenbecherBlock(Properties pProperties) {
        super(pProperties);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final VoxelShape SHAPE = Block.box(5.5d,0d,5.5d, 10.5d, 1.25d, 10.5d);
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) { return SHAPE; }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack stack = pPlayer.getItemInHand(pHand);

        if(!pLevel.isClientSide && (pHand == InteractionHand.MAIN_HAND || pHand == InteractionHand.OFF_HAND)){
        if(stack.is(ModItems.ZIGARETTE.get())){
            System.out.println("CRUELTY SQUAD");
            pLevel.setBlockAndUpdate(pPos, ModBlocks.ASCHENBECHER_ZIG.get().defaultBlockState());
            if(pHand == InteractionHand.OFF_HAND){ pPlayer.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);}
            if(pHand == InteractionHand.MAIN_HAND){ pPlayer.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);}
            if(pState.is(ModBlocks.ASCHENBECHER_ZIGRR.get())){ pPlayer.setItemInHand(pHand, new ItemStack(ModItems.ZIGARRE.get())); }
            if(pState.is(ModBlocks.ASCHENBECHER_ZIG.get())){ pPlayer.setItemInHand(pHand, new ItemStack(ModItems.ZIGARETTE.get())); }
            return InteractionResult.SUCCESS;
         }
        if(stack.is(ModItems.ZIGARRE.get())){
            System.out.println("CRUELTY SQUAD 2");
            pLevel.setBlockAndUpdate(pPos, ModBlocks.ASCHENBECHER_ZIGRR.get().defaultBlockState());
            if(pHand == InteractionHand.MAIN_HAND){ pPlayer.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);}
            if(pHand == InteractionHand.OFF_HAND){ pPlayer.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);}
            if(pState.is(ModBlocks.ASCHENBECHER_ZIG.get())){ pPlayer.setItemInHand(pHand, new ItemStack(ModItems.ZIGARETTE.get())); }
            if(pState.is(ModBlocks.ASCHENBECHER_ZIGRR.get())){ pPlayer.setItemInHand(pHand, new ItemStack(ModItems.ZIGARRE.get())); }
            return InteractionResult.SUCCESS;
         }
        if(stack.is(ItemStack.EMPTY.getItem())){
            System.out.println("LMAO WHO DID THIS");
            if(pState.is(ModBlocks.ASCHENBECHER_ZIG.get())){
                pLevel.setBlockAndUpdate(pPos, ModBlocks.ASCHENBECHER.get().defaultBlockState());
                pPlayer.setItemInHand(pHand, new ItemStack(ModItems.ZIGARETTE.get()));
            }
            if(pState.is(ModBlocks.ASCHENBECHER_ZIGRR.get())){
                System.out.println("AHAHHAAHAHAHAAHAHAHA");
                pLevel.setBlockAndUpdate(pPos, ModBlocks.ASCHENBECHER.get().defaultBlockState());
                pPlayer.setItemInHand(pHand, new ItemStack(ModItems.ZIGARRE.get()));
            }
         }
        }
        return InteractionResult.PASS;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    void raucheAmbiente(Level level, BlockPos pos, RandomSource rnd){
        float chance = 0.33f;
        double rx=rnd.nextGaussian()/100;
        double rz=rnd.nextGaussian()/100;
        if(chance >= rnd.nextFloat()){
        level.addParticle(ParticleTypes.SMOKE,
                pos.getX()+0.5d+rx, pos.getY()+0.1d, pos.getZ()+0.5d+rz,
                0d, 0.0005d, 0d);}
    }
    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if(this == ModBlocks.ASCHENBECHER_ZIG.get()){
            raucheAmbiente(pLevel, pPos, pRandom);
        }
        if(this == ModBlocks.ASCHENBECHER_ZIGRR.get()){
            raucheAmbiente(pLevel, pPos,pRandom);
        }
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
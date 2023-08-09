package mett.palemannie.tabakmod.block.custom;

import mett.palemannie.tabakmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

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
        entity.causeFallDamage(v, 0.5f, level.damageSources().fall());
    }
    //------------------------------------------------------------------------------------------------------------------

    public int i=100;

    @Override
    public void animateTick(BlockState p_220827_, Level p_220828_, BlockPos p_220829_, RandomSource p_220830_) {
        i--;
        System.out.println(i);
        if(i<0){System.out.println("BEREIT");
            }
        super.animateTick(p_220827_, p_220828_, p_220829_, p_220830_);
    }

    @Override
    public void tick(BlockState p_222945_, ServerLevel p_222946_, BlockPos p_222947_, RandomSource p_222948_) {

        super.tick(p_222945_, p_222946_, p_222947_, p_222948_);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult blockHitResult) {
        // Server: Main Hand & Off Hand
        // Client: Main Hand & Off Hand
        if(i<0){
            player.sendSystemMessage(Component.literal("Bereit"));
        }
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND && i>0) {
            player.sendSystemMessage(Component.literal("Nicht bereit"));
        }
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND && i<0){
        player.sendSystemMessage(Component.literal("Tadaa"));
            level.setBlock(pos, ModBlocks.HELLER_TABAKBALLEN.get().defaultBlockState(),1); }
        return super.use(state, level, pos, player, hand, blockHitResult);
    }
    //------------------------------------------------------------------------------------------------------------------

}
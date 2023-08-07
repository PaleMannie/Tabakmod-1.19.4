package mett.palemannie.tabakmod.block.custom;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;
import java.util.function.Supplier;

public interface FermentingTobacco extends ChangeOverTimeBlock<FermentingTobacco.FermentState> {

    BiMap<Block,Block> NEXT_BY_BLOCK = HashBiMap.create();
    Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(NEXT_BY_BLOCK::inverse);

    static Optional<Block> getPrevious(Block block) {
        return Optional.ofNullable(PREVIOUS_BY_BLOCK.get().get(block));
    }
    static Block getFirst(Block block){
        Block firstBlock = block;
        for(Block testBlock = PREVIOUS_BY_BLOCK.get().get(block); testBlock != null; testBlock = PREVIOUS_BY_BLOCK.get().get(testBlock)){
            firstBlock = testBlock;
        }
        return firstBlock;
    }
    static Optional<BlockState> getPrevious(BlockState blockState) {
        return getPrevious(blockState.getBlock()).map((block) -> block.withPropertiesOf(blockState));
    }

    static Optional<Block> getNext(Block block) {
        return Optional.ofNullable(NEXT_BY_BLOCK.get(block));
    }

    static BlockState getFirst(BlockState blockState) {
        return getFirst(blockState.getBlock()).withPropertiesOf(blockState);
    }

    default Optional<BlockState> getNext(BlockState blockState) {
        return getNext(blockState.getBlock()).map((block) -> block.withPropertiesOf(blockState));
    }

    default float getChanceModifier(){
        return this.getAge() == FermentState.DRIED ? 0.75F : 1.0F;
    }

    enum FermentState {
        DRIED,
        LIGHT,
        MEDIUM,
        DARK

    }
}

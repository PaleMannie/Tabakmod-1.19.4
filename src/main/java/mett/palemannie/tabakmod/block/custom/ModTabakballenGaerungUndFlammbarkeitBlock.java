package mett.palemannie.tabakmod.block.custom;

import mett.palemannie.tabakmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.fml.common.Mod;


public class ModTabakballenGaerungUndFlammbarkeitBlock extends Block {
    public ModTabakballenGaerungUndFlammbarkeitBlock(Properties properties) {
        super(properties);
    }
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
    protected int gaerZeit = 100;
    public static final int TABAK_GETROCKNET = 0;
    public static final int TABAK_HELL = 1;
    public static final int TABAK_MITTEL = 2;
    public static final int TABAK_DUNKEL = 3;



    public static final IntegerProperty GAERSTATUS = IntegerProperty.create("gaerstatus",0,3);

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {
        gaerZeit--;
        if(gaerZeit==0){
            if(pos.equals(ModBlocks.GETROCKNETER_TABAKBALLEN)){
            getrocknetZuHell(level, pos, state);}
        } else if (pos.equals(ModBlocks.HELLER_TABAKBALLEN)) {
            hellZuMittel(level, pos, state);
        } else if (pos.equals(ModBlocks.MITTLERER_TABAKBALLEN)) {
            mittelZuDunkel(level, pos, state);
        }


        super.tick(state, level, pos, randomSource);
    }

    public void getrocknetZuHell(Level level, BlockPos pos, BlockState state){
        level.setBlock(pos, ModBlocks.GETROCKNETER_TABAKBALLEN.get().defaultBlockState().setValue(GAERSTATUS, TABAK_HELL),1);
    }
    public void hellZuMittel(Level level, BlockPos pos, BlockState state){
        level.setBlock(pos, ModBlocks.HELLER_TABAKBALLEN.get().defaultBlockState().setValue(GAERSTATUS, TABAK_MITTEL),2);
    }
    public void mittelZuDunkel(Level level, BlockPos pos, BlockState state){
        level.setBlock(pos, ModBlocks.MITTLERER_TABAKBALLEN.get().defaultBlockState().setValue(GAERSTATUS, TABAK_DUNKEL),3);
    }
}
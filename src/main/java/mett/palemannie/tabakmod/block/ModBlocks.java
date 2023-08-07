package mett.palemannie.tabakmod.block;

import mett.palemannie.tabakmod.TabakMod;
import mett.palemannie.tabakmod.block.custom.FermentingTobacco;
import mett.palemannie.tabakmod.block.custom.FermentingTobaccoBlock;
import mett.palemannie.tabakmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
        DeferredRegister.create(ForgeRegistries.BLOCKS, TabakMod.MODID);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    public static final RegistryObject<Block> DRIED_TOBACCO_BALE = registerBlock("getrockneter_tabakballen",
            () -> new FermentingTobaccoBlock(FermentingTobacco.FermentState.DRIED,BlockBehaviour.Properties.of(Material.LEAVES)
                    .strength(0.5F).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> LIGHT_TOBACCO_BALE = registerBlock("heller_tabakballen",
            () -> new FermentingTobaccoBlock(FermentingTobacco.FermentState.LIGHT, BlockBehaviour.Properties.of(Material.LEAVES)
                    .strength(0.5F).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> MEDIUM_TOBACCO_BALE = registerBlock("mittlerer_tabakballen",
            () -> new FermentingTobaccoBlock(FermentingTobacco.FermentState.MEDIUM,BlockBehaviour.Properties.of(Material.LEAVES)
                    .strength(0.5F).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> DARK_TOBACCO_BALE = registerBlock("dunkler_tabakballen",
            () -> new FermentingTobaccoBlock(FermentingTobacco.FermentState.DARK,BlockBehaviour.Properties.of(Material.LEAVES)
                    .strength(0.5F).sound(SoundType.GRASS)));

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}

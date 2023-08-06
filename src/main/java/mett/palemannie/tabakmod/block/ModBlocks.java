package mett.palemannie.tabakmod.block;

import mett.palemannie.tabakmod.TabakMod;
import mett.palemannie.tabakmod.block.custom.GaerenderTabak;
import mett.palemannie.tabakmod.block.custom.ModTabakballenGaerungUndFlammbarkeitBlock;
import mett.palemannie.tabakmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
    public static final RegistryObject<Block> GETROCKNETER_TABAKBALLEN = registerBlock("getrockneter_tabakballen",
            () -> new ModTabakballenGaerungUndFlammbarkeitBlock(GaerenderTabak.GaerStatus.GETROCKNET,BlockBehaviour.Properties.of(Material.LEAVES)
                    .strength(0.5F).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> HELLER_TABAKBALLEN = registerBlock("heller_tabakballen",
            () -> new ModTabakballenGaerungUndFlammbarkeitBlock(GaerenderTabak.GaerStatus.HELL, BlockBehaviour.Properties.of(Material.LEAVES)
                    .strength(0.5F).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> MITTLERER_TABAKBALLEN = registerBlock("mittlerer_tabakballen",
            () -> new ModTabakballenGaerungUndFlammbarkeitBlock(GaerenderTabak.GaerStatus.MITTEL,BlockBehaviour.Properties.of(Material.LEAVES)
                    .strength(0.5F).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> DUNKLER_TABAKBALLEN = registerBlock("dunkler_tabakballen",
            () -> new ModTabakballenGaerungUndFlammbarkeitBlock(GaerenderTabak.GaerStatus.DUNKEL,BlockBehaviour.Properties.of(Material.LEAVES)
                    .strength(0.5F).sound(SoundType.GRASS)));

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}

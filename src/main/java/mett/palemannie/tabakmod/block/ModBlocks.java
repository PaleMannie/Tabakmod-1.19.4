package mett.palemannie.tabakmod.block;

import mett.palemannie.tabakmod.TabakMod;
import mett.palemannie.tabakmod.block.custom.*;
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

    public static final RegistryObject<Block> GETROCKNETER_TABAKBLATT = registerBlock("getrockneter_tabakblatt",
            () -> new GetrTabakblattBlock(BlockBehaviour.Properties.of(Material.PLANT)
                    .sound(SoundType.VINE).instabreak().noOcclusion()));
    public static final RegistryObject<Block> FAST_GETROCKNETER_TABAKBLATT = registerBlock("fast_getrockneter_tabakblatt",
            () -> new FstGetrTabakblattBlock(BlockBehaviour.Properties.of(Material.PLANT)
                    .sound(SoundType.VINE).instabreak().noOcclusion()));
    public static final RegistryObject<Block> HALB_GETROCKNETER_TABAKBLATT = registerBlock("halb_getrockneter_tabakblatt",
            () -> new HlbGetrTabakblattBlock(BlockBehaviour.Properties.of(Material.PLANT)
                    .sound(SoundType.VINE).instabreak().noOcclusion()));
    public static final RegistryObject<Block> LEICHT_GETROCKNETER_TABAKBLATT = registerBlock("leicht_getrockneter_tabakblatt",
            () -> new LchtGetrTabakBlattBlock(BlockBehaviour.Properties.of(Material.PLANT)
                    .sound(SoundType.VINE).instabreak().noOcclusion()));
    public static final RegistryObject<Block> ROHER_TABAKBLATT = registerBlock("roher_tabakblatt",
            () -> new TabakBlattBlock(BlockBehaviour.Properties.of(Material.PLANT)
                    .sound(SoundType.VINE).instabreak().noOcclusion()));

    public static final RegistryObject<Block> GETROCKNETER_TABAKBALLEN = registerBlock("getrockneter_tabakballen",
            () -> new TabakBallenBlock(BlockBehaviour.Properties.of(Material.LEAVES)
                .strength(0.5F).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> HELLER_TABAKBALLEN = registerBlock("heller_tabakballen",
            () -> new HellerTabakBallenBlock(BlockBehaviour.Properties.of(Material.LEAVES)
                    .strength(0.5F).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> MITTLERER_TABAKBALLEN = registerBlock("mittlerer_tabakballen",
            () -> new MittlererTabakBallenBlock(BlockBehaviour.Properties.of(Material.LEAVES)
                    .strength(0.5F).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> DUNKLER_TABAKBALLEN = registerBlock("dunkler_tabakballen",
            () -> new DunklerTabakBallenBlock(BlockBehaviour.Properties.of(Material.LEAVES)
                    .strength(0.5F).sound(SoundType.GRASS)));

    public static final RegistryObject<Block> TABAKPFLANZE = BLOCKS.register("tabakpflanze",
            () -> new TabakPflanzenBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}

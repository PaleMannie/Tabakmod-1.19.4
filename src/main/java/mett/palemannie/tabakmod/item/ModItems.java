package mett.palemannie.tabakmod.item;

import mett.palemannie.tabakmod.TabakMod;
import mett.palemannie.tabakmod.block.ModBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TabakMod.MODID);

    public static final RegistryObject<Item> TABAKSAMEN = ITEMS.register("tabaksamen",
            () -> new ItemNameBlockItem(ModBlocks.TABAKPFLANZE.get(),new Item.Properties()));
    public static final RegistryObject<Item> TABAKBLATT = ITEMS.register("tabakblatt",
        () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TROCKENER_TABAK = ITEMS.register("trockener_tabak",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HELLER_TABAK = ITEMS.register("heller_tabak",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MITTLERER_TABAK = ITEMS.register("mittlerer_tabak",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUNKLER_TABAK = ITEMS.register("dunkler_tabak",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus)
    {
    ITEMS.register(eventBus);
    }
}

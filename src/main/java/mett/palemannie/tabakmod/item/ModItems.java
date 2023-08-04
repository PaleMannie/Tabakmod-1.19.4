package mett.palemannie.tabakmod.item;

import mett.palemannie.tabakmod.TabakMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TabakMod.MODID);

    public static final RegistryObject<Item> GETROCKNETER_TABAKBLATT = ITEMS.register("getrockneter_tabakblatt",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FAST_GETROCKNETER_TABAKBLATT = ITEMS.register("fast_getrockneter_tabakblatt",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HALB_GETROCKNETER_TABAKBLATT = ITEMS.register("halb_getrockneter_tabakblatt",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LEICHT_GETROCKNETER_TABAKBLATT = ITEMS.register("leicht_getrockneter_tabakblatt",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROHER_TABAKBLATT = ITEMS.register("roher_tabakblatt",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TABAKSAMEN = ITEMS.register("tabaksamen",
            () -> new Item(new Item.Properties()));
    public static void register(IEventBus eventBus)
    {
    ITEMS.register(eventBus);
    }
}

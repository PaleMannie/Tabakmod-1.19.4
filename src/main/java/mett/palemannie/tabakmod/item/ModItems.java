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

    public static final RegistryObject<Item> TABAKSAMEN = ITEMS.register("tabaksamen",
            () -> new Item(new Item.Properties()));
    public static void register(IEventBus eventBus)
    {
    ITEMS.register(eventBus);
    }
}

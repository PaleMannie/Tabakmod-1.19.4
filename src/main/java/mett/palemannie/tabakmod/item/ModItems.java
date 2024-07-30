package mett.palemannie.tabakmod.item;

import mett.palemannie.tabakmod.TabakMod;
import mett.palemannie.tabakmod.block.ModBlocks;
import mett.palemannie.tabakmod.item.custom.*;
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
////////////////////////////////////////////////TABAKGEWÄCHS////////////////////////////////////////////////////////////////////////
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
    public static final RegistryObject<Item> HELLER_TABAK_BEHANDELT = ITEMS.register("heller_tabak_behandelt",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MITTLERER_TABAK_BEHANDELT = ITEMS.register("mittlerer_tabak_behandelt",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUNKLER_TABAK_BEHANDELT = ITEMS.register("dunkler_tabak_behandelt",
            () -> new Item(new Item.Properties()));
/////////////////////////////////////////////////TABAKPRODUKTE///////////////////////////////////////////////////////////////////////
    public static final RegistryObject<Item> ZIGARETTE = ITEMS.register("zigarette",
            () -> new ZigarettenItem(new Item.Properties().durability(200)));
    public static final RegistryObject<Item> ZIGARETTENSTUMMEL = ITEMS.register("zigarettenstummel",
            () -> new StummelItem(new Item.Properties()));
    public static final RegistryObject<Item> ZIGARETTENFILTER = ITEMS.register("zigarettenfilter",
            () -> new StummelItem(new Item.Properties()));
    public static final RegistryObject<Item> ZIGARETTEN = ITEMS.register("zigaretten",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ZIGARETTENSCHACHTEL = ITEMS.register("zigarettenschachtel",
            () -> new ZigSchachtelItem(new Item.Properties().durability(20)));
    public static final RegistryObject<Item> ZIGARETTENSCHACHTEL_LEER = ITEMS.register("zigarettenschachtel_leer",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ZIGARETTENSCHACHTEL_GROSS = ITEMS.register("zigarettenschachtel_gross",
            () -> new ZigSchachtelItem(new Item.Properties().durability(30)));
    public static final RegistryObject<Item> ZIGARETTENSCHACHTEL_GROSS_LEER = ITEMS.register("zigarettenschachtel_gross_leer",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ZIGARRE = ITEMS.register("zigarre",
            () -> new ZigarrenItem(new Item.Properties().durability(600)));
    public static final RegistryObject<Item> ZIGARREN = ITEMS.register("zigarren",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ZIGARRENSTUMMEL = ITEMS.register("zigarrenstummel",
            () -> new StummelItem(new Item.Properties()));
    public static final RegistryObject<Item> ZIGARRENSCHACHTEL = ITEMS.register("zigarrenschachtel",
            () -> new ZigrrSchachtelItem(new Item.Properties().durability(6)));
    public static final RegistryObject<Item> ZIGARRENSCHACHTEL_LEER = ITEMS.register("zigarrenschachtel_leer",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DECKBLATT = ITEMS.register("deckblatt",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PFEIFE = ITEMS.register("pfeife",
            () -> new PfeifenItem(new Item.Properties().durability(220)));
    public static final RegistryObject<Item> PFEIFE_LEER = ITEMS.register("pfeife_leer",
            () -> new LeerePfeifenItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ZIGARETTE_MENTHOL = ITEMS.register("zigarette_menthol",
            () -> new MenthZigarettenItem(new Item.Properties().durability(200)));
    public static final RegistryObject<Item> ZIGARETTENSCHACHTEL_MENTHOL = ITEMS.register("zigarettenschachtel_menthol",
            () -> new ZigSchachtelItem(new Item.Properties().durability(20)));
    public static final RegistryObject<Item> ZIGARETTENSCHACHTEL_MENTHOL_LEER = ITEMS.register("zigarettenschachtel_menthol_leer",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ZIGARETTE_SCHEISE = ITEMS.register("zigarette_scheise",
            () -> new ScheisZigarettenItem(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> DSCHOINT = ITEMS.register("dschoint",
            () -> new DschointItem(new Item.Properties().durability(200)));
    public static final RegistryObject<Item> KAUTABAK = ITEMS.register("kautabak",
            () -> new KautabakItem(new Item.Properties()));

    public static void register(IEventBus eventBus)
    {
    ITEMS.register(eventBus);
    }
}
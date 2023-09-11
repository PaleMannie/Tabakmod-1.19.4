package mett.palemannie.tabakmod;

import com.mojang.logging.LogUtils;
import mett.palemannie.tabakmod.block.ModBlocks;
import mett.palemannie.tabakmod.item.ModCreativeModeTabs;
import mett.palemannie.tabakmod.item.ModItems;
import mett.palemannie.tabakmod.loot.ModLootModifiers;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TabakMod.MODID)
public class TabakMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "tabakmod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public TabakMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModLootModifiers.register(modEventBus);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == ModCreativeModeTabs.TABAK_TAB) {

            event.accept(ModItems.TABAKSAMEN);
            event.accept(ModItems.TABAKBLATT);

            event.accept(ModItems.TROCKENER_TABAK);
            event.accept(ModItems.HELLER_TABAK_BEHANDELT);
            event.accept(ModItems.MITTLERER_TABAK_BEHANDELT);
            event.accept(ModItems.DUNKLER_TABAK_BEHANDELT);
            event.accept(ModItems.HELLER_TABAK);
            event.accept(ModItems.MITTLERER_TABAK);
            event.accept(ModItems.DUNKLER_TABAK);

            event.accept(ModBlocks.ROHER_TABAKBLATT);
            event.accept(ModBlocks.LEICHT_GETROCKNETER_TABAKBLATT);
            event.accept(ModBlocks.HALB_GETROCKNETER_TABAKBLATT);
            event.accept(ModBlocks.FAST_GETROCKNETER_TABAKBLATT);
            event.accept(ModBlocks.GETROCKNETER_TABAKBLATT);

            event.accept(ModBlocks.GETROCKNETER_TABAKBALLEN);
            event.accept(ModBlocks.HELLER_TABAKBALLEN);
            event.accept(ModBlocks.MITTLERER_TABAKBALLEN);
            event.accept(ModBlocks.DUNKLER_TABAKBALLEN);

            event.accept(ModItems.ZIGARETTE);
            event.accept(ModItems.ZIGARRE);
            event.accept(ModItems.ZIGARETTEN);
            event.accept(ModItems.ZIGARREN);
            event.accept(ModItems.ZIGARETTENFILTER);
            event.accept(ModItems.ZIGARETTENSTUMMEL);
            event.accept(ModItems.ZIGARRENSTUMMEL);
            event.accept(ModItems.ZIGARETTENSCHACHTEL);
            event.accept(ModItems.ZIGARETTENSCHACHTEL_LEER);
            event.accept(ModItems.ZIGARETTENSCHACHTEL_GROSS);
            event.accept(ModItems.ZIGARETTENSCHACHTEL_GROSS_LEER);
            event.accept(ModItems.ZIGARRENSCHACHTEL);
            event.accept(ModItems.ZIGARRENSCHACHTEL_LEER);
            event.accept(ModItems.DECKBLATT);

        }

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.TABAKPFLANZE.get(), RenderType.cutout());
        }
    }
}

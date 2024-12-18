package mett.palemannie.tabakmod;

import com.mojang.logging.LogUtils;
import mett.palemannie.tabakmod.block.ModBlocks;
import mett.palemannie.tabakmod.effect.ModEffects;
import mett.palemannie.tabakmod.entity.ModEntities;
import mett.palemannie.tabakmod.item.ModCreativeModeTabs;
import mett.palemannie.tabakmod.item.ModItems;
import mett.palemannie.tabakmod.loot.ModLootModifiers;
import mett.palemannie.tabakmod.networking.ModMessages;
import mett.palemannie.tabakmod.paintings.ModPaintings;
import mett.palemannie.tabakmod.sound.ModSounds;
import mett.palemannie.tabakmod.util.ModItemProperties;
import mett.palemannie.tabakmod.villager.ModVillagers;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.level.block.ComposterBlock;
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

@Mod(TabakMod.MODID)
public class TabakMod {
    public static final String MODID = "tabakmod";
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
        ModVillagers.register(modEventBus);
        ModSounds.register(modEventBus);
        ModPaintings.register(modEventBus);
        ModEffects.register(modEventBus);
        ModEntities.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork( ()-> {
            ModMessages.register();
            ModVillagers.registerPOIs();
        });
        event.enqueueWork( ()-> {
            ComposterBlock.COMPOSTABLES.put(ModItems.TABAKBLATT.get(),0.3f);
            ComposterBlock.COMPOSTABLES.put(ModItems.TABAKSAMEN.get(),0.2f);
        });
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

            event.accept(ModBlocks.TABAKBLAETTER);
            event.accept(ModBlocks.TABAKBLAETTER_TEIL_GETROCKNET);
            event.accept(ModBlocks.TABAKBLAETTER_HALB_GETROCKNET);
            event.accept(ModBlocks.TABAKBLAETTER_FAST_GETROCKNET);
            event.accept(ModBlocks.TABAKBLAETTER_GETROCKNET);

            event.accept(ModBlocks.GETROCKNETER_TABAKBALLEN);
            event.accept(ModBlocks.HELLER_TABAKBALLEN);
            event.accept(ModBlocks.MITTLERER_TABAKBALLEN);
            event.accept(ModBlocks.DUNKLER_TABAKBALLEN);

            event.accept(ModItems.KAUTABAK);
            event.accept(ModItems.KAUTABAKMISCHE);
            event.accept(ModBlocks.TABAKKUCHEN);

            event.accept(ModItems.ZIGARETTE);
            event.accept(ModItems.ZIGARETTE_MENTHOL);
            event.accept(ModItems.ZIGARETTE_SCHEISE);
            event.accept(ModItems.ZIGARRE);
            event.accept(ModItems.PFEIFE);
            event.accept(ModItems.PFEIFE_LEER);
            event.accept(ModItems.DSCHOINT);
            event.accept(ModItems.KAKERLAKE);
            event.accept(ModItems.ZIGARETTEN);
            event.accept(ModItems.ZIGARREN);
            event.accept(ModItems.ZIGARETTENFILTER);
            event.accept(ModItems.ZIGARETTENSTUMMEL);
            event.accept(ModItems.ZIGARRENSTUMMEL);
            event.accept(ModItems.ZIGARETTENSCHACHTEL);
            event.accept(ModItems.ZIGARETTENSCHACHTEL_LEER);
            event.accept(ModItems.ZIGARETTENSCHACHTEL_GROSS);
            event.accept(ModItems.ZIGARETTENSCHACHTEL_GROSS_LEER);
            event.accept(ModItems.ZIGARETTENSCHACHTEL_MENTHOL);
            event.accept(ModItems.ZIGARETTENSCHACHTEL_MENTHOL_LEER);
            event.accept(ModItems.ZIGARRENSCHACHTEL);
            event.accept(ModItems.ZIGARRENSCHACHTEL_LEER);
            event.accept(ModItems.DECKBLATT);

            event.accept(ModBlocks.ASCHENBECHER);
            event.accept(ModBlocks.ASCHENBECHER_GROSS);
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.TABAKPFLANZE.get(), RenderType.cutout());
            ModItemProperties.addCustomItemProperties();

            EntityRenderers.register(ModEntities.SPUCKE.get(), ThrownItemRenderer::new);
        }
    }
}

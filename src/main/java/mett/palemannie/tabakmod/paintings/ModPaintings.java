package mett.palemannie.tabakmod.paintings;

import mett.palemannie.tabakmod.TabakMod;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, TabakMod.MODID);

    public static void register(IEventBus eventBus){
        PAINTING_VARIANTS.register(eventBus);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final RegistryObject<PaintingVariant> SASCHIGALKA = PAINTING_VARIANTS.register("saschigalka", () -> new PaintingVariant(16, 32));
    public static final RegistryObject<PaintingVariant> ETO_JAD = PAINTING_VARIANTS.register("eto_jad", () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> KURENJE_RASRISCHINNO = PAINTING_VARIANTS.register("kurenje_rasrischinno", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> GOD_BIL_1943 = PAINTING_VARIANTS.register("god_bil_1943", () -> new PaintingVariant(64, 48));
    public static final RegistryObject<PaintingVariant> FISIKALNOST = PAINTING_VARIANTS.register("fisikalnost", () -> new PaintingVariant(48, 64));
    public static final RegistryObject<PaintingVariant> JA_KOGDA_TABAKMOD = PAINTING_VARIANTS.register("ja_kogda_tabakmod", () -> new PaintingVariant(48, 48));
    public static final RegistryObject<PaintingVariant> RYDER_NIGGA = PAINTING_VARIANTS.register("ryder_nigga", () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> IDU_KURIU_2 = PAINTING_VARIANTS.register("idu_kuriu2", () -> new PaintingVariant(16, 32));
    public static final RegistryObject<PaintingVariant> IDU_KURIU_3 = PAINTING_VARIANTS.register("idu_kuriu3", () -> new PaintingVariant(32, 48));
    public static final RegistryObject<PaintingVariant> IDU_KURIU_4 = PAINTING_VARIANTS.register("idu_kuriu4", () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> IDU_KURIU_5 = PAINTING_VARIANTS.register("idu_kuriu5", () -> new PaintingVariant(48, 48));
}
package mett.palemannie.tabakmod.loot;

import com.mojang.serialization.Codec;
import mett.palemannie.tabakmod.TabakMod;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import static mett.palemannie.tabakmod.TabakMod.MODID;


public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, TabakMod.MODID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ITEM =
            LOOT_MODIFIER_SERIALIZERS.register("add_item", AddItemModifier.CODEC);
/*
*
*
*   */
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> TABAKSAMEN_AUS_GRAS =
            LOOT_MODIFIER_SERIALIZERS.register("tabaksamen_aus_gras", TabaksamenAusGras.CODEC);
/*
*
*
* */
    public static void register(IEventBus bus) {
        LOOT_MODIFIER_SERIALIZERS.register(bus);
    }
}

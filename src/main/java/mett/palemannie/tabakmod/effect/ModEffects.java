package mett.palemannie.tabakmod.effect;

import mett.palemannie.tabakmod.TabakMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TabakMod.MODID);

    public static final RegistryObject<MobEffect> SPUCKEN = MOB_EFFECTS.register("spucken", ()-> new SpuckenEffect(MobEffectCategory.NEUTRAL, 10813239));

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}

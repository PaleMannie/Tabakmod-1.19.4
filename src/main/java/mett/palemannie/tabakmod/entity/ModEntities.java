package mett.palemannie.tabakmod.entity;

import mett.palemannie.tabakmod.TabakMod;
import mett.palemannie.tabakmod.entity.custom.SpuckeEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TabakMod.MODID);

    public static final RegistryObject<EntityType<SpuckeEntity>> SPUCKE =
            ENTITY_TYPES.register("spucke", () -> EntityType.Builder.<SpuckeEntity>of(SpuckeEntity::new, MobCategory.MISC)
                    .sized(0.2f, 0.2f)
                    .fireImmune()
                    .build("spucke"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}

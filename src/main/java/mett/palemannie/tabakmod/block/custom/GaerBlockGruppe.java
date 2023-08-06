package mett.palemannie.tabakmod.block.custom;


import com.google.common.collect.Maps;
import mett.palemannie.tabakmod.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class GaerBlockGruppe {
    private final String prefix, postfix;
    private final String[] stages;
    private final GaerenderTabak.GaerStatus[] states;
    private final Factory factory;

    public final List<Supplier<Block>> blocks = Lists.newArrayList();
    public final Map<GaerenderTabak.GaerStatus, Supplier<Block>> state_to_block = Maps.newHashMap();

    public GaerBlockGruppe(String prefix, String postfix, String[] stages, GaerenderTabak.GaerStatus[] states, Factory factory) {
        this.prefix = prefix;
        this.postfix = postfix;
        this.stages = stages;
        this.states = states;
        this.factory = factory;
    }
    public GaerBlockGruppe addBlockOverride(Supplier<Block> block, GaerenderTabak.GaerStatus state)
    {
        //Used to insert regular iron block
        blocks.add(block);
        state_to_block.put(state, block);
        return this;
    }

    public GaerBlockGruppe register(DeferredRegister<Block> blockRegistry, DeferredRegister<Item> itemRegister)
    {
        for(int i = 0; i < states.length; i++)
        {
            String stage = stages[i];
            GaerenderTabak.GaerStatus state = states[i];

            String name = prefix + stage + postfix;
            ResourceLocation location = new ResourceLocation(Constants.MODID, name);

            RegistrySupplier<Block> block = blockRegistry.register(location, factory.create(state));

            itemRegister.register(location, ()->new BlockItem(block.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

            blocks.add(block);
            state_to_block.put(state, block);
        }
        GaerenderTabak.GRUPPEN.add(this);
        return this;
    }
    public void registerFermentables() {
        for(int i = 0; i < blocks.size() - 1; i++)
        {
            Supplier<Block> next = blocks.get(i + 1);
            System.out.println(Registry.BLOCK.getKey(blocks.get(i).get()));
            GaerenderTabak.NEXT_BY_BLOCK.put(blocks.get(i).get(), next.get());
        }
    }

    public interface Factory {
        Supplier<Block> create(GaerenderTabak.GaerStatus age);

    }
}

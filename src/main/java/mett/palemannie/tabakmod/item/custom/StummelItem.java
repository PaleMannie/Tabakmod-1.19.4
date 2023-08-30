package mett.palemannie.tabakmod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class StummelItem extends Item {
    public StummelItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public int getEntityLifespan(ItemStack itemStack, Level level) {
        return 72000;
    }
}
package mett.palemannie.tabakmod.item.custom;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class PfeifenItem extends Item {
    public PfeifenItem(Properties pProperties) {
        super(pProperties);
    }
////////////////////////////////////////////EIGENE METHODEN/////////////////////////////////////////////////////////////

////////////////////////////////////////////NUTZMETHODEN////////////////////////////////////////////////////////////////

//////////////////////////////////////////////SONSTIGE METHODEN/////////////////////////////////////////////////////////
    @Override
    public int getEntityLifespan(ItemStack itemStack, Level level) {
    return 72000;
}
    @Override
    public int getUseDuration(ItemStack pStack) {
        return 102;
    }
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }
    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) { return false; }
    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot armorType, Entity entity) { return true; }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

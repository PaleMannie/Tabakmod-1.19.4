package mett.palemannie.tabakmod.item.custom;

import mett.palemannie.tabakmod.item.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class LeerePfeifenItem extends Item {
    public LeerePfeifenItem(Properties pProperties) {
        super(pProperties);
    }
////////////////////////////////////////////EIGENE METHODEN/////////////////////////////////////////////////////////////

////////////////////////////////////////////NUTZMETHODEN////////////////////////////////////////////////////////////////

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        ItemStack derPassendeTabak = new ItemStack(ModItems.MITTLERER_TABAK_BEHANDELT.get());
        boolean istTabakImInventar = pPlayer.getInventory().contains(derPassendeTabak);
        if(istTabakImInventar){
            pPlayer.startUsingItem(pUsedHand);
            return InteractionResultHolder.consume(itemstack);
        } else {
        return InteractionResultHolder.fail(itemstack); }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        ItemStack tbk = new ItemStack(ModItems.MITTLERER_TABAK_BEHANDELT.get());
        if(pLivingEntity instanceof Player pPlayer){
            pPlayer.getInventory().removeItem(pPlayer.getInventory().findSlotMatchingItem(tbk),1);
            InteractionHand pHand = pPlayer.getUsedItemHand();
            pPlayer.setItemInHand(pHand, new ItemStack(ModItems.PFEIFE.get()));
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        super.releaseUsing(pStack, pLevel, pLivingEntity, pTimeCharged);
    }

//////////////////////////////////////////////SONSTIGE METHODEN/////////////////////////////////////////////////////////
    @Override
    public UseAnim getUseAnimation(ItemStack pStack) { return UseAnim.BRUSH; }
    @Override
    public int getUseDuration(ItemStack pStack) { return 41; }

    @Override
    public int getEntityLifespan(ItemStack itemStack, Level level) { return 72000; }
    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot armorType, Entity entity) { return true; }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

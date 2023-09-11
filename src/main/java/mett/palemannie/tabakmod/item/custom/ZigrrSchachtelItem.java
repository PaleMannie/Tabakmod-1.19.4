package mett.palemannie.tabakmod.item.custom;

import mett.palemannie.tabakmod.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;

public class ZigrrSchachtelItem extends ZigSchachtelItem{
    public ZigrrSchachtelItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        ItemStack zigrr = new ItemStack(ModItems.ZIGARRE.get());

        stack.hurtAndBreak(1,pPlayer, p ->{
            if(this == ModItems.ZIGARRENSCHACHTEL.get()){
                ItemStack z = new ItemStack(ModItems.ZIGARRENSCHACHTEL_LEER.get());
                p.drop(z, false);
            }
            p.stopUsingItem();
        });
        if(stack.getDamageValue() >= stack.getMaxDamage()-1){
            pPlayer.playSound(SoundEvents.WOOD_BREAK,4f,1);
            pPlayer.stopUsingItem();
        }
        //FÜGE NOCH CUSTOM TÖNE HINZU
        pPlayer.playSound(SoundEvents.ARMOR_EQUIP_CHAIN);
        pPlayer.addItem(zigrr);
        pPlayer.stopUsingItem();
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);
    }
}
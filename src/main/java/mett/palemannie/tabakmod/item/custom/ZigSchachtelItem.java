package mett.palemannie.tabakmod.item.custom;

import mett.palemannie.tabakmod.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class ZigSchachtelItem extends Item {
    public ZigSchachtelItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        ItemStack zig = new ItemStack(ModItems.ZIGARETTE.get());

        stack.hurtAndBreak(1,pPlayer, p ->{
            if(this == ModItems.ZIGARETTENSCHACHTEL.get()){
                ItemStack z = new ItemStack(ModItems.ZIGARETTENSCHACHTEL_LEER.get());
                p.drop(z,false);
            }
            if(this == ModItems.ZIGARETTENSCHACHTEL_GROSS.get()){
                ItemStack x = new ItemStack(ModItems.ZIGARETTENSCHACHTEL_GROSS_LEER.get());
                p.drop(x,false);
            }
            p.stopUsingItem();
        });
        if(stack.getDamageValue() >= stack.getMaxDamage()-1){
            pPlayer.playSound(SoundEvents.BOOK_PAGE_TURN);
        }
        //FÜGE NOCH CUSTOM TÖNE HINZU
        pPlayer.playSound(SoundEvents.CHAIN_PLACE);
        pPlayer.addItem(zig);

        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);
    }


    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BLOCK;
    }
}

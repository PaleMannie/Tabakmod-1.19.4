package mett.palemannie.tabakmod.item.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class ZigarettenItem extends Item {
    public ZigarettenItem(Properties pProperties) { super(pProperties); }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        pPlayer.playSound(SoundEvents.PIG_AMBIENT, 0.1f,1f);
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        super.onUseTick(pLevel, pLivingEntity, pStack, pRemainingUseDuration);
        if(pLivingEntity instanceof Player pPlayer) {
            Vec3 MausPos = pPlayer.getEyePosition();
            Vec3 SchauWinkel = pPlayer.getLookAngle();
            pLevel.addParticle(ParticleTypes.SMOKE,
                    MausPos.x, MausPos.y-0.15d, MausPos.z,
                    SchauWinkel.x/10, SchauWinkel.y/10, SchauWinkel.z/10);
            pStack.hurtAndBreak(1, pPlayer, pPlayer1 -> pPlayer1.attack(pPlayer));//broadcastBreakEvent(pPlayer1.getUsedItemHand()));
        }
    }

    @Override
    public void onDestroyed(ItemEntity itemEntity, DamageSource damageSource) {
        System.out.println("KAPUUT");
        super.onDestroyed(itemEntity, damageSource);
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        if(pLivingEntity instanceof Player pPlayer){
            Vec3 MausPos = pPlayer.getEyePosition();
            Vec3 SchauWinkel = pPlayer.getLookAngle();
            pLevel.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                    MausPos.x, MausPos.y-0.2d, MausPos.z,
                    SchauWinkel.x/20, SchauWinkel.y/20, SchauWinkel.z/20);
        }
        this.stopUsing(pLivingEntity);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        super.finishUsingItem(pStack, pLevel, pLivingEntity);
        if(pLivingEntity instanceof Player pPlayer){
            Vec3 MausPos = pPlayer.getEyePosition();
            Vec3 SchauWinkel = pPlayer.getLookAngle();
            pLevel.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                MausPos.x, MausPos.y-0.2d, MausPos.z,
                SchauWinkel.x/20, SchauWinkel.y/20, SchauWinkel.z/20);
        }
        this.stopUsing(pLivingEntity);
        return pStack;
    }
    private void stopUsing(LivingEntity pUser) {
        pUser.playSound(SoundEvents.PIG_DEATH, 0.1F, 1.0F);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 30;
    }
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot armorType, Entity entity) {
        return true;
    }

}

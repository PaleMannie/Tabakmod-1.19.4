package mett.palemannie.tabakmod.item.custom;

import mett.palemannie.tabakmod.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class ZigarrenItem extends Item {
    public ZigarrenItem(Properties pProperties) {
        super(pProperties);
    }
    void paffe(Level level, Player player) {
        Vec3 MausPos = player.getEyePosition();
        Vec3 SchauWinkel = player.getLookAngle();
        level.addParticle(ParticleTypes.LARGE_SMOKE,
                MausPos.x, MausPos.y-0.2d, MausPos.z,
                SchauWinkel.x/10, SchauWinkel.y/10, SchauWinkel.z/10);
    }
    void exhaliere(Level level, Player player) {
        Vec3 MausPos = player.getEyePosition();
        Vec3 SchauWinkel = player.getLookAngle();
        for(int i=0; i<=5; i++){
        level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                MausPos.x, MausPos.y-0.2d, MausPos.z,
                SchauWinkel.x/20, SchauWinkel.y/20, SchauWinkel.z/20);
        }
    }
    void gibRauchStandardEffekte(Player player){
        player.addEffect(new MobEffectInstance(MobEffects.CONFUSION,110,0));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,120,1));
        player.addEffect(new MobEffectInstance(MobEffects.SATURATION,1,1));
    }

    void gibZuLangesZiehenEffekte(Player player){
        player.addEffect(new MobEffectInstance(MobEffects.HARM,1,1));
        player.addEffect(new MobEffectInstance(MobEffects.CONFUSION,140,1));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,220,1));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,220,1));
        player.addEffect(new MobEffectInstance(MobEffects.SATURATION,2,1));
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        pPlayer.playSound(SoundEvents.PIG_AMBIENT, 0.1f,1f);
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        super.onUseTick(pLevel, pLivingEntity, pStack, pRemainingUseDuration);
        if(pLivingEntity instanceof Player pPlayer && (pRemainingUseDuration <= getUseDuration(pStack) - 24)) {
            paffe(pLevel,pPlayer);
            pStack.hurtAndBreak(1, pPlayer, p -> {
                gibRauchStandardEffekte(pPlayer);
                ItemStack itemstack = new ItemStack(ModItems.ZIGARRENSTUMMEL.get());
                p.drop(itemstack,true);
            });
            if(pStack.getDamageValue() >= pStack.getMaxDamage()-1){
                exhaliere(pLevel,pPlayer);
                pPlayer.playSound(SoundEvents.COW_DEATH,0.1f,1f);
            }
        }
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        if(pLivingEntity instanceof Player pPlayer && (pTimeCharged <= getUseDuration(pStack) - 24)){
            gibRauchStandardEffekte(pPlayer);
            exhaliere(pLevel,pPlayer);
        }
        this.stopUsing(pLivingEntity);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        super.finishUsingItem(pStack, pLevel, pLivingEntity);
        if(pLivingEntity instanceof Player pPlayer){
            gibZuLangesZiehenEffekte(pPlayer);
            exhaliere(pLevel,pPlayer);
        }
        this.stopUsing(pLivingEntity);
        return pStack;
    }
    private void stopUsing(LivingEntity pUser) {
        pUser.playSound(SoundEvents.PIG_DEATH, 0.1F, 1.0F);
        if(pUser instanceof Player player){
            player.getCooldowns().addCooldown(this,12);
        }
    }

    ////////////////////////////////////////////////////SONSTIGE METHODEN////////////////////////////////////////////////////////////////////
    @Override
    public int getEntityLifespan(ItemStack itemStack, Level level) {
        return 72000;
    }
    @Override
    public int getUseDuration(ItemStack pStack) {
        return 94;
    }
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }
    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) { return false; }
    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot armorType, Entity entity) {
        return true;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

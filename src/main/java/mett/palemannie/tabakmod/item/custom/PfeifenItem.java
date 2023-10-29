package mett.palemannie.tabakmod.item.custom;

import mett.palemannie.tabakmod.item.ModItems;
import mett.palemannie.tabakmod.sound.ModSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
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

public class PfeifenItem extends Item {
    public PfeifenItem(Properties pProperties) {
        super(pProperties);
    }
////////////////////////////////////////////EIGENE METHODEN/////////////////////////////////////////////////////////////
    void paffe(Level level, Player player){
        RandomSource rdm = RandomSource.create();
        float r = (float)rdm.nextInt(9,11)/10;
        player.playSound(ModSounds.PAFFEN.get(), 1f,r);
        Vec3 MausPos = player.getEyePosition();
        Vec3 SchauWinkel = player.getLookAngle();
        level.addParticle(ParticleTypes.SMOKE,
            MausPos.x, MausPos.y-0.15d, MausPos.z,
            SchauWinkel.x/10, SchauWinkel.y/10, SchauWinkel.z/10);
    }
    void exhaliere(Level level, Player player){
        Vec3 MausPos = player.getEyePosition();
        Vec3 SchauWinkel = player.getLookAngle();
        level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                MausPos.x, MausPos.y-0.2d, MausPos.z,
                SchauWinkel.x/20, SchauWinkel.y/20, SchauWinkel.z/20);
    }
    void gibRauchStandardEffekte(Player player){
        player.addEffect(new MobEffectInstance(MobEffects.CONFUSION,80,0));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,200,0));
        player.addEffect(new MobEffectInstance(MobEffects.SATURATION,2,0));
    }

    void gibZuLangesZiehenEffekte(Player player){
        player.addEffect(new MobEffectInstance(MobEffects.CONFUSION,110,0));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,300,0));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,300,0));
        player.addEffect(new MobEffectInstance(MobEffects.SATURATION,3,0));
        player.addEffect(new MobEffectInstance(MobEffects.HARM,1,1));
    }
////////////////////////////////////////////NUTZMETHODEN////////////////////////////////////////////////////////////////
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        RandomSource rdm = RandomSource.create();
        float r = (float)rdm.nextInt(8,12)/10;
        pPlayer.playSound(ModSounds.TABAKPRODUKT_ANZUENDEN.get(), 1f,r);
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);
    }
    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        super.onUseTick(pLevel, pLivingEntity, pStack, pRemainingUseDuration);
        RandomSource random = RandomSource.create();
        float chance = 0.5f;
        if(pLivingEntity instanceof Player pPlayer && (pRemainingUseDuration <= getUseDuration(pStack) - 10)) {
            if(chance >= random.nextFloat()){
                paffe(pLevel, pPlayer);
            }
            pStack.hurtAndBreak(1, pPlayer, p -> {
                gibRauchStandardEffekte(pPlayer);
                ItemStack itemstack = new ItemStack(ModItems.PFEIFE_LEER.get());
                p.addItem(itemstack);
            });
            if(pStack.getDamageValue() >= pStack.getMaxDamage()-1){
                exhaliere(pLevel,pPlayer);
                RandomSource rdm = RandomSource.create();
                float r = (float)rdm.nextInt(8,12)/10;
                pPlayer.playSound(ModSounds.FERTIG_GERAUCHT.get(),1f,r);
            }
        }
    }
    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        if(pLivingEntity instanceof Player pPlayer && (pTimeCharged <= getUseDuration(pStack) - 10)){
            gibRauchStandardEffekte(pPlayer);
            exhaliere(pLevel,pPlayer);
            RandomSource rdm = RandomSource.create();
            float r = (float)rdm.nextInt(8,12)/10;
            pPlayer.playSound(ModSounds.FERTIG_GERAUCHT.get(),1f,r);
        }
        this.stopUsing(pLivingEntity);
    }
    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        super.finishUsingItem(pStack, pLevel, pLivingEntity);
        if(pLivingEntity instanceof Player pPlayer){
            RandomSource rdm = RandomSource.create();
            float r = (float)rdm.nextInt(8,12)/10;
            pPlayer.playSound(ModSounds.ZU_LANGE_GEZOGEN.get(),1f,r);
            gibZuLangesZiehenEffekte(pPlayer);
            exhaliere(pLevel,pPlayer);
        }
        this.stopUsing(pLivingEntity);
        return pStack;
    }
    private void stopUsing(LivingEntity pUser) {
        if(pUser instanceof Player player){
            player.getCooldowns().addCooldown(this,2);
        }
    }

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
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) { return slotChanged; }
    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot armorType, Entity entity) { return true; }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

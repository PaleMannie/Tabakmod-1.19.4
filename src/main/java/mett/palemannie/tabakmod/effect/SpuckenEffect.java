package mett.palemannie.tabakmod.effect;

import mett.palemannie.tabakmod.networking.ModMessages;
import mett.palemannie.tabakmod.networking.packets.SpuckenC2SPacket;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.LivingEntity;

public class SpuckenEffect extends MobEffect {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public SpuckenEffect(MobEffectCategory mobEffectCategory, int colour){
        super(mobEffectCategory, colour);
    }
    /*public SpuckenEffect(MobEffectCategory mobEffectCategory, int colour, int ticks){
        super(mobEffectCategory, colour, ticks);
    }


    public void setCounter(int wieVieleTicks){
        wieVieleTicks = counter;
    }*/
    int basis = 80;
    int counter = basis;

    void resetCounter(int c){
        c = counter = basis;
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        counter--;
        if(counter % basis == 0) {
            ModMessages.sendToServer(new SpuckenC2SPacket());
            resetCounter(counter);
        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}

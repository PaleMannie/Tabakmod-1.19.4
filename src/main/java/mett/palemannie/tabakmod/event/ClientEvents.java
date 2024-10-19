package mett.palemannie.tabakmod.event;

import mett.palemannie.tabakmod.TabakMod;
import mett.palemannie.tabakmod.networking.ModMessages;
import mett.palemannie.tabakmod.networking.packets.SpuckenC2SPacket;
import mett.palemannie.tabakmod.util.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {

    @Mod.EventBusSubscriber(modid = TabakMod.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents{
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event){
            if(KeyBinding.SPUCKTASTE.consumeClick()) {

                ModMessages.sendToServer(new SpuckenC2SPacket());
            }
        }
    }
    @Mod.EventBusSubscriber(modid = TabakMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event){
            event.register(KeyBinding.SPUCKTASTE);
        }
    }
}

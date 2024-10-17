package mett.palemannie.tabakmod.networking;

import mett.palemannie.tabakmod.TabakMod;
import mett.palemannie.tabakmod.networking.packets.SpuckPartikelS2CPacket;
import mett.palemannie.tabakmod.networking.packets.SpuckTonS2CPacket;
import mett.palemannie.tabakmod.networking.packets.SpuckenC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;
    private static int PacketID = 0;
    private static int id(){
        return PacketID++;
    }

    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(TabakMod.MODID, "messages"))
                .networkProtocolVersion(()-> "1.0").clientAcceptedVersions(s -> true).serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(SpuckenC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SpuckenC2SPacket::new)
                .encoder(SpuckenC2SPacket::toBytes)
                .consumerMainThread(SpuckenC2SPacket::handle)
                .add();

        net.messageBuilder(SpuckPartikelS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SpuckPartikelS2CPacket::new)
                .encoder(SpuckPartikelS2CPacket::toBytes)
                .consumerMainThread(SpuckPartikelS2CPacket::handle)
                .add();

        net.messageBuilder(SpuckTonS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SpuckTonS2CPacket::new)
                .encoder(SpuckTonS2CPacket::toBytes)
                .consumerMainThread(SpuckTonS2CPacket::handle)
                .add();

    }

    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(()->player), message);
    }
}

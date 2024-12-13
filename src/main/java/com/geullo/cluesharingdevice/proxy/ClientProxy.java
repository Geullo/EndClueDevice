package com.geullo.cluesharingdevice.proxy;

import com.geullo.cluesharingdevice.PacketMessage;
import com.geullo.cluesharingdevice.Events.RightClickItem;
import com.geullo.cluesharingdevice.util.ModResourcePack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.openal.AL;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.List;

public class ClientProxy extends CommonProxy{
    public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel("cluesharingdevice");

    @Override
    public void preInit(File configFile) throws IOException {
        File modFolder = new File(Minecraft.getMinecraft().mcDataDir, "resources/CLUE");
        modFolder.mkdirs();
        File configFolder = new File(Minecraft.getMinecraft().mcDataDir, "config/END");
        configFolder.mkdirs();
        File[] modedFolder = {
                new File(Minecraft.getMinecraft().mcDataDir, "resources/CLUE/menu"),
                new File(Minecraft.getMinecraft().mcDataDir, "resources/CLUE/alarms"),
                new File(Minecraft.getMinecraft().mcDataDir, "resources/CLUE/menu/background"),
                new File(Minecraft.getMinecraft().mcDataDir, "resources/CLUE/menu/icon"),
                new File(Minecraft.getMinecraft().mcDataDir, "resources/CLUE/menu/clues"),
                new File(Minecraft.getMinecraft().mcDataDir, "resources/CLUE/menu/clues/preview"),
                new File(Minecraft.getMinecraft().mcDataDir, "resources/CLUE/menu/clues/quizs")
        };
        for (int i = 0; i < modedFolder.length; i++) {
            if (!modedFolder[i].exists()) {
                modedFolder[i].mkdirs();
            }
        }
        File file3 = new File(configFolder, "clues.properties");
        String[] textureArr = {
                "alarms/alarm_sa.png", "alarms/alarm_d7.png","alarms/alarm_Da.png","alarms/alarm_No.png","alarms/alarm_Hu.png","alarms/alarm_Ko.png","alarms/alarm_Ru.png",
                "alarms/alarm_Se.png","alarms/alarm.png","alarms/alarm_Un.png","menu/background/default.png","menu/background/login.png","menu/background/image.png"
                ,"menu/icon/camera.png","menu/icon/hint.png","menu/icon/fingerprint.png","menu/icon/gallery_over.png","menu/icon/gallery.png","menu/icon/latest_clue.png","menu/icon/previous.png","menu/icon/main_date.png"
                ,"menu/icon/gallery_date.png","menu/clues/clue_0.png","menu/clues/clue_1.png","menu/clues/clue_2.png","menu/clues/clue_3.png","menu/clues/clue_4.png",
                "menu/clues/clue_5.png","menu/clues/clue_6.png","menu/clues/clue_7.png"
                ,"menu/clues/preview/clue_0.png","menu/clues/preview/clue_1.png","menu/clues/preview/clue_2.png","menu/clues/preview/clue_3.png","menu/clues/preview/clue_4.png",
                "menu/clues/preview/clue_5.png","menu/clues/preview/clue_6.png","menu/clues/preview/clue_7.png"
                ,"menu/clues/quizs/barcode.png"
        };
        for (int i = 0; i < 33; i++) {
            if (new File(modFolder, "menu/clues/quizs/quiz_"+i+".png").exists()) {
                if (new File(modFolder, "menu/clues/quizs/quiz_"+i+".png").exists()&&new File(modFolder, "menu/clues/quizs/quiz_"+i+".png").length() != new File(getClass().getResource("/assets/cluesharingdevice/textures/menu/clues/quizs/quiz_"+i+".png").getPath()).length()) {
                    Files.delete(new File(modFolder, "menu/clues/quizs/quiz_"+i+".png").toPath());
                }
            }
            if (!new File(modFolder, "menu/clues/quizs/quiz_"+i+".png").exists()) {
                Files.copy(getClass().getResourceAsStream("/assets/cluesharingdevice/textures/menu/clues/quizs/quiz_"+i+".png"), new File(modFolder, "menu/clues/quizs/quiz_"+i+".png").toPath());
            }
        }

        for (int i = 0; i < textureArr.length; i++) {
            if (new File(modFolder, textureArr[i]).exists()) {
                if (new File(modFolder, textureArr[i]).length() != new File(getClass().getResource("/assets/cluesharingdevice/textures/" + textureArr[i]).getPath()).length()) {
                    Files.delete(new File(modFolder, textureArr[i]).toPath());
                }
            }
            if (!new File(modFolder, textureArr[i]).exists()) {
                Files.copy(getClass().getResourceAsStream("/assets/cluesharingdevice/textures/" + textureArr[i]), new File(modFolder, textureArr[i]).toPath());
            }
        }
        if (!file3.exists()) {
            Files.copy(getClass().getResourceAsStream("/assets/cluesharingdevice/textures/configuration/config.properties"), new File(configFolder, "clues.properties").toPath());
        }

    }
    @Override
    public void init() {
        List<IResourcePack> defaultResourcePacks = ObfuscationReflectionHelper.getPrivateValue(FMLClientHandler.class, FMLClientHandler.instance(), "resourcePackList");
        IResourcePack pack = new ModResourcePack(new File(Minecraft.getMinecraft().mcDataDir, "resources/CLUE"));
        defaultResourcePacks.add(pack);

        ((SimpleReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).reloadResourcePack(pack);
        NETWORK.registerMessage(PacketMessage.Handle.class, PacketMessage.class, 0 , Side.CLIENT);

        FMLCommonHandler.instance().bus().register(RightClickItem.getInstance());
    }

    @Override
    public void postInit() {

    }

}

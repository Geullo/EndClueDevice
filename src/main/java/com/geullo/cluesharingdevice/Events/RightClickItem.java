package com.geullo.cluesharingdevice.Events;

import com.geullo.cluesharingdevice.UI.Device.ALoginMenu;
import com.geullo.cluesharingdevice.UI.Device.BDeviceMainMenu;
import net.minecraft.client.Minecraft;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.IOException;

import static com.geullo.clueabout.Item.ItemsRegiter.device;

public class RightClickItem {
    private static RightClickItem instance;
    private Minecraft mc = Minecraft.getMinecraft();
    public boolean logined = false;
    public static RightClickItem getInstance(){
        if (instance == null) {
            instance=new RightClickItem();
        }
        return instance;
    }
    public RightClickItem(){}

    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent.RightClickItem e) throws IOException {
        if (e.getItemStack().isItemEqual(new ItemStack(device))){
            mc.displayGuiScreen(logined?new BDeviceMainMenu():new ALoginMenu());
        }
    }



}

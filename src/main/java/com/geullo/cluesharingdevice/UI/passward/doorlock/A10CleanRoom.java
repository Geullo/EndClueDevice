package com.geullo.cluesharingdevice.UI.passward.doorlock;

import com.geullo.endpassward.Lock.DoorLock;
import net.minecraft.client.Minecraft;

import java.io.IOException;

public class A10CleanRoom extends DoorLock {
    public A10CleanRoom() {
        super("1120");
    }

    @Override
    public void onReward() throws IOException {
        super.onReward();
        if (Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.sendChatMessage("/csd unlock door 10");
            Minecraft.getMinecraft().player.sendChatMessage("/door setblock door_10");
        }
    }
}
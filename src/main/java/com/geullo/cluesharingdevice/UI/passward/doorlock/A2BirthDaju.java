package com.geullo.cluesharingdevice.UI.passward.doorlock;

import com.geullo.endpassward.Lock.DoorLock;
import net.minecraft.client.Minecraft;

import java.io.IOException;

public class A2BirthDaju extends DoorLock {
    public A2BirthDaju() {
        super("0607");
    }

    @Override
    public void onReward() throws IOException {
        super.onReward();
        if (Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.sendChatMessage("/csd unlock door 2");
            Minecraft.getMinecraft().player.sendChatMessage("/door setblock door_2");
        }
    }
}

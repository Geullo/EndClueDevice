package com.geullo.cluesharingdevice.UI.passward.doorlock;

import com.geullo.endpassward.Lock.DoorLock;
import net.minecraft.client.Minecraft;

import java.io.IOException;

public class A1Birth3castle extends DoorLock {
    public A1Birth3castle() {
        super("0918");
    }

    @Override
    public void onReward() throws IOException {
        super.onReward();
        if (Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.sendChatMessage("/csd unlock door 1");
            Minecraft.getMinecraft().player.sendChatMessage("/door setblock door_1");
        }
    }
}

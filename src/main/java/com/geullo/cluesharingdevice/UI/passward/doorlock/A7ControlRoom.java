package com.geullo.cluesharingdevice.UI.passward.doorlock;

import com.geullo.endpassward.Lock.DoorLock;
import net.minecraft.client.Minecraft;

import java.io.IOException;

// 조종실
public class A7ControlRoom extends DoorLock {
    public A7ControlRoom() {
        super("0908");
    }

    @Override
    public void onReward() throws IOException {
        super.onReward();
        if (Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.sendChatMessage("/csd unlock door 7");
            Minecraft.getMinecraft().player.sendChatMessage("/door setblock door_7");

        }
    }
}

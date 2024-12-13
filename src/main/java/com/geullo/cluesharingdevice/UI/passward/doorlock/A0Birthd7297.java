package com.geullo.cluesharingdevice.UI.passward.doorlock;

import com.geullo.endpassward.Lock.DoorLock;
import net.minecraft.client.Minecraft;

import java.io.IOException;

public class A0Birthd7297 extends DoorLock {
    public A0Birthd7297() {
        super("0821");
    }

    @Override
    public void onReward() throws IOException {
        super.onReward();
        if (Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.sendChatMessage("/csd unlock door 0");
            Minecraft.getMinecraft().player.sendChatMessage("/door setblock door_0");
        }
    }
}

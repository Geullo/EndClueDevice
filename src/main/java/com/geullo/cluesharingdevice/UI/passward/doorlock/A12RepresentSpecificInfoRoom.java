package com.geullo.cluesharingdevice.UI.passward.doorlock;

import com.geullo.endpassward.Lock.DoorLock;
import net.minecraft.client.Minecraft;

import java.io.IOException;

public class A12RepresentSpecificInfoRoom extends DoorLock {
    public A12RepresentSpecificInfoRoom() {
        super("IEL");
    }
    @Override
    public void onReward() throws IOException {
        super.onReward();
        if (Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.sendChatMessage("/csd unlock door 12");
            Minecraft.getMinecraft().player.sendChatMessage("/door setblock door_12");
        }
    }
}

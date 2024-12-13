package com.geullo.cluesharingdevice.UI.passward.doorlock;

import com.geullo.endpassward.Lock.DoorLock;
import net.minecraft.client.Minecraft;

import java.io.IOException;


// 실험관제실
public class A6ExperimentDoorlock extends DoorLock {
    public A6ExperimentDoorlock() {
        super("NONE");
    }

    @Override
    public void onReward() throws IOException {
        super.onReward();
        if (Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.sendChatMessage("/csd unlock door 6");
            Minecraft.getMinecraft().player.sendChatMessage("/door setblock door_6");
        }
    }
}

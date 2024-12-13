package com.geullo.cluesharingdevice.UI.passward.doorlock;

import com.geullo.endpassward.Lock.DoorLock;
import net.minecraft.client.Minecraft;

import java.io.IOException;

public class A9MedicineExperimentRoomDoorLock extends DoorLock {
    public A9MedicineExperimentRoomDoorLock() {
        super("KILL");
    }

    @Override
    public void onReward() throws IOException {
        super.onReward();
        if (Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.sendChatMessage("/csd unlock door 9");
            Minecraft.getMinecraft().player.sendChatMessage("/door setblock door_9");
        }
    }
}
package com.geullo.cluesharingdevice.UI.passward.doorlock;

import com.geullo.endpassward.Lock.DoorLock;
import net.minecraft.client.Minecraft;

import java.io.IOException;

//휴게 화장실
public class A4MedicineCleanRoom extends DoorLock {
    public A4MedicineCleanRoom() {
        super("02368");
    }

    @Override
    public void onReward() throws IOException {
        super.onReward();
        if (Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.sendChatMessage("/csd unlock door 4");
            Minecraft.getMinecraft().player.sendChatMessage("/door setblock door_4");
        }
    }
}

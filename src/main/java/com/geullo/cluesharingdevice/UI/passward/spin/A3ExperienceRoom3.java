package com.geullo.cluesharingdevice.UI.passward.spin;

import com.geullo.endpassward.Lock.SpinLock;
import net.minecraft.client.Minecraft;

import java.io.IOException;

import static com.geullo.endpassward.configuration.GetOneValue.GetOneValue;

public class A3ExperienceRoom3 extends SpinLock {
    public A3ExperienceRoom3() {
        super("3467");
    }

    @Override
    public void onReward() throws IOException {
        if (Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.sendChatMessage("/csd unlock spin 3");
            Minecraft.getMinecraft().player.sendChatMessage("/door setblock spin_3");
        }
        super.onReward();
    }
}

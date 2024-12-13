package com.geullo.cluesharingdevice.UI.passward.spin;

import com.geullo.endpassward.Lock.SpinLock;
import net.minecraft.client.Minecraft;

import static com.geullo.endpassward.configuration.GetOneValue.GetOneValue;

import java.io.IOException;

public class A2ExperienceRoom2 extends SpinLock {
    public A2ExperienceRoom2() {
        super("8241");
    }

    @Override
    public void onReward() throws IOException {
        if (Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.sendChatMessage("/csd unlock spin 2");
            Minecraft.getMinecraft().player.sendChatMessage("/door setblock spin_2");
        }
        super.onReward();
    }
}

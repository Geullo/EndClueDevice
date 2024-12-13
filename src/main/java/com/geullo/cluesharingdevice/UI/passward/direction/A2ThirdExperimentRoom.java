package com.geullo.cluesharingdevice.UI.passward.direction;

import com.geullo.endpassward.Lock.DirectionLock;
import com.geullo.endpassward.configuration.GetOneValue;
import net.minecraft.client.Minecraft;

import java.io.IOException;

public class A2ThirdExperimentRoom extends DirectionLock {
    public A2ThirdExperimentRoom() {
        super(
                COORD.EAST.label // 우
                        +COORD.NORTH.label+COORD.NORTH.label // 상 상
                        +COORD.WEST.label // 좌
                        +COORD.NORTH.label+COORD.NORTH.label // 상 상
                        +COORD.EAST.label // 우
                        +COORD.NORTH.label // 상
        );
    }
    @Override
    public void onReward() throws IOException {
        if (Minecraft.getMinecraft().player!=null){
            Minecraft.getMinecraft().player.sendChatMessage("/csd unlock direction 2");
            Minecraft.getMinecraft().player.sendChatMessage("/door setblock direction_2");
        }
        super.onReward();
    }
}

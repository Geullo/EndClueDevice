package com.geullo.cluesharingdevice.UI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Alarm extends GuiScreen {
    private File alarmFile = new File(Minecraft.getMinecraft().mcDataDir, "resources/CLUE/alarms/");//ResourceLocation("csd", "textures/menu/small_background.png");
    private Image BGIMG;
    private int BGimgWidth,BGimgHeight;
    private int[] pos = new int[2];
    private String[] mapping = {"sa","d7","Da","No","Hu","Ko","Ru","Se"},changeMapping = {"sa","d7","Da","No","Ko","Ru"};

    private String alarmPlayer;
    private Minecraft mc = Minecraft.getMinecraft();
    ScaledResolution scaledresolution = new ScaledResolution(mc);
    public int WindowWidth = scaledresolution.getScaledWidth();
    public int WindowHeigth = scaledresolution.getScaledHeight();
    private int RemovedPos = WindowHeigth + 10;
    private int displayTime = 0;
    private boolean displayFinished = false;

    public Alarm(String alarmPlayer){
        this.alarmPlayer = "Un";
        if (Arrays.asList(mapping).contains(alarmPlayer)) {
            this.alarmPlayer = alarmPlayer;
        }
        try {
            this.BGIMG = ImageIO.read(new File(alarmFile,"alarm.png"));
            BGimgWidth = (int) (BGIMG.getWidth(null)/2);
            BGimgHeight = (int) (BGIMG.getHeight(null)/2/1.05); // 5.45
            pos[0] = WindowWidth - BGimgWidth;
            pos[1] = WindowHeigth -BGimgHeight;
            if (pos[0] <= -1){
                pos[0] = 0;
            }
            if (pos[1] <= -1){
                pos[1] = 0;
            }
        } catch (NullPointerException e ) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //mc.currentScreen.width;
    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.VIGNETTE){
            GlStateManager.pushMatrix();
            GlStateManager.loadIdentity();
            GlStateManager.translate(0,0,-2000.0);
            if (!Arrays.asList(changeMapping).contains(this.alarmPlayer)) {
                mc.renderEngine.bindTexture(new ResourceLocation("cluesharingdevice", "alarms/alarm.png"));
                drawModalRectWithCustomSizedTexture(pos[0], pos[1], 0, 0, BGimgWidth, BGimgHeight, BGimgWidth, BGimgHeight);
            }
            mc.renderEngine.bindTexture(new ResourceLocation("cluesharingdevice", "alarms/alarm_"+this.alarmPlayer+".png"));
            drawModalRectWithCustomSizedTexture(pos[0],pos[1], 0,0, BGimgWidth,BGimgHeight,BGimgWidth,BGimgHeight );
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GlStateManager.popMatrix();
            if (displayTime >= 200) {
                pos[1] += 4;
                if (!displayFinished) {
                    if (comparedAlarmPos(pos[1])) {
                        displayFinished = true;
                        MinecraftForge.EVENT_BUS.unregister(this);
                        displayTime = 0;
                        return;
                    }
                }
            }else {
                displayTime += 1;
            }
        }
    }
    public boolean comparedAlarmPos(int imgPos) {return imgPos >= this.RemovedPos;}
}

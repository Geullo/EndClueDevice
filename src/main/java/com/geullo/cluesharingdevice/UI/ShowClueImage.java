package com.geullo.cluesharingdevice.UI;

import com.geullo.cluesharingdevice.Render;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class ShowClueImage extends GuiScreen {
    protected GuiScreen parentScreen;
    protected File Path = new File(Minecraft.getMinecraft().mcDataDir,"resources/CLUE/menu/clues/");

    protected int[] Cluesize = new int[2];
    protected int[] Cluepos = new int[2];
    protected int previousWidth = 0,previousHeight=0;
    protected boolean once = false;

    protected String clueID;

    public ShowClueImage(String clueID,GuiScreen parent) {
        this.parentScreen = parent;
        this.clueID = clueID;
    }

    @Override
    public void initGui(){
        firstset();
    }

    public void firstset(){
        if (width!=previousWidth||height!=previousHeight) {
            try {
                Image CUEIMG = ImageIO.read(new File(Path, clueID + ".png"));
                Cluesize[0] = CUEIMG.getWidth(null);
                Cluesize[1] = CUEIMG.getHeight(null);
                if (CUEIMG.getWidth(null) >= width || CUEIMG.getHeight(null) >= height) {
                    Cluesize[0] = width;
                    Cluesize[1] = height;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            Cluepos[0] = (width - Cluesize[0]) / 2;
            Cluepos[1] = (height - Cluesize[1]) / 2;
            if (Cluepos[0] <= -1) {
                Cluepos[0] = 0;
            }
            if (Cluepos[1] <= -1) {
                Cluepos[1] = 0;
            }
            previousWidth = width;
            previousHeight = height;
        }
    }

    @Override
    public void updateScreen() {
            firstset();
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float f) {
        GlStateManager.disableFog();
        GlStateManager.disableLighting();
        if (!once) {
            mc.mouseHelper.ungrabMouseCursor();
            once=true;
        }
        try {
            drawDefaultBackground();
            GlStateManager.pushMatrix();
            GlStateManager.loadIdentity();
            GlStateManager.translate(0, 0, -2000.0);

            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("cluesharingdevice", "menu/clues/" +clueID + ".png"));
            Render.setColor(0xffffffff);
            Render.drawTexturedRect(Cluepos[0], Cluepos[1], Cluesize[0], Cluesize[1]);

            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GlStateManager.popMatrix();
        }catch (NullPointerException e) {e.printStackTrace();}
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        if (keyCode == Keyboard.KEY_ESCAPE){
            if (parentScreen!=null) {
                mc.displayGuiScreen(parentScreen);
            }else {
                mc.displayGuiScreen(null);
                GlStateManager.disableFog();
                GlStateManager.disableLighting();
            }
            return;
        }
    }
}

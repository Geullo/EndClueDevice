package com.geullo.cluesharingdevice.config;

import net.minecraft.client.Minecraft;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetOneValue {
    private static InputStream inputStream;
    private static boolean Value;
    public static boolean GetOneValue(String locType,int locID) throws IOException {
        try {
            Properties properties = new Properties();
            String propName = Minecraft.getMinecraft().mcDataDir + "/config/END/clues.properties";
            inputStream = new FileInputStream(propName);
            if (inputStream != null){
                properties.load(inputStream);
            }else {
                throw new FileNotFoundException("Geullo: Configuration File :"+propName+" not found in the class path.");
            }
            if (!locType.contains("_")){
                locType = locType+"_";
            }
            Value = "true".equals(properties.getProperty(locType + locID));

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            inputStream.close();
        }
        return Value;
    }
    public static String GetOneValueA(String key) throws IOException {
        String value = "";
        try {
            Properties properties = new Properties();
            String propName = Minecraft.getMinecraft().mcDataDir + "/config/END/clues.properties";
            inputStream = new FileInputStream(propName);
            if (inputStream != null){
                properties.load(inputStream);
            }else {
                throw new FileNotFoundException("Geullo: Configuration File :"+propName+" not found in the class path.");
            }
            value = properties.getProperty(key);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            inputStream.close();
        }
        return value;
    }
}

package com.geullo.cluesharingdevice.config;

import net.minecraft.client.Minecraft;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class GetValues {
    private static InputStream inputStream;
    public HashMap<String, Boolean> clues = new HashMap<>();
    private static boolean[] Value;
    public static boolean[] GetValues(String locType) throws IOException {
        try {
            Properties prop = new Properties();
            String propName = Minecraft.getMinecraft().mcDataDir + "/config/END/clues.properties";
            inputStream = new FileInputStream(propName);
            if (inputStream != null){
                prop.load(inputStream);
            }else {
                throw new FileNotFoundException("Geullo: Configuration File :"+propName+" not found in the class path.");
            }
            Value = new boolean[prop.size()];
            for (int i = 0;i<prop.size();i++){
                Value[i] = Boolean.valueOf(prop.getProperty(locType+"_"+i));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            inputStream.close();
        }
        return Value;
    }
}

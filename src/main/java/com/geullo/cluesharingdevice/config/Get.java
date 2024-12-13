package com.geullo.cluesharingdevice.config;

import net.minecraft.client.Minecraft;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class Get {
    private static InputStream inputStream;
    private static HashMap<String, String> clues = new HashMap<>();
    public static HashMap<String, String> GetValues(String locType) throws IOException {
        try {
            Properties prop = new Properties();
            String propName = Minecraft.getMinecraft().mcDataDir + "/config/END/clues.properties";
            inputStream = new FileInputStream(propName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("Geullo: Configuration File :" + propName + " not found in the class path.");
            }
            clues.clear();
            String id;
            for (int i = 0; i < prop.size(); i++) {
                id = locType + "_" + i;
                if (prop.getProperty(id)!=null&&prop.getProperty(id).contains("CLUE_")) {
                    clues.put(id, prop.getProperty(id));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            inputStream.close();
        }
        return clues;
    }
}

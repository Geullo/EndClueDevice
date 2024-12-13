package com.geullo.cluesharingdevice.config;

import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class ResetConfig {
    private File configFolder = new File(Minecraft.getMinecraft().mcDataDir, "config/END");
    public ResetConfig() throws IOException {
        if (!this.configFolder.exists()) {this.configFolder.mkdirs();}
        Files.delete(new File(configFolder, "clues.properties").toPath());
        Files.copy(Objects.requireNonNull(getClass().getResourceAsStream("/assets/cluesharingdevice/textures/configuration/config.properties")), new File(configFolder, "clues.properties").toPath());
    }
}

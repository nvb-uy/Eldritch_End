package elocindev.eldritch_end.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import elocindev.eldritch_end.config.entries.PrimordialAbyssConfig;
import net.fabricmc.loader.api.FabricLoader;

public class ConfigBuilder {
    public static final Gson BUILDER = (new GsonBuilder()).setPrettyPrinting().create();
  
    // Folders
    public static final Path Folder = FabricLoader.getInstance().getConfigDir().resolve("eldritch_end");
    public static final Path BiomeFolder = Folder.resolve("biomes");

    // Files
    public static final Path PrimordialAbyss = BiomeFolder.resolve("primordial_abyss.json");
    
    public static boolean start() {
        createFolders();
        
        return true;
    }

    public static PrimordialAbyssConfig loadPrimordialAbyss() {
        try {            
            if (Files.notExists(PrimordialAbyss)) {
                PrimordialAbyssConfig defaultCfg = new PrimordialAbyssConfig();
                
                defaultCfg.biome_weight = 5.0F;

                String defaultJson = BUILDER.toJson(defaultCfg);
                Files.writeString(PrimordialAbyss, defaultJson);
            }

            return BUILDER.fromJson(Files.readString(PrimordialAbyss), PrimordialAbyssConfig.class);

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void createFolders() {
        try {
            if (Files.notExists(Folder)) Files.createDirectory(Folder);

            if (Files.notExists(BiomeFolder)) Files.createDirectory(BiomeFolder);

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}

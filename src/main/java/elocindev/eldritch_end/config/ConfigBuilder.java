package elocindev.eldritch_end.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import elocindev.eldritch_end.config.entries.ClientConfig;
import elocindev.eldritch_end.config.entries.PrimordialAbyssConfig;
import elocindev.eldritch_end.config.entries.entities.AberrationConfig;
import net.fabricmc.loader.api.FabricLoader;

public class ConfigBuilder {
    public static final Gson BUILDER = (new GsonBuilder()).setPrettyPrinting().create();
  
    // Folders
    public static final Path Folder = FabricLoader.getInstance().getConfigDir().resolve("eldritch_end");
    public static final Path BiomeFolder = Folder.resolve("biomes");
    public static final Path EntityFolder = Folder.resolve("entities");

    // Files
    public static final Path ClientConfig = Folder.resolve("eldritch_end-client.json");

        // Biomes
    public static final Path PrimordialAbyss = BiomeFolder.resolve("primordial_abyss.json");
    public static final Path HasturianWastes = BiomeFolder.resolve("hasturian_wastes.json");
        
        // Entities
    public static final Path Aberration = EntityFolder.resolve("aberration.json");
    
    public static boolean hasStarted() {
        return createFolders();
    }

    // CLIENTSIDE CONFIGS
    public static ClientConfig loadClientConfig() {
        try {            
            if (Files.notExists(ClientConfig)) {
                ClientConfig defaultCfg = new ClientConfig();
                
                defaultCfg.enable_fog = true;

                String defaultJson = BUILDER.toJson(defaultCfg);
                Files.writeString(ClientConfig, defaultJson);
            }

            return BUILDER.fromJson(Files.readString(ClientConfig), ClientConfig.class);

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    // BIOME RELATED CONFIGS

    public static PrimordialAbyssConfig loadPrimordialAbyss() {
        try {            
            if (Files.notExists(PrimordialAbyss)) {
                PrimordialAbyssConfig defaultCfg = new PrimordialAbyssConfig();
                
                defaultCfg.enabled = true;
                defaultCfg.spawn_endermen = false;
                defaultCfg.spawn_aberrations = true;
                defaultCfg.biome_weight = 1.5F;
                defaultCfg.biome_temperature = 0.0085F;

                String defaultJson = BUILDER.toJson(defaultCfg);
                Files.writeString(PrimordialAbyss, defaultJson);
            }

            return BUILDER.fromJson(Files.readString(PrimordialAbyss), PrimordialAbyssConfig.class);

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    // ENTITIES

    public static AberrationConfig loadAberration() {
        try {            
            if (Files.notExists(Aberration)) {
                AberrationConfig defaultCfg = new AberrationConfig();
                
                defaultCfg.HEALTH = 20.0;
                defaultCfg.MOVEMENT_SPEED = 0.15;
                defaultCfg.CHASE_SPEED = 1.20;
                defaultCfg.ATTACK_DAMAGE = 4.0;
                defaultCfg.ATTACK_SPEED = 1.0;
                defaultCfg.initital_corruption_duration_ticks = 200;

                String defaultJson = BUILDER.toJson(defaultCfg);
                Files.writeString(Aberration, defaultJson);
            }

            return BUILDER.fromJson(Files.readString(Aberration), AberrationConfig.class);

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    // MISC

    public static boolean createFolders() {
        try {
            if (Files.notExists(Folder)) Files.createDirectory(Folder);
            if (Files.notExists(BiomeFolder)) Files.createDirectory(BiomeFolder);
            if (Files.notExists(EntityFolder)) Files.createDirectory(EntityFolder);

            return true;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}

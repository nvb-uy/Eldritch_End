package elocindev.eldritch_end.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.fabricmc.loader.api.FabricLoader;

public class ServerConfig {
    public static final Gson BUILDER = (new GsonBuilder()).setPrettyPrinting().create();
  
    public static final Path Folder = FabricLoader.getInstance().getConfigDir().resolve("eldritch_end");
    public static final Path File = Folder.resolve("config.json");
    
    public static ServerEntries loadConfig() {
        try {
            if (Files.notExists(Folder))
                Files.createDirectory(Folder);
            
            if (Files.notExists(File)) {
                ServerEntries exampleConfig = new ServerEntries();
                
                String defaultJson = BUILDER.toJson(exampleConfig);
                Files.writeString(File, defaultJson);
            }

            return BUILDER.fromJson(Files.readString(File), ServerEntries.class);

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
}   
}

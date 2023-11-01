package elocindev.eldritch_end.config;

import java.nio.file.Path;

import net.fabricmc.loader.api.FabricLoader;

public class ConfigFolder {
    public static String getFile(String file) {
        Path folder = FabricLoader.getInstance().getConfigDir().resolve("eldritch_end");

        if (!folder.toFile().exists())
            folder.toFile().mkdir();

        return FabricLoader.getInstance().getConfigDir().resolve("eldritch_end").resolve(file).toString();
    }

    public static String getNestedFile(String file, String folder) {
        Path cfg = FabricLoader.getInstance().getConfigDir();
        Path eeFolder = cfg.resolve("eldritch_end");

        if (!eeFolder.toFile().exists())
            eeFolder.toFile().mkdir();
        
        Path nestedFolder = eeFolder.resolve(folder);
        
        if (!nestedFolder.toFile().exists()) nestedFolder.toFile().mkdir();

        return nestedFolder.resolve(file).toString();
    }
}

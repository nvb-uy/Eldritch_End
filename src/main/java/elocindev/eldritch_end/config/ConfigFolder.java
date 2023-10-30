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
        Path mainFolder = FabricLoader.getInstance().getConfigDir().resolve("eldritch_end");

        if (!mainFolder.toFile().exists())
            mainFolder.toFile().mkdir();
        
        Path nestedFolder = mainFolder.resolve(folder); if (!nestedFolder.toFile().exists()) nestedFolder.toFile().mkdir();

        return FabricLoader.getInstance().getConfigDir().resolve("eldritch_end").resolve(nestedFolder).resolve(file).toString();
    }
}

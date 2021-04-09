package me.advait.beheadthedragonproduction.game.manager;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import me.advait.beheadthedragonproduction.DragonPlugin;
import me.advait.beheadthedragonproduction.game.Arena;
import me.advait.beheadthedragonproduction.game.map.ArenaMap;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MapManager {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final DragonPlugin plugin;

    @Getter
    private final List<ArenaMap> loadedMaps;

    public MapManager(DragonPlugin plugin) {
        this.plugin = plugin;
        this.loadedMaps = plugin.getMapsFile().loadMaps();
    }

    public void createMapWorld(ArenaMap arenaMap, Arena arena) {
        String name = "arena_" + arena.getID();
        copyWorld(arenaMap.getMapWorld(), name);
        new WorldCreator(name).createWorld();
    }

    private void copyWorld(World originalWorld, String newWorldName) {
        copyFileStructure(originalWorld.getWorldFolder(), new File(Bukkit.getWorldContainer(), newWorldName));
        new WorldCreator(newWorldName).createWorld();
    }

    private void copyFileStructure(File source, File target) {
        try {
            ArrayList<String> ignore = new ArrayList<>(Arrays.asList("uid.dat", "session.lock"));
            if (!ignore.contains(source.getName())) {
                if (source.isDirectory()) {
                    if (!target.exists())
                        if (!target.mkdirs())
                            throw new IOException("Couldn't create world directory!");
                    String files[] = source.list();
                    for (String file : files) {
                        File srcFile = new File(source, file);
                        File destFile = new File(target, file);
                        copyFileStructure(srcFile, destFile);
                    }
                } else {
                    InputStream in = new FileInputStream(source);
                    OutputStream out = new FileOutputStream(target);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0)
                        out.write(buffer, 0, length);
                    in.close();
                    out.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

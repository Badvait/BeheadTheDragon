package me.advait.beheadthedragonproduction.files;

import me.advait.beheadthedragonproduction.DragonPlugin;
import me.advait.beheadthedragonproduction.game.map.ArenaMap;
import me.advait.beheadthedragonproduction.utility.LocationUtility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MapsFile extends YMLBase {

    public MapsFile(DragonPlugin plugin) {
        super(plugin, new File(plugin.getDataFolder(), "maps.yml"), true);
    }

    public List<ArenaMap> loadMaps() {
        List<ArenaMap> maps = new ArrayList<>();
        for (String key : getConfiguration().getConfigurationSection("maps").getKeys(false)) {

            ConfigurationSection section = getConfiguration().getConfigurationSection("maps." + key);
            ArenaMap arenaMap = new ArenaMap();

            arenaMap.setMapWorld(Bukkit.getWorld(section.getString("world-name")));
            arenaMap.setMapName(section.getString("display-name"));

            ConfigurationSection playerSpawnSection = section.getConfigurationSection("player-spawn-location");
            ConfigurationSection dragonSpawnSection = section.getConfigurationSection("dragon-spawn-location");
            arenaMap.setPlayerSpawn(LocationUtility.read(playerSpawnSection));
            arenaMap.setDragonSpawn(LocationUtility.read(dragonSpawnSection));

            ConfigurationSection cornerOneSection = section.getConfigurationSection("buildable-corner-one");
            ConfigurationSection cornerTwoSection = section.getConfigurationSection("buildable-corner-two");
            arenaMap.setBuildableCornerOne(LocationUtility.read(cornerOneSection));
            arenaMap.setBuildableCornerTwo(LocationUtility.read(cornerTwoSection));

            if (getConfiguration().getBoolean("choose-random-map")) return maps;

        }
        return maps;
    }

}

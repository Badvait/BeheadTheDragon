package me.advait.beheadthedragonproduction.files;

import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public abstract class YMLBase {

    /*
    Credit to github.com/IllusionTheDev for making this.
     */

    private final boolean existsOnSource;
    private final JavaPlugin plugin;

    @Getter
    private final File file;

    @Getter
    private FileConfiguration configuration;

    public YMLBase(JavaPlugin plugin, File file, boolean existsOnSource) {
        this.plugin = plugin;
        this.file = file;
        this.existsOnSource = existsOnSource;

        this.configuration = loadConfiguration();
        save();
    }

    public void save() {
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FileConfiguration loadConfiguration() {
        FileConfiguration cfg = new YamlConfiguration();

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            if (existsOnSource)
                plugin.saveResource(file.getName(), false);
            else {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            cfg.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        return cfg;
    }
}
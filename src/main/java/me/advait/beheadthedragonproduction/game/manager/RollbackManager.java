package me.advait.beheadthedragonproduction.game.manager;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import me.advait.beheadthedragonproduction.DragonPlugin;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class RollbackManager {

    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private final DragonPlugin plugin;

    public RollbackManager(DragonPlugin plugin) {
        this.plugin = plugin;
    }

    public void unloadWorld(World world) {
        if (!(world == null)) {
            Bukkit.unloadWorld(world.getName(), false);
        }
    }


}

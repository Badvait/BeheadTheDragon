package me.advait.beheadthedragonproduction.game.state;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import me.advait.beheadthedragonproduction.DragonPlugin;
import me.advait.beheadthedragonproduction.game.Arena;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public abstract class ArenaState implements Listener {

    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private DragonPlugin plugin;

    @Getter @Setter
    private Arena arena;

    public void onEnable(DragonPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void onDisable() {
        HandlerList.unregisterAll(this);
    }

}

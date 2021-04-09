package me.advait.beheadthedragonproduction.game.manager;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import me.advait.beheadthedragonproduction.DragonPlugin;
import me.advait.beheadthedragonproduction.game.Arena;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {

    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private final DragonPlugin plugin;

    @Getter
    private final List<Arena> activeArenas = new ArrayList<>();

    public ArenaManager(DragonPlugin plugin) {
        this.plugin = plugin;
    }

    public Arena getFirstAvailableArena() {
        return activeArenas.parallelStream().filter(Arena::canJoin)
                .findFirst()
                .orElse(new Arena(plugin));
    }

}

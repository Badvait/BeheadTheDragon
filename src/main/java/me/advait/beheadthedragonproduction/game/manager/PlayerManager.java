package me.advait.beheadthedragonproduction.game.manager;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.advait.beheadthedragonproduction.DragonPlugin;
import me.advait.beheadthedragonproduction.game.Arena;
import me.advait.beheadthedragonproduction.game.player.DragonPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class PlayerManager {

    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private DragonPlugin plugin;

    private final Map<UUID, DragonPlayer> players = new HashMap<>();

    public PlayerManager(DragonPlugin plugin) {
        this.plugin = plugin;
    }

    public DragonPlayer getDragonPlayer(Player player) {
        return getDragonPlayer(player.getUniqueId());
    }

    public DragonPlayer getDragonPlayer(UUID uuid) {
        return players.get(uuid);
    }

    public List<DragonPlayer> getPlayersInArena(Arena arena) {
        return players.values().stream().filter(player -> player.getArena().equals(arena)).collect(Collectors.toList());
    }

    public void sendArena(DragonPlayer player, Arena arena) {
        Player p = player.getBukkitPlayer();
        p.teleport(arena.getArenaMap().getPlayerSpawn());
    }

    public void addPlayer(DragonPlayer player) {
        players.put(player.getUuid(), player);
    }

    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
    }

}

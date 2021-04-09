package me.advait.beheadthedragonproduction.game.player;

import lombok.Data;
import me.advait.beheadthedragonproduction.DragonPlugin;
import me.advait.beheadthedragonproduction.game.Arena;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@Data
public class DragonPlayer {

    private final DragonPlugin plugin;
    private UUID uuid;
    private Arena arena;

    private ArenaRole arenaRole = ArenaRole.PLAYER;

    public DragonPlayer(DragonPlugin plugin, UUID uuid) {
        this.plugin = plugin;
        this.uuid = uuid;
    }

    public Player getBukkitPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public void setupPlayer() {
        Player player = getBukkitPlayer();
        player.getInventory().clear();
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.teleport(arena.getArenaMap().getPlayerSpawn());
        player.getActivePotionEffects().forEach(potionEffect ->
                player.removePotionEffect(potionEffect.getType()));
        this.arena = plugin.getArenaManager().getFirstAvailableArena();
        plugin.getPlayerManager().addPlayer(this);
        plugin.getPlayerManager().sendArena(this, arena);
    }

}

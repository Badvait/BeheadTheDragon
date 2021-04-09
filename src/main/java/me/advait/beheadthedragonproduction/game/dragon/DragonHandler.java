package me.advait.beheadthedragonproduction.game.dragon;

import com.ericdebouwer.petdragon.api.PetDragonAPI;
import lombok.Getter;
import me.advait.beheadthedragonproduction.DragonPlugin;
import me.advait.beheadthedragonproduction.game.Arena;
import me.advait.beheadthedragonproduction.game.player.ArenaRole;
import me.advait.beheadthedragonproduction.game.player.DragonPlayer;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DragonHandler {

    private final DragonPlugin plugin;
    @Getter
    private final Map<Arena, DragonPlayer> dragonOwners = new HashMap<>();

    public DragonHandler(DragonPlugin plugin) {
        this.plugin = plugin;
    }

    public DragonPlayer chooseDragonOwnerRandomly(Arena arena) {
        int random = new Random().nextInt(arena.getActivePlayers().size());
        DragonPlayer dragonPlayer = arena.getActivePlayers().get(random);
        setDragonOwner(dragonPlayer);
        dragonPlayer.getBukkitPlayer().setInvulnerable(true);
        return dragonPlayer;
    }

    public void setDragonOwner(DragonPlayer player) {
        player.setArenaRole(ArenaRole.DRAGON);
        dragonOwners.put(player.getArena(), player);
    }

    public void spawnDragon(Arena arena, DragonPlayer owner) {
        if (owner.getArenaRole() != ArenaRole.DRAGON) return;
        PetDragonAPI.getInstance().spawnDragon(arena.getArenaMap().getDragonSpawn(),
                owner.getUuid());
    }

    public void clearDragonsAndOwners(Arena arena) {
        for (Entity entity : arena.getArenaMap().getMapWorld().getEntities()) {
            if (entity instanceof EnderDragon) {
                entity.remove();
            }
        }
        DragonPlayer owner = dragonOwners.get(arena);
        owner.setArenaRole(ArenaRole.PLAYER);
        owner.getBukkitPlayer().setInvulnerable(false);
    }

}

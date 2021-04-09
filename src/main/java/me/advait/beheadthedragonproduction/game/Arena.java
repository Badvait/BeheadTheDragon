package me.advait.beheadthedragonproduction.game;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.advait.beheadthedragonproduction.DragonPlugin;
import me.advait.beheadthedragonproduction.game.map.ArenaMap;
import me.advait.beheadthedragonproduction.game.player.ArenaRole;
import me.advait.beheadthedragonproduction.game.player.DragonPlayer;
import me.advait.beheadthedragonproduction.game.state.ArenaState;
import me.advait.beheadthedragonproduction.game.state.WaitingArenaState;
import me.advait.beheadthedragonproduction.utility.Messages;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Data
public class Arena {

    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private DragonPlugin plugin;

    private ArenaMap arenaMap;
    @Getter
    private ArenaState arenaState;

    private int ID;

    public Arena(DragonPlugin plugin) {
        this.plugin = plugin;
        this.arenaMap = plugin.getMapManager().getLoadedMaps().get
                (new Random().nextInt(plugin.getMapManager().getLoadedMaps().size()));
        plugin.getArenaManager().getActiveArenas().add(this);
        this.ID = plugin.getArenaManager().getActiveArenas().indexOf(this);
        setArenaState(new WaitingArenaState());
    }

    public void setArenaState(ArenaState arenaState) {
        arenaState.setArena(this);
        if (this.arenaState.getClass() == arenaState.getClass()) return;
        this.arenaState.onDisable();
        this.arenaState = arenaState;
        this.arenaState.onEnable(plugin);
    }

    public boolean canJoin() {
        return arenaState instanceof WaitingArenaState;
    }

    public List<DragonPlayer> getActivePlayers() {
        List<DragonPlayer> players = new ArrayList<>();
        for (DragonPlayer player : plugin.getPlayerManager().getPlayersInArena(this)) {
            if (player.getArenaRole() == ArenaRole.PLAYER || player.getArenaRole() == ArenaRole.DRAGON) {
                players.add(player);
            }
        }
        return players;
    }

    public List<DragonPlayer> getSpectators() {
        return plugin.getPlayerManager().getPlayersInArena(this).stream().filter(player -> player.getArenaRole() == ArenaRole.SPECTATOR).collect(Collectors.toList());
    }

    public List<DragonPlayer> getAllPlayers() {
        return plugin.getPlayerManager().getPlayersInArena(this);
    }

    public void sendMessage(String message) {
        for (DragonPlayer player : plugin.getPlayerManager().getPlayersInArena(this)) {
            Player p = player.getBukkitPlayer();
            Messages.sendMessage(p, message);
        }
    }

}

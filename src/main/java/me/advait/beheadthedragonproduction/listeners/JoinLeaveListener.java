package me.advait.beheadthedragonproduction.listeners;

import me.advait.beheadthedragonproduction.DragonPlugin;
import me.advait.beheadthedragonproduction.game.Arena;
import me.advait.beheadthedragonproduction.game.player.DragonPlayer;
import me.advait.beheadthedragonproduction.game.state.StartingArenaState;
import me.advait.beheadthedragonproduction.utility.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {

    private final DragonPlugin plugin;

    public JoinLeaveListener(DragonPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        DragonPlayer dragonPlayer = new DragonPlayer(plugin, player.getUniqueId());
        dragonPlayer.setupPlayer();
        event.setJoinMessage(null);
        dragonPlayer.getArena().sendMessage("&#decd9e" + player.getName() + " &r&dteleported onto the island! " +
                Messages.playerCountFraction(dragonPlayer.getArena()));
        if (dragonPlayer.getArena().getActivePlayers().size() >= 8) {
            dragonPlayer.getArena().setArenaState(new StartingArenaState(false));
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        DragonPlayer dragonPlayer = plugin.getPlayerManager().getDragonPlayer(player);
        dragonPlayer.getArena().sendMessage("&#decd9e" + player.getName() + " &r&dleft the island! " +
                Messages.playerCountFraction(dragonPlayer.getArena()));
        plugin.getPlayerManager().removePlayer(player);
    }


}

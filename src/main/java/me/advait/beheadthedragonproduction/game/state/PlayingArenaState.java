package me.advait.beheadthedragonproduction.game.state;

import me.advait.beheadthedragonproduction.DragonPlugin;
import me.advait.beheadthedragonproduction.game.dragon.DragonHandler;
import me.advait.beheadthedragonproduction.game.player.ArenaRole;
import me.advait.beheadthedragonproduction.game.player.DragonPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

import javax.swing.plaf.SplitPaneUI;

public class PlayingArenaState extends ArenaState {

    private DragonPlugin plugin;
    private DragonHandler dragonHandler;
    private DragonPlayer dragonOwner;

    @Override
    public void onEnable(DragonPlugin plugin) {
        super.onEnable(plugin);
        this.plugin = plugin;
        this.dragonHandler = plugin.getDragonHandler();
        dragonHandler.chooseDragonOwnerRandomly(getArena());
        this.dragonOwner = dragonHandler.getDragonOwners().get(getArena());
        dragonHandler.spawnDragon(getArena(), dragonOwner);
    }

    @Override
    public void onDisable() {
        dragonHandler.clearDragonsAndOwners(getArena());
        super.onDisable();
    }

    @EventHandler
    public void onDismount(EntityDismountEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        DragonPlayer dragonPlayer = plugin.getPlayerManager().
                getDragonPlayer(event.getEntity());
        dragonPlayer.setArenaRole(ArenaRole.SPECTATOR);
        event.setDeathMessage(null);
        getArena().sendMessage("");
        // TODO: Send death message and see if a game win event is required.
    }


}

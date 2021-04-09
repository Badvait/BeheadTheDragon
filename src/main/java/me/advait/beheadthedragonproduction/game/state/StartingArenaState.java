package me.advait.beheadthedragonproduction.game.state;

import me.advait.beheadthedragonproduction.DragonPlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

public class StartingArenaState extends ArenaState {

    private boolean forceStart;
    private int timeLeft;
    private BukkitTask startingTask;

    public StartingArenaState(boolean forceStart) {
        this.forceStart = forceStart;
        this.timeLeft = 10;
    }

    @Override
    public void onEnable(DragonPlugin plugin) {
        super.onEnable(plugin);
        this.startingTask = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            getArena().sendMessage("&dThe game starts in &6" + timeLeft + " &dseconds!");
            if (getArena().getActivePlayers().size() < 7 && !forceStart) {
                getArena().sendMessage("&cThe game doesn't have enough players to start!");
                getArena().setArenaState(new WaitingArenaState());
                return;
            }
            if (timeLeft == 0) {
                getArena().setArenaState(new PlayingArenaState());
            }
            timeLeft--;
        }, 0, 20);
    }

    @Override
    public void onDisable() {
        startingTask.cancel();
        super.onDisable();
    }

}

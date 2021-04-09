package me.advait.beheadthedragonproduction;

import lombok.Getter;
import me.advait.beheadthedragonproduction.files.MapsFile;
import me.advait.beheadthedragonproduction.game.dragon.DragonHandler;
import me.advait.beheadthedragonproduction.game.manager.ArenaManager;
import me.advait.beheadthedragonproduction.game.manager.MapManager;
import me.advait.beheadthedragonproduction.game.manager.PlayerManager;
import me.advait.beheadthedragonproduction.game.manager.RollbackManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class DragonPlugin extends JavaPlugin {

    private DragonHandler dragonHandler;
    private ArenaManager arenaManager;
    private MapManager mapManager;
    private PlayerManager playerManager;
    private RollbackManager rollbackManager;

    private MapsFile mapsFile;

    @Override
    public void onEnable() {
        super.onEnable();
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        setupFiles();
        this.dragonHandler = new DragonHandler(this);
        this.arenaManager = new ArenaManager(this);
        this.mapManager = new MapManager(this);
        this.playerManager = new PlayerManager(this);
        this.rollbackManager = new RollbackManager(this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void setupFiles() {
        this.mapsFile = new MapsFile(this);
    }

}

package me.advait.beheadthedragonproduction.game.map;

import lombok.Data;
import org.bukkit.Location;
import org.bukkit.World;

@Data
public class ArenaMap {

    private World mapWorld;
    private String mapName;
    private Location playerSpawn;
    private Location dragonSpawn;
    private Location buildableCornerOne;
    private Location buildableCornerTwo;

}

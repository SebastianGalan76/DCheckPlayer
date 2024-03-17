package pl.dream.dcheckplayer.controller;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.Debug;
import pl.dream.dcheckplayer.DCheckPlayer;
import pl.dream.dreamlib.Config;

import java.util.HashSet;

public class ConfigController {
    private final FileConfiguration config;

    public ConfigController(FileConfiguration config){
        this.config = config;

        loadPlugin();
    }

    private void loadPlugin(){
        DCheckPlayer plugin = DCheckPlayer.getPlugin();

        plugin.allowedCommands = new HashSet<>(config.getStringList("allowedCommands"));

        plugin.checkCommands = config.getStringList("commands.check");
        plugin.clearCommands = config.getStringList("commands.clear");
        plugin.logoutCommands = config.getStringList("commands.logout");
        plugin.cheaterCommands = config.getStringList("commands.cheater");
        plugin.admissionCommands = config.getStringList("commands.admission");

        plugin.spawnLocation = Config.getLocation(config, "locations.spawn");
        plugin.cageLocation = Config.getLocation(config, "locations.cage");
    }

    public void setLocation(String path, Location location){
        Config.setLocation(DCheckPlayer.getPlugin(), path, location);
        DCheckPlayer.getPlugin().saveConfig();
    }
}

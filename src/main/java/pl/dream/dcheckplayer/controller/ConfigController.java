package pl.dream.dcheckplayer.controller;

import org.bukkit.configuration.file.FileConfiguration;
import pl.dream.dcheckplayer.DCheckPlayer;

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
    }
}

package pl.dream.dcheckplayer;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pl.dream.dcheckplayer.command.AdmissionCommand;
import pl.dream.dcheckplayer.command.CheckCommand;
import pl.dream.dcheckplayer.controller.ConfigController;
import pl.dream.dcheckplayer.data.AdminPlayer;
import pl.dream.dcheckplayer.data.SuspectPlayer;
import pl.dream.dcheckplayer.listener.ExecuteCommandListener;
import pl.dream.dcheckplayer.listener.PlayerJoinListener;
import pl.dream.dcheckplayer.listener.PlayerQuitListener;
import pl.dream.dcheckplayer.listener.SendMessageListener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class DCheckPlayer extends JavaPlugin {
    private static DCheckPlayer plugin;

    public HashMap<String, SuspectPlayer> suspects;
    public HashMap<String, AdminPlayer> admins;

    public ConfigController configController;

    public Location spawnLocation;
    public Location cageLocation;

    public Set<String> allowedCommands;

    public List<String> checkCommands;
    public List<String> clearCommands;
    public List<String> logoutCommands;
    public List<String> cheaterCommands;
    public List<String> admissionCommands;

    @Override
    public void onEnable() {
        plugin = this;

        suspects = new HashMap<>();
        admins = new HashMap<>();

        loadPlugin();

        getCommand("check").setExecutor(new CheckCommand());
        getCommand("przyznaje").setExecutor(new AdmissionCommand());

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new SendMessageListener(), this);
        getServer().getPluginManager().registerEvents(new ExecuteCommandListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void reloadPlugin(){
        reloadConfig();

        loadPlugin();
    }

    private void loadPlugin(){
        saveDefaultConfig();
        Locale.loadMessages(this);
        configController = new ConfigController(getConfig());
    }

    public static DCheckPlayer getPlugin(){
        return plugin;
    }
}

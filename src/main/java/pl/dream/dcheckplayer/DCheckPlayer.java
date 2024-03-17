package pl.dream.dcheckplayer;

import org.bukkit.plugin.java.JavaPlugin;

public final class DCheckPlayer extends JavaPlugin {
    private static DCheckPlayer plugin;
    
    @Override
    public void onEnable() {
        plugin = this;

        loadPlugin();
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


    }

    public static DCheckPlayer getPlugin(){
        return plugin;
    }
}

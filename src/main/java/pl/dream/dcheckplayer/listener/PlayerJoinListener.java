package pl.dream.dcheckplayer.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.dream.dcheckplayer.DCheckPlayer;
import pl.dream.dcheckplayer.data.AdminPlayer;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        if(player.hasPermission("dcheckplayer.check")){
            DCheckPlayer.getPlugin().admins.put(player.getName(), new AdminPlayer(player));
        }
    }
}

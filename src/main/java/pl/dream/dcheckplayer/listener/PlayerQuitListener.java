package pl.dream.dcheckplayer.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.dream.dcheckplayer.DCheckPlayer;
import pl.dream.dcheckplayer.data.SuspectPlayer;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        SuspectPlayer suspect = DCheckPlayer.getPlugin().suspects.get(e.getPlayer().getName());
        if(suspect!=null){
            suspect.logout();
        }
    }
}

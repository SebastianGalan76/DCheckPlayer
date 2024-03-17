package pl.dream.dcheckplayer.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import pl.dream.dcheckplayer.DCheckPlayer;
import pl.dream.dcheckplayer.Locale;
import pl.dream.dreamlib.Message;

public class ExecuteCommandListener implements Listener {

    @EventHandler
    public void onExecuteCommand(PlayerCommandPreprocessEvent e){
        if(DCheckPlayer.getPlugin().suspects.containsKey(e.getPlayer().getName())){
            String[] parts = e.getMessage().split("\\s+");

            if(!DCheckPlayer.getPlugin().allowedCommands.contains(parts[0].substring(1))){
                Message.sendMessage(e.getPlayer(), Locale.DISALLOWED_COMMAND.toString());
                e.setCancelled(true);
            }
        }
    }
}

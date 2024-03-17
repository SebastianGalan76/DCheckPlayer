package pl.dream.dcheckplayer.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.dream.dcheckplayer.DCheckPlayer;
import pl.dream.dcheckplayer.data.AdminPlayer;
import pl.dream.dcheckplayer.data.SuspectPlayer;
import pl.dream.dreamlib.Color;

public class SendMessageListener implements Listener {

    @EventHandler
    public void onSendMessage(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();

        if(DCheckPlayer.getPlugin().suspects.containsKey(player.getName())){
            e.getRecipients().clear();
            for(AdminPlayer admin:DCheckPlayer.getPlugin().admins.values()){
                e.getRecipients().add(admin.getPlayer());
            }
            e.getRecipients().add(player);

            String formattedMessage = String.format(e.getFormat(), player.getName(), e.getMessage());
            String message = Color.fix(" &c[!] "+formattedMessage);
            e.setMessage(message);
        }

        AdminPlayer adminPlayer = DCheckPlayer.getPlugin().admins.get(player.getName());
        if(adminPlayer!=null && adminPlayer.chat){
            e.getRecipients().clear();
            for(AdminPlayer admin:DCheckPlayer.getPlugin().admins.values()){
                e.getRecipients().add(admin.getPlayer());
            }
            for(SuspectPlayer suspect:DCheckPlayer.getPlugin().suspects.values()){
                e.getRecipients().add(suspect.getPlayer());
            }

            String formattedMessage = String.format(e.getFormat(), player.getName(), e.getMessage());
            String message = Color.fix(" &c[!] "+formattedMessage);
            e.setMessage(message);
        }
    }
}

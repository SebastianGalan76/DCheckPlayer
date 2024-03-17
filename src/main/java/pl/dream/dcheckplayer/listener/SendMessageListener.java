package pl.dream.dcheckplayer.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.dream.dcheckplayer.DCheckPlayer;
import pl.dream.dcheckplayer.data.AdminPlayer;
import pl.dream.dcheckplayer.data.SuspectPlayer;
import pl.dream.dreamlib.Color;
import pl.dream.dreamlib.Message;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SendMessageListener implements Listener {

    @EventHandler
    public void onSendMessage(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();

        if(DCheckPlayer.getPlugin().suspects.containsKey(player.getName())){
            Set<Player> recipients = new HashSet<>();
            recipients.add(player);
            for(AdminPlayer admin:DCheckPlayer.getPlugin().admins.values()){
                recipients.add(admin.getPlayer());
            }

            String formattedMessage = String.format(e.getFormat(), player.getName(), e.getMessage());
            String message = Color.fix(" &c[!] "+formattedMessage);
            e.setCancelled(true);
            sendMessage(recipients, message);
        }

        AdminPlayer adminPlayer = DCheckPlayer.getPlugin().admins.get(player.getName());
        if(adminPlayer!=null && adminPlayer.chat){
            Set<Player> recipients = new HashSet<>();
            recipients.add(player);
            for(AdminPlayer admin:DCheckPlayer.getPlugin().admins.values()){
                recipients.add(admin.getPlayer());
            }
            for(SuspectPlayer suspect:DCheckPlayer.getPlugin().suspects.values()){
                recipients.add(suspect.getPlayer());
            }

            String formattedMessage = String.format(e.getFormat(), player.getName(), e.getMessage());
            String message = Color.fix(" &c[!] "+formattedMessage);
            e.setCancelled(true);
            sendMessage(recipients, message);
        }
    }

    public void sendMessage(Set<Player> players, String message){
        for(Player p:players){
            Message.sendMessage(p, message);
        }
    }
}

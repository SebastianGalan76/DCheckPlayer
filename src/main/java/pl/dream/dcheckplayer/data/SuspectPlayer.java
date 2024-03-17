package pl.dream.dcheckplayer.data;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.dream.dcheckplayer.DCheckPlayer;

import java.util.List;

public class SuspectPlayer extends LocalPlayer{
    public SuspectPlayer(Player player) {
        super(player);
    }

    public void check(){
        DCheckPlayer.getPlugin().suspects.put(player.getName(), this);
        executeCommands(DCheckPlayer.getPlugin().checkCommands);
    }

    public void clear(){
        DCheckPlayer.getPlugin().suspects.remove(player.getName());
        executeCommands(DCheckPlayer.getPlugin().clearCommands);
    }

    public void cheater(){
        DCheckPlayer.getPlugin().suspects.remove(player.getName());
        executeCommands(DCheckPlayer.getPlugin().cheaterCommands);
    }

    public void admission(){
        DCheckPlayer.getPlugin().suspects.remove(player.getName());
        executeCommands(DCheckPlayer.getPlugin().admissionCommands);
    }

    public void logout(){
        DCheckPlayer.getPlugin().suspects.remove(player.getName());
        executeCommands(DCheckPlayer.getPlugin().logoutCommands);
    }

    private void executeCommands(List<String> commands){
        if(commands == null || commands.isEmpty()){
            return;
        }

        CommandSender console = Bukkit.getConsoleSender();
        for(String command:commands){
            Bukkit.dispatchCommand(console, command
                    .replace("{PLAYER}", player.getName()));
        }
    }
}

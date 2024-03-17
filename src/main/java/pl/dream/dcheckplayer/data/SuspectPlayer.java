package pl.dream.dcheckplayer.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;
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

        teleport(player, DCheckPlayer.getPlugin().cageLocation);
    }

    public void clear(){
        DCheckPlayer.getPlugin().suspects.remove(player.getName());
        executeCommands(DCheckPlayer.getPlugin().clearCommands);

        teleport(player, DCheckPlayer.getPlugin().spawnLocation);
    }

    public void cheater(){
        DCheckPlayer.getPlugin().suspects.remove(player.getName());
        executeCommands(DCheckPlayer.getPlugin().cheaterCommands);

        teleport(player, DCheckPlayer.getPlugin().spawnLocation);
    }

    public void admission(){
        DCheckPlayer.getPlugin().suspects.remove(player.getName());
        executeCommands(DCheckPlayer.getPlugin().admissionCommands);

        teleport(player, DCheckPlayer.getPlugin().spawnLocation);
    }

    public void logout(){
        DCheckPlayer.getPlugin().suspects.remove(player.getName());
        executeCommands(DCheckPlayer.getPlugin().logoutCommands);

        teleport(player, DCheckPlayer.getPlugin().spawnLocation);
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

    private void teleport(Player player, Location location){
        if(player == null || location == null){
            return;
        }

        player.teleport(location);
    }
}

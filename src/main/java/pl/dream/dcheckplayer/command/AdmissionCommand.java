package pl.dream.dcheckplayer.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import pl.dream.dcheckplayer.DCheckPlayer;
import pl.dream.dcheckplayer.Locale;
import pl.dream.dcheckplayer.data.SuspectPlayer;
import pl.dream.dreamlib.Message;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AdmissionCommand implements CommandExecutor {

    private HashMap<UUID, BukkitTask> confirmations;

    public AdmissionCommand(){
        confirmations = new HashMap<>();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            Message.sendMessage(sender, Locale.ONLY_PLAYER.toString());
            return true;
        }

        if(args.length==1){
            if(args[0].equalsIgnoreCase("sie") || args[0].equalsIgnoreCase("się")){
                SuspectPlayer suspectPlayer = DCheckPlayer.getPlugin().suspects.get(sender.getName());

                if(suspectPlayer==null){
                    Player player = (Player)sender;
                    BukkitTask bukkitTask = confirmations.get(player.getUniqueId());

                    if(bukkitTask!=null){
                        bukkitTask.cancel();

                        new SuspectPlayer(player).admission();
                        return true;
                    }

                    Message.sendMessage(sender, Locale.ADMISSION_CONFIRMATION.toString());
                    bukkitTask = DCheckPlayer.getPlugin().getServer().getScheduler().runTaskLaterAsynchronously(DCheckPlayer.getPlugin(), new Runnable() {
                        @Override
                        public void run() {
                            confirmations.remove(player.getUniqueId());
                        }
                    }, 400);

                    confirmations.put(player.getUniqueId(), bukkitTask);
                }
                else{
                    suspectPlayer.admission();
                }
            }
        }
        else{
            Message.sendMessage(sender, Locale.ADMISSION_USAGE.toString());
        }

        return true;
    }
}

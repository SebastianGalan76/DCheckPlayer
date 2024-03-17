package pl.dream.dcheckplayer.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.dream.dcheckplayer.DCheckPlayer;
import pl.dream.dcheckplayer.Locale;
import pl.dream.dcheckplayer.data.AdminPlayer;
import pl.dream.dcheckplayer.data.SuspectPlayer;
import pl.dream.dreamlib.Message;

import java.util.List;

public class CheckCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(args.length==1){
            if(!checkPermission(sender, "dcheckplayer.staff")){
                return true;
            }

            AdminPlayer admin;
            if(sender instanceof Player){
                admin = DCheckPlayer.getPlugin().admins.get(sender.getName());

                if(admin==null){
                    admin = new AdminPlayer(((Player) sender).getPlayer());
                    DCheckPlayer.getPlugin().admins.put(sender.getName(), admin);
                }
            }
            else{
                admin = null;
            }

            if(args[0].equalsIgnoreCase("reload")){
                if(checkPermission(sender, "dcheckplayer.admin")){
                    DCheckPlayer.getPlugin().reloadPlugin();
                    Message.sendMessage(sender, Locale.RELOAD.toString());
                }

                return true;
            }
            else if(args[0].equalsIgnoreCase("setspawn")){
                if(checkPermission(sender, "dcheckplayer.admin")){
                    if(sender instanceof Player){
                        DCheckPlayer.getPlugin().configController.setLocation("locations.spawn", ((Player) sender).getLocation());
                        Message.sendMessage(sender, Locale.SET_LOCATION.toString());
                    }
                }

                return true;
            }
            else if(args[0].equalsIgnoreCase("setcage")){
                if(checkPermission(sender, "dcheckplayer.admin")){
                    if(sender instanceof Player){
                        DCheckPlayer.getPlugin().configController.setLocation("locations.cage", ((Player) sender).getLocation());
                        Message.sendMessage(sender, Locale.SET_LOCATION.toString());
                    }
                }

                return true;
            }
            else if(args[0].equalsIgnoreCase("chat")){
                if(admin!=null){
                    admin.chat = !admin.chat;

                    //TODO switch chat
                }
                else{
                    Message.sendMessage(sender, Locale.ONLY_PLAYER.toString());
                }

                return true;
            }

            Player player = Bukkit.getPlayer(args[0]);
            if(player==null){
                Message.sendMessage(sender, Locale.NO_PLAYER.toString());
                return true;
            }

            SuspectPlayer suspectPlayer = DCheckPlayer.getPlugin().suspects.get(args[0]);
            if(suspectPlayer==null){
                suspectPlayer = new SuspectPlayer(player);

                if(label.equalsIgnoreCase("czysty")){
                    suspectPlayer.clear();
                }
                else{
                    suspectPlayer.check();
                }
            }
            else{
                Message.sendMessage(sender, Locale.PLAYER_IS_ALREADY_CHECKED.toString());
                return true;
            }
        }
        else if(args.length==2){
            if(!checkPermission(sender, "dcheckplayer.staff")){
                return true;
            }

            AdminPlayer admin;
            if(sender instanceof Player){
                admin = DCheckPlayer.getPlugin().admins.get(sender.getName());

                if(admin==null){
                    admin = new AdminPlayer(((Player) sender).getPlayer());
                    DCheckPlayer.getPlugin().admins.put(sender.getName(), admin);
                }
            }
            else{
                admin = null;
            }

            if(args[0].equalsIgnoreCase("chat")){
                if(admin!=null){
                    if(args[1].equalsIgnoreCase("off")){
                        admin.chat = false;
                    }
                    else if(args[1].equalsIgnoreCase("on")){
                        admin.chat = true;
                    }

                    //TODO switch chat
                }
                else{
                    Message.sendMessage(sender, Locale.ONLY_PLAYER.toString());
                }

                return true;
            }

            Player player = Bukkit.getPlayer(args[0]);
            if(player==null){
                Message.sendMessage(sender, Locale.NO_PLAYER.toString());
                return true;
            }

            SuspectPlayer suspectPlayer = DCheckPlayer.getPlugin().suspects.get(args[0]);
            if(suspectPlayer==null){
                Message.sendMessage(sender, Locale.PLAYER_IS_NOT_CHECKED.toString());
                return true;
            }

            if(args[1].equalsIgnoreCase("clear")){
                suspectPlayer.clear();
                return true;
            }
            else if(args[1].equalsIgnoreCase("cheater")){
                suspectPlayer.cheater();
                return true;
            }
            else if(args[1].equalsIgnoreCase("admission")){
                suspectPlayer.admission();
                return true;
            }
        }
        else {
            showHelp(sender);
        }

        return true;
    }

    private boolean checkPermission(CommandSender sender, String permission){
        if(!sender.hasPermission(permission)){
            Message.sendMessage(sender, Locale.NO_PERMISSION.toString());
            return false;
        }

        return true;
    }

    private void showHelp(CommandSender sender){
        if(sender.hasPermission("dcheckplayer.admin")){
            if(sender instanceof Player){
                List<String> messageList = Locale.HELP_STAFF.getList();
                messageList.addAll(Locale.HELP_ADMIN.getList());

                Message.sendMessage(sender, messageList);
                return;
            }
        }

        if(checkPermission(sender, "dcheckplayer.staff")){
            Message.sendMessage(sender, Locale.HELP_STAFF.getList());
        }
    }
}

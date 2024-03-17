package pl.dream.dcheckplayer.data;

import org.bukkit.entity.Player;
import pl.dream.dcheckplayer.DCheckPlayer;

public class SuspectPlayer extends LocalPlayer{
    public SuspectPlayer(Player player) {
        super(player);
    }

    public void check(){
        DCheckPlayer.getPlugin().suspects.put(player.getName(), this);
    }

    public void clear(){

    }

    public void cheater(){

    }

    public void admission(){

    }

    public void logout(){

    }
}

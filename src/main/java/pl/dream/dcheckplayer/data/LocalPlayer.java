package pl.dream.dcheckplayer.data;

import org.bukkit.entity.Player;

public class LocalPlayer {
    protected Player player;

    public LocalPlayer(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }
}

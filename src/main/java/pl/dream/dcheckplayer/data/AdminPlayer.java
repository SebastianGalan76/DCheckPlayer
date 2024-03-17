package pl.dream.dcheckplayer.data;

import org.bukkit.entity.Player;

public class AdminPlayer extends LocalPlayer{
    public boolean chat;

    public AdminPlayer(Player player) {
        super(player);
    }
}

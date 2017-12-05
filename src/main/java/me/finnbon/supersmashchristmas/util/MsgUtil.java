package me.finnbon.supersmashchristmas.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public enum MsgUtil {

    ARENA_FULL("This arena is already full!", true),
    ARENA_STARTED("This arena has already started!", true),
    ARENA_START("Enough players have joined! Starting the countdown!", false),
    ARENA_LEAVE("You have left the arena!", false),
    ARENA_LEAVE_OTHER("%player% has left the arena!", false),
    ARENA_FIGHT("Let the fight begin!", false),
    ARENA_END("The winner is %player%", false),

    CMD_PLAYER_ONLY("Only players can execute this command!", true),
    CMD_LIST("That is not a valid argument! Type /ss and then press tab too see all available arguments.", true),

    CHAMPION_SET("Your champion has been set to %champ%", false),
    ;

    private final String msg;
    private final boolean error;

    MsgUtil(String msg, boolean error) {
        this.msg = msg;
        this.error = error;
    }

    public void send(CommandSender receiver) {
        receiver.sendMessage((error ? RED : YELLOW) + ChatColor.translateAlternateColorCodes('&', msg));
    }

    public void set(String s, String name, CommandSender receiver) {
        receiver.sendMessage((error ? RED : YELLOW) + ChatColor.translateAlternateColorCodes('&', msg.replace(s, name)));
    }

    public void send(Brawler br) {
        send(br.getPlayer());
    }

    public void set(String s, String name, Brawler br) {
        set(s, name, br.getPlayer());
    }

    public String get() {
        return msg;
    }

    public String get(String s, String name) {
        return msg.replace(s, name);
    }

}
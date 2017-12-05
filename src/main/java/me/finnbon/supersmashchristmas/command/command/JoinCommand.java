package me.finnbon.supersmashchristmas.command.command;

import me.finnbon.supersmashchristmas.command.ICommand;
import org.bukkit.entity.Player;

import java.util.List;

public class JoinCommand extends ICommand {

    @Override
    public void execute(Player player, String[] args) {

    }

    @Override
    public List<String> tab(Player player, String[] args) {
        return null;
    }

    public String getName() {
        return "join";
    }

    public String[] getArgs() {
        return new String[]{"j"};
    }

}

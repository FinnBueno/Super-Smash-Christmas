package me.finnbon.supersmashchristmas.command;

import org.bukkit.entity.Player;

import java.util.List;

public abstract class ICommand {

    public abstract void execute(Player player, String[] args);

    public abstract List<String> tab(Player player, String[] args);

    public abstract String getName();

    public abstract String[] getArgs();

}
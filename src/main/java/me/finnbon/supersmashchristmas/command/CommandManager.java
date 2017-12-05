package me.finnbon.supersmashchristmas.command;

import me.finnbon.supersmashchristmas.command.command.JoinCommand;
import me.finnbon.supersmashchristmas.util.MsgUtil;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class CommandManager implements CommandExecutor, TabExecutor {

    private final Set<ICommand> commands;

    public CommandManager() {
        commands = new HashSet<>();
        commands.addAll(Collections.singletonList(new JoinCommand()));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String lbl, String[] args) {
        if (!(sender instanceof Player)) {
            MsgUtil.CMD_PLAYER_ONLY.send(sender);
            return true;
        }
        if (args.length == 0) {
            MsgUtil.CMD_LIST.send(sender);
            return true;
        }

        for (ICommand icmd : commands)
            if (icmd.getName().equalsIgnoreCase(args[0]) || ArrayUtils.contains(icmd.getArgs(), args[0])) {
                String[] shorterArgs = new String[args.length - 1];
                System.arraycopy(args, 1, shorterArgs, 0, args.length - 1);
                icmd.execute((Player) sender, shorterArgs);
            }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String lbl, String[] args) {
        if (args.length == 0)
            return commands.stream().map(ICommand::getName).collect(Collectors.toList());
        return commands.stream().map(ICommand::getName).filter(name -> name.toLowerCase().startsWith(args[0].toLowerCase())).collect(Collectors.toList());
    }

}
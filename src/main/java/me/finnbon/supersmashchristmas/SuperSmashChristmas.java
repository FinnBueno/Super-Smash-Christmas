package me.finnbon.supersmashchristmas;

import me.finnbon.supersmashchristmas.arena.ArenaManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperSmashChristmas extends JavaPlugin {

    private ArenaManager arenaManager;
    private static SuperSmashChristmas instance;

    @Override
    public void onEnable() {
        // assert plugin directory
        instance = this;

        if (!getDataFolder().exists())
            getDataFolder().mkdir();
    }

    @Override
    public void onDisable() {

    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public static SuperSmashChristmas getPlugin() {
        return instance;
    }
}

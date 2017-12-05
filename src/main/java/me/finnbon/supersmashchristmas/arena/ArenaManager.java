package me.finnbon.supersmashchristmas.arena;

import com.google.gson.Gson;
import me.finnbon.supersmashchristmas.SuperSmashChristmas;
import me.finnbon.supersmashchristmas.util.Brawler;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class ArenaManager {

    private final Set<Arena> arenas = new HashSet<>();
    private final Gson gson;
    private final SuperSmashChristmas plugin;

    public ArenaManager(SuperSmashChristmas pl) {
        plugin = pl;
        gson = new Gson();
        importArenas();
    }

    private void importArenas() {
        File[] files = plugin.getDataFolder().listFiles();
        for (File file : files)
            if (file.getName().startsWith("valid_arena_file_") && file.getName().endsWith(".json")) {
                try (Reader reader = new FileReader(file)) {
                    Arena arena = gson.fromJson(reader, Arena.class);
                    arenas.add(arena);
                } catch (Exception e) {
                    plugin.getLogger().warning("An error occurred while trying to load arena file '" + file.getName() + "'!");
                }
                plugin.getLogger().info("Successfully loaded arena file '" + file.getName() + "'!");
        }
    }

    private void saveArenas() {
        for (Arena arena : arenas)
            saveArena(arena);
    }

    public void saveArena(Arena arena) {
        try (FileWriter writer = new FileWriter(plugin.getDataFolder() + File.separator + "valid_arena_file_" + arena.getName() + ".json")) {
            gson.toJson(arena, writer);
            plugin.getLogger().info("Successfully saved arena '" + arena.getName() + "'!");
        } catch (IOException e) {
            plugin.getLogger().warning("An error occurred while trying to save arena '" + arena.getName() + "'!");
        }
    }

    public Brawler getBrawler(Player player) {
        for (Arena arena : arenas) {
            Brawler brawler = arena.getBrawler(player);
            if (brawler == null)
                continue;
            return brawler;
        }
        return null;
    }
}
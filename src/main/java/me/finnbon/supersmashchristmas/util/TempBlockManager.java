package me.finnbon.supersmashchristmas.util;

import me.finnbon.supersmashchristmas.SuperSmashChristmas;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class TempBlockManager extends BukkitRunnable {

    private final SuperSmashChristmas plugin;
    private final Map<TempBlock, Long> temps = new HashMap<>();

    public TempBlockManager(SuperSmashChristmas pl) {
        plugin = pl;
        runTaskTimer(plugin, 0L, 1L);
    }

    public void createBlock(Block block, Material mat, long duration) {
        temps.put(new TempBlock(block, mat), System.currentTimeMillis() + duration);
    }

    @Override
    public void run() {

    }

}

package me.finnbon.supersmashchristmas.util;

import me.finnbon.supersmashchristmas.arena.Arena;
import me.finnbon.supersmashchristmas.champion.Champion;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class Brawler {

    private final Player player;
    private final Arena arena;
    private final PlayerInventory previousInv;
    private final Location previousLoc;
    private final int previousExp;
    private Champion champion;

    public Brawler(Player pl, Arena ar) {
        previousLoc = pl.getLocation();
        previousInv = pl.getInventory();
        previousExp = pl.getTotalExperience();
        player = pl;
        arena = ar;

        // clear inventory
        pl.getInventory().clear();
    }

    public Player getPlayer() {
        return player;
    }

    public Arena getArena() {
        return arena;
    }

    public Champion getChampion() {
        return champion;
    }

    public void teleport(Location location) {
        player.teleport(location);
    }

    public void setChampion(Champion champion) {
        this.champion = champion;
        MsgUtil.CHAMPION_SET.set("%champ%", champion.getName(), player);
    }

    public void leave() {
        // reset inventory
        player.getInventory().setContents(previousInv.getContents());
        player.getInventory().setArmorContents(previousInv.getArmorContents());
        player.getInventory().setHeldItemSlot(previousInv.getHeldItemSlot());

        // reset exp
        player.setTotalExperience(previousExp);

        // reset location
        player.teleport(previousLoc);
    }
}

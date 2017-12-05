package me.finnbon.supersmashchristmas.ability;

import me.finnbon.supersmashchristmas.SuperSmashChristmas;
import me.finnbon.supersmashchristmas.util.Brawler;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Ability extends BukkitRunnable {

    private final Brawler caster;

    public Ability(Brawler player) {
        this.caster = player;
    }

    public abstract boolean progress();

    public abstract void stop();

    public void start() {
        runTaskTimer(SuperSmashChristmas.getPlugin(), 0L, 1L);
    }

    @Override
    public void run() {
        if (!progress())
            cancel();
    }

    @Override
    public void cancel() {
        super.cancel();
        stop();
    }

    protected Set<? extends Entity> getEntitiesAroundPoint(Location loc, double radius) {
        return loc.getWorld().getNearbyEntities(loc, radius, radius, radius).stream()
                .filter(e -> e.getLocation().distanceSquared(loc) <= radius * radius).collect(Collectors.toSet());
    }

}
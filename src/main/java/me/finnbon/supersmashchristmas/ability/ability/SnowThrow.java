package me.finnbon.supersmashchristmas.ability.ability;

import me.finnbon.supersmashchristmas.ability.Ability;
import me.finnbon.supersmashchristmas.util.Brawler;
import me.finnbon.supersmashchristmas.util.ConfigOption;
import org.bukkit.Location;
import org.bukkit.entity.Snowball;

public class SnowThrow extends Ability {

    private final Snowball snowball;

    public SnowThrow(Brawler player) {
        super(player);
        Location inFront = player.getPlayer().getEyeLocation().add(player.getPlayer().getLocation().getDirection().multiply(.2));
        snowball = player.getPlayer().getWorld().spawn(inFront, Snowball.class);
        snowball.setVelocity(player.getPlayer().getLocation().getDirection().multiply(ConfigOption.SNOWBALL_SPEED.get()));
        start();
    }

    @Override
    public boolean progress() {
        return false;
    }

    @Override
    public void stop() {

    }
}

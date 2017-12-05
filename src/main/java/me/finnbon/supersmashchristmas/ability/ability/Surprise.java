package me.finnbon.supersmashchristmas.ability.ability;

import me.finnbon.supersmashchristmas.ability.Ability;
import me.finnbon.supersmashchristmas.util.Brawler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Surprise extends Ability {

    public Surprise(Brawler player) {
        super(player);
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

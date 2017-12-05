package me.finnbon.supersmashchristmas.champion;

import me.finnbon.supersmashchristmas.ability.Abilities;
import me.finnbon.supersmashchristmas.ability.Ability;
import me.finnbon.supersmashchristmas.ability.CastType;
import me.finnbon.supersmashchristmas.util.Brawler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public abstract class Champion {

    private final List<Abilities> abilities;
    private final String name;
    private final ItemStack icon;

    public Champion(String name, ItemStack icon, Abilities mainAbility, Abilities specialAbility,
                    Abilities movementAbility, Abilities ultAbility) {
        this.name = name;
        this.icon = icon;
        abilities = Arrays.asList(mainAbility, specialAbility, movementAbility, ultAbility);
    }

    public abstract void passive(Brawler player);

    public String getName() {
        return name;
    }

    public void preparePlayer(Brawler brawler) {
        Player player = brawler.getPlayer();
        player.getInventory().clear();

        // TODO: Add items to player
        for (int i = 0; i < abilities.size(); i++)
            player.getInventory().setItem(i, abilities.get(i).getIcon());

    }

    public void castAbility(Brawler brawler, CastType castType) {
        int slot = brawler.getPlayer().getInventory().getHeldItemSlot();
        if (slot > 3)
            return;
        Abilities ab = abilities.get(slot);
        if (ab.getCastType() != castType)
            return;
        ab.instantiate(brawler);
    }

}
package me.finnbon.supersmashchristmas.ability;

import me.finnbon.supersmashchristmas.ability.ability.SnowThrow;
import me.finnbon.supersmashchristmas.ability.ability.Surprise;
import me.finnbon.supersmashchristmas.util.Brawler;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum Abilities {

    // reindeer

    // snowman
    SNOWTHROW(CastType.CLICK, SnowThrow.class, null),

    // santa
    SURPRISE(CastType.CLICK, Surprise.class, null)
    ;

    private final Class<? extends Ability> abilityClass;
    private final CastType castType;
    private final ItemStack icon;
    private Constructor<? extends Ability> abilityConstructor;

    Abilities(CastType type, Class<? extends Ability> abCls, ItemStack it) {
        castType = type;
        abilityClass = abCls;
        try {
            abilityConstructor = abilityClass.getConstructor(Player.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        icon = it;
    }

    public CastType getCastType() {
        return castType;
    }

    public void instantiate(Brawler player) {
        try {
            abilityConstructor.newInstance(player);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public ItemStack getIcon() {
        return icon;
    }
}
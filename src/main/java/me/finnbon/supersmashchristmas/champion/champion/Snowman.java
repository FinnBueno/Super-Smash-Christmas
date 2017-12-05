package me.finnbon.supersmashchristmas.champion.champion;

import me.finnbon.supersmashchristmas.ability.Abilities;
import me.finnbon.supersmashchristmas.champion.Champion;
import me.finnbon.supersmashchristmas.util.Brawler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

public class Snowman extends Champion {

    public Snowman(String name, ItemStack icon, Abilities mainAbility, Abilities specialAbility, Abilities movementAbility, Abilities ultAbility) {
        super(name, icon, mainAbility, specialAbility, movementAbility, ultAbility);
    }

    @Override
    public void passive(Brawler player) {
        // snow create
        Location loc = player.getPlayer().getLocation();

        for (double x = -1; x <= 1; x++)
            for (double z = -1; z <= 1; z++) {

                loc.add(x, 0, z);

                // TODO: Add temp blocks
                Block block = loc.getBlock();
                if (block.getType().isSolid()) {
                    if (!block.getRelative(BlockFace.UP).getType().isSolid())
                        block.setType(Material.SNOW);
                } else if (!block.getRelative(BlockFace.DOWN).getType().isSolid()) {
                    if (block.getRelative(BlockFace.DOWN, 2).getType().isSolid())
                        block.getRelative(BlockFace.DOWN).setType(Material.SNOW);
                }

                loc.subtract(x, 0, z);

            }
    }

}

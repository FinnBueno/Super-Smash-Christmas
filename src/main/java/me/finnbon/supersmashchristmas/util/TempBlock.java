package me.finnbon.supersmashchristmas.util;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;

public class TempBlock {

    private final BlockState oldState;
    private final Block block;

    TempBlock(Block bl, Material mat) {
        block = bl;
        oldState = block.getState();
        block.setType(mat);
    }

    public void revert() {
        block.setType(oldState.getType());
        block.getState().setData(oldState.getData());
    }

}

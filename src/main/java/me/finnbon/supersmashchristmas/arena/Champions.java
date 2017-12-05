package me.finnbon.supersmashchristmas.arena;

import me.finnbon.supersmashchristmas.champion.Champion;

import java.util.concurrent.ThreadLocalRandom;

public enum Champions {

    SANTA, SNOWMAN, REINDEER;

    private Champion instance;

    public Champion getInstance() {
        return instance;
    }

    public static Champions getRandomChamp() {
        return values()[ThreadLocalRandom.current().nextInt(values().length)];
    }

}

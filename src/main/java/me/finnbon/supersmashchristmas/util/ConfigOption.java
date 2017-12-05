package me.finnbon.supersmashchristmas.util;

import me.finnbon.supersmashchristmas.SuperSmashChristmas;
import org.apache.commons.lang.WordUtils;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigOption<T> {

    public static final ConfigOption<Double> SNOWBALL_SPEED = new ConfigOption<>("Abilities.Snowman.Snowball.Speed", 1.2);

    private final String path;
    private final T defaultValue;
    private T value;

    private ConfigOption(String path, T defaultValue) {
        this.path = path;
        this.defaultValue = defaultValue;
    }

    private void create(FileConfiguration config) {
        config.addDefault(path, defaultValue);
    }

    private void load(FileConfiguration config) {
        value = (T) config.get(path);
    }

    public T get() {
        return value;
    }

    public static ConfigOption[] values() {
        return new ConfigOption[]{
            SNOWBALL_SPEED
        };
    }

    public static void initialize(SuperSmashChristmas plugin) {
        FileConfiguration config = plugin.getConfig();
        for (ConfigOption co : values())
            co.create(config);
        config.options().copyDefaults(true);
        plugin.saveConfig();
        for (ConfigOption co : values())
            co.load(config);
    }


}

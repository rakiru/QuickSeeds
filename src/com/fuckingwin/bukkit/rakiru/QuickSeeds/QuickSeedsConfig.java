// Thanks to Yetanotherx for help getting started
package com.fuckingwin.bukkit.rakiru.QuickSeeds;

import java.io.File;

import org.bukkit.util.config.Configuration;

public class QuickSeedsConfig {

    /**
     * Settings
     */
    public static boolean debugMode = false;
    /**
     * Bukkit config class
     */
    public static Configuration config = null;

    /**
     * Load and parse the YAML config file
     */
    public static void load() {

        File dataDirectory = new File("plugins" + File.separator + "QuickSeeds" + File.separator);

        dataDirectory.mkdirs();

        File file = new File("plugins" + File.separator + "QuickSeeds", "config.yml");

        config = new Configuration(file);
        config.load();

        if (!file.exists()) {
            config.setProperty("debug", debugMode);
            config.save();
        }

        setSettings();

    }

    /**
     * Sets the internal variables
     */
    private static void setSettings() {
        debugMode = config.getBoolean("debug", false);
    }
}

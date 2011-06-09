package com.fuckingwin.bukkit.rakiru.QuickSeeds;

import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import org.bukkit.plugin.Plugin;
import java.util.logging.Logger;
import org.bukkit.util.config.Configuration;

/**
 * QuickSeeds plugin for bukkit
 *
 * @author rakiru
 */
public class QuickSeedsPlugin extends JavaPlugin {

    public static PermissionHandler permissionHandler;
    public boolean usePermissions;
    public static final QuickSeedsLogger log = new QuickSeedsLogger();
    private final QuickSeedsBlockListener blockListener = new QuickSeedsBlockListener(this);

    public void onDisable() {
        // Output to console that plugin is disabled
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " disabled!");
    }

    public void onEnable() {
        // Load config
        QuickSeedsConfig.load();
        
        // Get plugin info from plugin.yml
        PluginDescriptionFile pdfFile = this.getDescription();

        // Setup permissions
        Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");

        if (permissionsPlugin == null) {
            log.info("[" + pdfFile.getName() + "] Permission system not detected, defaulting to all users");
            usePermissions = false;
        } else {
            log.info("[" + pdfFile.getName() + "] Permission system detected");
            usePermissions = true;
            this.permissionHandler = ((Permissions) permissionsPlugin).getHandler();
        }

        // Register commands
        getCommand("quickseeds").setExecutor(new QuickSeedsCommand(this));

        // Register events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.BLOCK_PLACE, blockListener, Priority.Normal, this);

        // Output to console that plugin is enabled
        log.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " enabled!");
    }
}

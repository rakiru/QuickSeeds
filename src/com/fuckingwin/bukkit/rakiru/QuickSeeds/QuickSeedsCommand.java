package com.fuckingwin.bukkit.rakiru.QuickSeeds;

import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

/**
 * Handler for the /quickseeds command.
 * @author rakiru
 */
public class QuickSeedsCommand implements CommandExecutor {

    private final QuickSeedsPlugin plugin;

    public QuickSeedsCommand(QuickSeedsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
        if (plugin.usePermissions) {
            if (sender instanceof Player && !(plugin.permissionHandler.has((Player) sender, "quickseeds.reload"))) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
                return true;
            }
        } else if (!(sender.isOp())) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
            return true;
        }
        QuickSeedsConfig.load();
        sender.sendMessage(ChatColor.GREEN + "QuickSeeds configuration reloaded.");
        return true;
    }
}

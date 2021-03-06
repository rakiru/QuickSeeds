package com.fuckingwin.bukkit.rakiru.QuickSeeds;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.Material;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Listener for the block place event.
 * @author rakiru
 */
public class QuickSeedsBlockListener extends BlockListener {

    private final QuickSeedsPlugin plugin;

    public QuickSeedsBlockListener(final QuickSeedsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (plugin.usePermissions) {
            if (!(plugin.permissionHandler.has(player, "quickseeds.plant"))) {
                return;
            }
        }

        Block block = event.getBlock();
        // If player planted seeds and has more seeds
        if (block.getType() == Material.CROPS && player.getInventory().contains(Material.SEEDS,8)) {
            // stores how many seeds are used and thus need to be removed from the inventory
            int seeds = 1;
            // Loop around the 8 bordering blocks
            for (int x = -1; x < 2; x++) {
                for (int z = -1; z < 2; z++) {
                    if (x != 0 || z != 0) {
                        Block neighbour = block.getRelative(x, 0, z);
                        Block neighbourBelow = neighbour.getRelative(BlockFace.DOWN);
                        if (neighbourBelow.getType() == Material.SOIL && neighbour.getType() == Material.AIR) {
                            if (player.getInventory().contains(Material.SEEDS)) {
                                neighbour.setType(Material.CROPS);
                                seeds++;
                            }
                        }
                    }
                }
            }
            removeItems(player, Material.SEEDS, seeds);
        }
    }

    public void removeItems(Player player, Material material, int amount) {
         plugin.log.debug("Amount to remove: " + Integer.toString(amount));
        int remaining = amount;
        int stackSize;
        int slot;
        while (remaining > 0) {
            slot = player.getInventory().first(material);
            stackSize = player.getInventory().getItem(slot).getAmount();
            plugin.log.debug("Stack size: " + Integer.toString(stackSize));
            if (stackSize <= remaining) {
                player.getInventory().clear(slot);
                remaining-=stackSize;
            } else {
                ItemStack stack = player.getInventory().getItem(slot);
                int newStackSize = stackSize-remaining;
                plugin.log.debug("New stack size: " + Integer.toString(newStackSize));
                stack.setAmount(newStackSize);
                player.getInventory().setItem(slot, stack);
                remaining = 0;
            }
        }
        player.updateInventory();
    }
}

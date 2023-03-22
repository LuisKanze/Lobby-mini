package de.codedex.lobbymini.listener;

import de.codedex.lobbymini.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class Build implements Listener {
    @EventHandler
    public void BlockPlaceEvent(final BlockPlaceEvent e) {
        if (e.getBlock().getType() == Material.SANDSTONE) {
            e.getPlayer().getItemInHand().setAmount(64);
            e.setCancelled(false);
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                public void run() {
                    e.getBlock().setType(Material.AIR);
                }
            }, 60L);
        }
    }

    @EventHandler
    public void on(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (event.getBlock().getType() == Material.SANDSTONE) {
            event.setCancelled(true);
        }

        if (Main.build.contains(player)) {
            event.setCancelled(false);
        } else {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void on(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (Main.build.contains(player)) {
            event.setCancelled(false);
        } else {
            event.setCancelled(true);
        }

    }
}
package de.codedex.lobbymini.listener;

import de.codedex.lobbymini.main.Main;
import de.codedex.lobbymini.utils.Config;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

public class AntiEvents implements Listener {
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        if (event.toWeatherState()) {
            event.setCancelled(true);
        }

    }

    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onTNTPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlockPlaced();
        if (block.getType() == Material.TNT) {
            event.setCancelled(true);
            player.sendMessage(Main.pr + "§cTNT ist auf diesem Server nicht erlaubt.");
        }

    }

    @EventHandler
    public void onTNTInventory(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInHand().getType() == Material.TNT) {
            player.setItemInHand((ItemStack)null);
            player.sendMessage(Main.pr + "§cTNT ist auf diesem Server nicht erlaubt.");
        }

    }

    @EventHandler
    public void onTNTInventoryHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInHand().getType() == Material.TNT) {
            player.setItemInHand((ItemStack)null);
            player.sendMessage(Main.pr + "§cTNT ist auf diesem Server nicht erlaubt.");
        }

    }

    @EventHandler
    public void onTNTPlacedCommand(EntityBlockFormEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.PRIMED_TNT || entity.getType() == EntityType.MINECART_TNT) {
            entity.remove();
            entity.eject();
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onTNTPlacedCommand2(EntityExplodeEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.PRIMED_TNT || entity.getType() == EntityType.MINECART_TNT) {
            entity.remove();
            entity.eject();
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void Food(FoodLevelChangeEvent event) {
        event.setFoodLevel(20);
        event.setCancelled(false);
    }

    @EventHandler
    public void Damage(EntityDamageEvent event) {
        event.setDamage(10.0D);
        event.setCancelled(true);
    }

    @EventHandler
    public void onMobSpawn(EntitySpawnEvent event) {
        Entity ent = event.getEntity();
        event.setCancelled(true);
    }

    @EventHandler
    public void on(PlayerDropItemEvent event) {
        if (Main.build.contains(event.getPlayer())) {
            event.setCancelled(false);
        } else {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void on(PlayerPickupItemEvent event) {
        if (Main.build.contains(event.getPlayer())) {
            event.setCancelled(false);
        } else {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void on(PlayerDeathEvent event) {
        event.setDeathMessage((String)null);
        event.setKeepInventory(true);
    }

    @EventHandler
    public void on(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location loc = Config.getLocation(e.getPlayer(), Main.cfg, "spawn");
        if (p.getLocation().getY() <= 0.0D) {
            p.teleport(loc);
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BURP, 1.0F, 1.0F);
        }

    }
}
package de.codedex.lobbymini.listener;

import de.codedex.lobbymini.main.Main;
import de.codedex.lobbymini.utils.Config;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Joinlistener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        event.setJoinMessage((String)null);
        Location loc = Config.getLocation(event.getPlayer(), Main.cfg, "spawn");
        p.teleport(loc);
        this.giveItems(p);
        p.sendMessage(Main.pr + "JOIN MESSAGE");
    }

    public void giveItems(Player player) {
        ItemStack block = new ItemStack(Material.SANDSTONE);
        ItemMeta blockmeta = block.getItemMeta();
        blockmeta.setDisplayName("§7•§8● §bBLOCKS");
        block.setItemMeta(blockmeta);
        player.getInventory().setItem(4, block);
    }
}
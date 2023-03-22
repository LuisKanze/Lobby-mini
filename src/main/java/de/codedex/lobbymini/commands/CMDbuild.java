package de.codedex.lobbymini.commands;

import de.codedex.lobbymini.listener.Joinlistener;
import de.codedex.lobbymini.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CMDbuild implements CommandExecutor {
    public static Plugin plugin;

    public CMDbuild(Plugin plugin) {
        CMDbuild.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (player.hasPermission("lobbysystem.build")) {
                Joinlistener joinlistener;
                if (args.length == 0) {
                    Location loc = player.getLocation();
                    if (!Main.build.contains(player)) {
                        Main.build.add(player);
                        player.setGameMode(GameMode.CREATIVE);
                        player.getInventory().clear();
                        player.sendMessage(Main.pr + "§7Du kannst nun §bBauen.");
                    } else if (Main.build.contains(player)) {
                        Main.build.remove(player);
                        player.setGameMode(GameMode.SURVIVAL);
                        player.getInventory().clear();
                        player.sendMessage(Main.pr + "§7Du kannst nun nicht mehr §bBauen.");
                        joinlistener = new Joinlistener();
                        joinlistener.giveItems(player);
                    }
                } else if (args.length == 1) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        Player t = Bukkit.getPlayer(args[0]);
                        if (!Main.build.contains(t)) {
                            Main.build.add(t);
                            player.sendMessage(Main.pr + "§7Der Spieler §b" + args[0] + " §7kann nun §bBauen.");
                            t.setGameMode(GameMode.CREATIVE);
                            t.getInventory().clear();
                        } else if (Main.build.contains(t)) {
                            Main.build.remove(t);
                            player.sendMessage(Main.pr + "§7Der Spieler §b" + args[0] + " §7kann nun nicht mehr §bBauen.");
                            t.setGameMode(GameMode.SURVIVAL);
                            t.getInventory().clear();
                            joinlistener = new Joinlistener();
                            joinlistener.giveItems(t);
                        }
                    }
                } else {
                    player.sendMessage(Main.pr + "§cBitte benutze §b/build [Spieler]");
                }
            } else {
                player.sendMessage(Main.noperms);
            }
        }

        return false;
    }
}
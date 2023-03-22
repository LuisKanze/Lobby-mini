package de.codedex.lobbymini.commands;

import de.codedex.lobbymini.main.Main;
import de.codedex.lobbymini.utils.Config;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDspawn implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            Location loc = Config.getLocation(player, Main.cfg, "spawn");
                player.teleport(loc);
                player.sendMessage(Main.pr + "§7Du wurdest zum §bSpawn §7teleportiert.");
        }

        return false;
    }
}
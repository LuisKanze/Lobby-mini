package de.codedex.lobbymini.commands;

import de.codedex.lobbymini.main.Main;
import de.codedex.lobbymini.utils.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDsetspawn implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (player.hasPermission("lobbysystem.setspawn")) {
                Config.setLocation(player, Main.cfg, Main.file, "spawn");
                player.sendMessage(Main.pr + "§7Du hast den §bSpawn §7gesetzt.");
            }
        }

        return false;
    }
}
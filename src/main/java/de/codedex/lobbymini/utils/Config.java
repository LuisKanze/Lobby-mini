package de.codedex.lobbymini.utils;

import java.io.File;
import java.io.IOException;

import de.codedex.lobbymini.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Config {
    public static void setLocation(Player player, YamlConfiguration cfg, File file, String path) {
        double X = player.getLocation().getX();
        double Y = player.getLocation().getY();
        double Z = player.getLocation().getZ();
        float Yaw = player.getLocation().getYaw();
        float Pitch = player.getLocation().getPitch();
        String World = player.getLocation().getWorld().getName();
        cfg.set("lobby." + path + ".X", X);
        cfg.set("lobby." + path + ".Y", Y);
        cfg.set("lobby." + path + ".Z", Z);
        cfg.set("lobby." + path + ".Yaw", Yaw);
        cfg.set("lobby." + path + ".Pitch", Pitch);
        cfg.set("lobby." + path + ".World", World);

        try {
            cfg.save(file);
        } catch (IOException var14) {
            Bukkit.getConsoleSender().sendMessage(Main.pr + "§cDie Config konnte nicht gespeichert werden");
        }

    }

    public static void teleportPlayerToLocation(Player player, YamlConfiguration cfg, String path) {
        double X = cfg.getDouble("lobby." + path + ".X");
        double Y = cfg.getDouble("lobby." + path + ".Y");
        double Z = cfg.getDouble("lobby." + path + ".Z");
        float Yaw = (float)cfg.getDouble("lobby." + path + ".Yaw");
        float Pitch = (float)cfg.getDouble("lobby." + path + ".Pitch");
        World world = Bukkit.getWorld(cfg.getString("lobby." + path + ".World"));
        Location loc = new Location(world, X, Y, Z, Yaw, Pitch);
        player.teleport(loc);
    }

    public static Location getLocation(Player player, YamlConfiguration cfg, String path) {
        double X = cfg.getDouble("lobby." + path + ".X");
        double Y = cfg.getDouble("lobby." + path + ".Y");
        double Z = cfg.getDouble("lobby." + path + ".Z");
        float Yaw = (float)cfg.getDouble("lobby." + path + ".Yaw");
        float Pitch = (float)cfg.getDouble("lobby." + path + ".Pitch");
        World world = Bukkit.getWorld(cfg.getString("lobby." + path + ".World"));
        Location loc = new Location(world, X, Y, Z, Yaw, Pitch);
        return loc;
    }

    public static void getWorld(YamlConfiguration cfg, String path) {
        World world = Bukkit.getWorld(cfg.getString("lobby." + path + ".World"));
        world.setPVP(false);
        world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
    }
}
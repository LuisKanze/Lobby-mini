package de.codedex.lobbymini.main;

import de.codedex.lobbymini.commands.CMDbuild;
import de.codedex.lobbymini.commands.CMDsetspawn;
import de.codedex.lobbymini.commands.CMDspawn;
import de.codedex.lobbymini.listener.AntiEvents;
import de.codedex.lobbymini.listener.Build;
import de.codedex.lobbymini.listener.Joinlistener;
import de.codedex.lobbymini.utils.Config;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main instance;
    public static ArrayList<Player> build = new ArrayList();
    public static String pr = "§bLobby §8§l» §7";
    public static String noperms;
    public static String playernotfound;
    public static File file;
    public static YamlConfiguration cfg;


    public static Main getInstance() {
        return instance;
    }



    public void onEnable() {
        instance = this;

        this.getCommand("setspawn").setExecutor(new CMDsetspawn());
        this.getCommand("build").setExecutor(new CMDbuild(this));
        this.getCommand("spawn").setExecutor(new CMDspawn());
        Bukkit.getPluginManager().registerEvents(new Build(), this);
        Bukkit.getPluginManager().registerEvents(new Joinlistener(), this);
        Bukkit.getPluginManager().registerEvents(new AntiEvents(), this);
    }

    public void onDisable() {
    }

    static {
        noperms = pr + "§cNo Permissions.";
        playernotfound = pr + "§cNo Player found";
        file = new File("plugins/LobbySystem/location.yml");
        cfg = YamlConfiguration.loadConfiguration(file);
    }
}
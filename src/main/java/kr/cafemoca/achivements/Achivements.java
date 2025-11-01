package kr.cafemoca.achivements;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Achivements extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new Listener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

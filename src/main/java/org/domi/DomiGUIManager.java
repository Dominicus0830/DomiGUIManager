package org.domi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.domi.guis.Event;

public class DomiGUIManager extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new Event(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

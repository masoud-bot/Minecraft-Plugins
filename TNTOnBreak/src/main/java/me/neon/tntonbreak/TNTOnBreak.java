package me.neon.tntonbreak;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class TNTOnBreak extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new  BlockBreakListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

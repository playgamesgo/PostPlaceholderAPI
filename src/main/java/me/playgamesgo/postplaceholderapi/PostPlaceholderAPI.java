package me.playgamesgo.postplaceholderapi;

import de.leonhard.storage.Config;
import de.leonhard.storage.internal.settings.ReloadSettings;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class PostPlaceholderAPI extends JavaPlugin {
    PluginLogger logger = new PluginLogger(this);
    static Server server = Bukkit.getServer();
    public static Config config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        config = new Config("config.yml", getDataFolder().toString());
        config.setReloadSettings(ReloadSettings.INTELLIGENT);

        config.setDefault("port", 8000);
        List<String> tokens = new ArrayList<>();
        tokens.add(String.valueOf(UUID.randomUUID()));
        tokens.add(String.valueOf(UUID.randomUUID()));
        config.setDefault("tokens", tokens);

        config.write();

        new HttpManager(logger);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        HttpManager.server.stop(0);
    }
}

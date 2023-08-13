package me.playgamesgo.postplaceholderapi;

import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class PostPlaceholderAPI extends JavaPlugin {
    PluginLogger logger = new PluginLogger(this);
    static Server server = Bukkit.getServer();
    public static YamlDocument config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
            config = YamlDocument.create(new File(getDataFolder(), "config.yml"), getResource("config.yml"),
                    GeneralSettings.DEFAULT, LoaderSettings.builder().setAutoUpdate(true).build(), DumperSettings.DEFAULT, UpdaterSettings.builder().setVersioning(new BasicVersioning("config-version")).build());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        new HttpManager(logger);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        HttpManager.server.stop(1);
    }
}

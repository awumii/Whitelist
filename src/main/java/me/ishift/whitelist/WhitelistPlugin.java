package me.ishift.whitelist;

import me.ishift.whitelist.command.WhitelistCommand;
import me.ishift.whitelist.command.WhitelistTabCompleter;
import me.ishift.whitelist.data.WhitelistData;
import me.ishift.whitelist.listener.PlayerPreLoginListener;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class WhitelistPlugin extends JavaPlugin {
    private WhitelistData data;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.data = new WhitelistData(this.getDataFolder() + "/data.yml");
        this.data.load();

        Bukkit.getPluginManager().registerEvents(new PlayerPreLoginListener(this), this);

        PluginCommand command = this.getCommand("whitelist");
        if (command == null) {
            throw new IllegalStateException("Command is null, have you modified the plugin.yml file?");
        }
        command.setExecutor(new WhitelistCommand(this));
        command.setTabCompleter(new WhitelistTabCompleter(this));
    }

    @Override
    public void onDisable() {
        this.data.save();
    }

    public WhitelistData getData() {
        return this.data;
    }
}

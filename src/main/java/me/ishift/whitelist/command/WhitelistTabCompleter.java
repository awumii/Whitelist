package me.ishift.whitelist.command;

import me.ishift.whitelist.WhitelistPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;

public class WhitelistTabCompleter implements TabCompleter {
    private final WhitelistPlugin plugin;

    public WhitelistTabCompleter(WhitelistPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length < 1) {
            return Arrays.asList("on", "off", "add", "remove", "message", "list");
        }
        if (args[0].equalsIgnoreCase("remove")) {
            return this.plugin.getData().getPlayers();
        }
        return null;
    }
}

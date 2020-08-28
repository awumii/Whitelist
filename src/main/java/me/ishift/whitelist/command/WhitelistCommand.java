package me.ishift.whitelist.command;

import me.ishift.whitelist.WhitelistPlugin;
import me.ishift.whitelist.util.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WhitelistCommand implements CommandExecutor {
    private final WhitelistPlugin plugin;

    public WhitelistCommand(WhitelistPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length < 1) {
            this.sendMessage(sender, "messages.usage");
            return true;
        }

        switch (args[0]) {
            case "on":
                this.sendMessage(sender, "messages.toggle-on");
                this.plugin.getData().setWhitelist(true);
                break;
            case "off":
                this.sendMessage(sender, "messages.toggle-off");
                this.plugin.getData().setWhitelist(false);
                break;
            case "add":
                if (args.length < 2) {
                    this.sendMessage(sender, "messages.player-not-provided");
                    return true;
                }

                String player = args[1];
                this.sendMessage(sender, "messages.player-added", player);
                if (!this.plugin.getData().getPlayers().contains(player)) {
                    this.plugin.getData().getPlayers().add(player);
                }
                break;
            case "remove":
                if (args.length < 2) {
                    this.sendMessage(sender, "messages.player-not-provided");
                    return true;
                }

                player = args[1]; // can't repeat variables in switch
                this.sendMessage(sender, "messages.player-removed", player);
                this.plugin.getData().getPlayers().remove(player);
                break;
            case "message":
                if (args.length < 2) {
                    this.sendMessage(sender, "messages.message-not-provided");
                    return true;
                }

                String message = MessageUtils.buildString(args, 1);
                this.sendMessage(sender, "messages.message-set", message);
                this.plugin.getData().setMessage(message);
                break;
            case "list":
                this.sendMessage(sender, "messages.list", StringUtils.join(this.plugin.getData().getPlayers(), ", "));
                break;
            default:
                this.sendMessage(sender, "messages.usage");
        }
        return true;
    }

    public void sendMessage(CommandSender sender, String key, String... placeholder) {
        sender.sendMessage(String.format(
                MessageUtils.colored(this.plugin.getConfig().getString(key)),
                (Object[]) placeholder));
    }
}

package me.ishift.whitelist.listener;

import me.ishift.whitelist.WhitelistPlugin;
import me.ishift.whitelist.util.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class PlayerPreLoginListener implements Listener {
    private final WhitelistPlugin plugin;

    public PlayerPreLoginListener(WhitelistPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {
        if (this.plugin.getData().isWhitelist()) {
            if (!this.plugin.getData().getPlayers().contains(event.getName())) {
                event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
                event.setKickMessage(MessageUtils.colored(this.plugin.getData().getMessage()));

                if (this.plugin.getConfig().getBoolean("notifications.enabled")) {
                    String message = String.format(
                            this.plugin.getConfig().getString("notifications.message"),
                            event.getName());
                    Bukkit.broadcast(MessageUtils.colored(message), "whitelist.notifications");
                }
            }
        }
    }
}

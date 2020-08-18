package me.ishift.whitelist.util;

import org.bukkit.ChatColor;

public final class MessageUtils {
    public static String colored(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String buildString(String[] args, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < args.length; ++i) {
            sb.append(args[i]).append(" ");
        }
        return sb.toString();
    }

    private MessageUtils() {}
}

package me.ishift.whitelist.data;

import java.util.ArrayList;
import java.util.List;

public class WhitelistData extends DataStorage {
    private List<String> players = new ArrayList<>();
    private boolean whitelist;
    private String message;

    public WhitelistData(String path) {
        super(path);
    }

    @Override
    public void load() {
        this.setDefault("message", "&cYou are not whitelisted.");
        this.setDefault("players", new ArrayList<>());
        this.setDefault("whitelist", false);

        this.players = this.getConfig().getStringList("players");
        this.whitelist = this.getConfig().getBoolean("whitelist");
        this.message = this.getConfig().getString("message");
    }

    @Override
    public void save() {
        this.getConfig().set("players", this.players);
        this.getConfig().set("whitelist", this.whitelist);
        this.getConfig().set("message", this.message);
        this.writeToFile();
    }

    public boolean isWhitelist() {
        return this.whitelist;
    }

    public void setWhitelist(boolean whitelist) {
        this.whitelist = whitelist;
    }

    public List<String> getPlayers() {
        return this.players;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package me.jqrtox.jortooeconomy.data;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.poalib.Economy.Economy;

public class Placeholders extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "jortoo";
    }

    @Override
    public @NotNull String getAuthor() {
        return "jortoo";
    }

    @Override
    public @NotNull String getVersion() {
        return "1";
    }
    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        double money = Economy.getBalance(player);
        return switch (params) {
            case "money" -> money + "";
            default -> "";
        };
    }
}

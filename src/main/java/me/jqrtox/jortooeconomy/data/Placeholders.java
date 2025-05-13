package me.jqrtox.jortooeconomy.data;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.jqrtox.jortooeconomy.JortooEconomy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Placeholders extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "jortoo-eco";
    }

    @Override
    public @NotNull String getAuthor() {
        return "jortoo";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.1";
    }
    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        double money = JortooEconomy.economy.getBalance(player);
        String formattedMoney = String.format("%,.0f", money);
        return switch (params) {
            case "money" -> formattedMoney;
            default -> "";
        };
    }
}

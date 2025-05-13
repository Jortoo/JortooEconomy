package me.jqrtox.jortooeconomy.commands;

import lombok.Getter;
import me.jqrtox.jortooeconomy.JortooEconomy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class PayCommand implements CommandExecutor {

    @Getter
    public static String prefix = "<dark_gray>[<green><bold>ECONOMY</bold><dark_gray>] <gray>▸ <white>";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            return false;
        }
        if (args.length < 2) {
            player.sendRichMessage(prefix + "Correct usage: /pay <player> <amount>");
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);
        double moneyAmount = Integer.parseInt(args[1]);
        String formattedAmount = String.format("%,.0f", moneyAmount);

        if (target == player) {
            player.sendRichMessage(prefix + "You can't send money to yourself");
            return false;
        }

        if (moneyAmount < 100) {
            player.sendRichMessage(prefix + "Minimum has to be <green>100$");
            return false;
        }

        if (JortooEconomy.economy.getBalance(player) < moneyAmount) {
            player.sendRichMessage(prefix + "You do not have enough money");
            return false;
        }

        JortooEconomy.economy.depositPlayer(target, moneyAmount);
        target.sendRichMessage(prefix + "<yellow>" + player.getName() + " <white>has sent you <green>" + formattedAmount + "$");
        player.sendRichMessage(prefix + "You have sent <yellow>" + target.getName() + " <green>" + formattedAmount + "$");
        JortooEconomy.economy.withdrawPlayer(player, moneyAmount);

        return true;
    }
}

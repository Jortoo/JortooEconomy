package me.jqrtox.jortooeconomy.commands;

import me.jqrtox.jortooeconomy.CustomSkullUtil;
import me.jqrtox.jortooeconomy.JortooEconomy;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import poa.poalib.shaded.NBT;

import java.util.ArrayList;
import java.util.List;

public class CreateMoneyBag implements CommandExecutor {

    static FileConfiguration config = JortooEconomy.plugin.getConfig();
    private static final double smallBagMinValue = config.getDouble("moneybags.small");
    private static final double mediumBagMinValue = config.getDouble("moneybags.medium");
    private static final double largeBagMinValue = config.getDouble("moneybags.large");
    private static final double hugeBagMinValue = config.getDouble("moneybags.huge");
    private static final double insaneBagMinValue = config.getDouble("moneybags.insane");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        String prefix = PayCommand.prefix;

        if (!(sender instanceof Player player)) return false;

        if (args.length < 3) {
            player.sendRichMessage(prefix + "Correct usage: /moneybag create <min-amount> <max-amount>");
            return false;
        }

        double minAmount = Integer.parseInt(args[1]);
        double maxAmount = Integer.parseInt(args[2]);

        if (minAmount > maxAmount) {
            player.sendRichMessage(prefix + "Your minimum amount needs to be smaller than the max amount");
            return false;
        }

        if (minAmount < 100) {
            player.sendRichMessage(prefix + "Minimum amount needs to be 100$ or higher");
            return false;
        }

        if (args[0].equals("create")) {

            player.getInventory().addItem(createBag(minAmount, maxAmount));
            player.sendRichMessage(prefix + "You have created a new money bag!");

            return true;
        }

        return true;
    }

    //@Override
    //public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
    //    return switch (args.length) {
    //        case 1 -> EasyTabComplete.correctTabComplete(args[0], "create");
    //        case 2 -> EasyTabComplete.correctTabComplete(args[1], "min-amount");
    //        case 3 -> EasyTabComplete.correctTabComplete(args[2], "max-amount");
    //        default -> "";
    //    };
    //}

    public static ItemStack rtMoneyBag(Double min, Double max) {

        String formattedMin = String.format("%,.0f", min);
        String formattedMax = String.format("%,.0f", max);

        if ( max > insaneBagMinValue ) {
            return CustomSkullUtil.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTM3ZGNmYjY2YTYxYzUyOWYyYTEyOGFlZWY5M2MyNWY5ZDBiMDQzOWI1OWZiMTk5ZDJiMDRhY2ZlMGI4NWFhYyJ9fX0=", "<!italic><#1FFB08><bold>INSANE MONEY BAG</bold> <dark_gray>[<yellow>" + formattedMin + " - " + formattedMax + "<dark_gray>]");
        }
        if ( max > hugeBagMinValue) {
            return CustomSkullUtil.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjhiMWFhZWUzNDYwNmQ2ZDdmNmRhZmQwMmQwMzc5ZDcxNTkxMzE5NGYwMDI1ZTZmZWZlMDM0NDM5YzczNTFlNCJ9fX0=", "<!italic><#1FFB08><bold>HUGE MONEY BAG</bold> <dark_gray>[<yellow>" + formattedMin + " - " + formattedMax + "<dark_gray>]");
        }
        if ( max > largeBagMinValue) {
            return CustomSkullUtil.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGQxMTEwOWY0YWIwM2FhNmM1Yjc2Y2FkMTI5MTc2ZmZiMWZjZThjMTc0ZTY5YzllOGJhMDZiOWY4MDYxZTVhZCJ9fX0=", "<!italic><#1FFB08><bold>LARGE MONEY BAG</bold> <dark_gray>[<yellow>" + formattedMin + " - " + formattedMax + "<dark_gray>]");
        }
        if ( max > mediumBagMinValue ) {
            return CustomSkullUtil.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTQ2N2E3YjlkNzZiYTZkMGZlZDc0MzYwMjUzM2ZjOThjODdhZjBjNjBmODBmMzhkYTc3NGY3YTAxYTIwOTNmYSJ9fX0=", "<!italic><#1FFB08><bold>MEDIUM MONEY BAG</bold> <dark_gray>[<yellow>" + formattedMin + " - " + formattedMax + "<dark_gray>]");
        }
        return CustomSkullUtil.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzMyNGE3ZDYxY2NkNDRiMDMxNzQ0YjUxN2Y5MTFhNWM0NjE2MTRiOTUzYjE3ZjY0ODI4MmUxNDdiMjlkMTBlIn19fQ==", "<!italic><#1FFB08><bold>SMALL MONEY BAG</bold> <dark_gray>[<yellow>" + formattedMin + " - " + formattedMax + "<dark_gray>]");
    }

    public static ItemStack createBag(double min, double max) {

        ItemStack bag = rtMoneyBag(min, max);
        ItemMeta meta = bag.getItemMeta();

        MiniMessage mm = MiniMessage.miniMessage();

        List<Component> lore = new ArrayList<>();
        lore.add(mm.deserialize("<!italic><dark_gray>Voucher"));
        lore.add(mm.deserialize(""));
        lore.add(mm.deserialize(" <!italic><#1FFB08> | <gray>Get a random amount of money"));
        lore.add(mm.deserialize(""));
        lore.add(mm.deserialize("<!italic><dark_gray>(( <gray>Right-Click to redeem <dark_gray>))"));
        meta.lore(lore);

        bag.setItemMeta(meta);

        NBT.modify(bag, nbt -> {
            nbt.setDouble("minamount", min);
            nbt.setDouble("maxamount", max);
        });

        return bag;

    }

}

package me.jqrtox.jortooeconomy.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import poa.poalib.Economy.Economy;
import poa.poalib.shaded.NBT;

import java.util.ArrayList;
import java.util.List;

public class Withdraw implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            return false;
        }
        if (args.length < 1) {
            return false;
        }

        double amount = Integer.parseInt(args[0]);
        String formattedAmount = String.format("%,.0f", amount);

        if (amount < 100) {
            player.sendRichMessage(PayCommand.prefix + "Minimum has to be <green>100$");
            return false;
        }
        if (Economy.getBalance(player) < amount) {
            player.sendRichMessage(PayCommand.prefix + "You do not have enough money");
            return false;
        }
        if (player.getInventory().firstEmpty() == -1) {
            player.sendRichMessage(PayCommand.prefix + "Your inventory is full");
            return false;
        }

        Economy.withdrawPlayer(player, amount);
        player.getInventory().addItem(moneyNote(amount));
        player.sendRichMessage(PayCommand.prefix + "You have withdrawn <green>" + formattedAmount + "$");
        player.playSound(player, Sound.ENTITY_ITEM_PICKUP, 1L, 1L);

        return true;
    }

    public static ItemStack moneyNote(double amount) {

        MiniMessage mm = MiniMessage.miniMessage();
        String formattedAmount = String.format("%,.0f", amount);

        ItemStack note = new ItemStack(Material.PAPER);

        ItemMeta meta = note.getItemMeta();
        meta.addEnchant(Enchantment.MENDING, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.displayName(mm.deserialize("<!italic><#3DFB08><bold>BANKNOTE</bold> <dark_gray>[<yellow>" + formattedAmount + "$<dark_gray>]"));

        List<Component> lore = new ArrayList<>();
        lore.add(mm.deserialize("<!italic><dark_gray>Voucher"));
        lore.add(mm.deserialize(""));
        lore.add(mm.deserialize("<!italic> <dark_gray>» <white>Amount: <green>" + formattedAmount + "$"));
        lore.add(mm.deserialize(""));
        lore.add(mm.deserialize("<!italic><dark_gray>(( <gray>Right-Click to redeem <dark_gray>))"));
        meta.lore(lore);
        note.setItemMeta(meta);

        NBT.modify(note, nbt -> {
            nbt.setDouble("moneyamount", amount);
        });

        return note;
    }

}

package me.jqrtox.jortooeconomy.events;

import me.jqrtox.jortooeconomy.commands.PayCommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import poa.poalib.Economy.Economy;
import poa.poalib.shaded.NBT;

public class BanknoteRedeem implements Listener {

    @EventHandler
    public void onBankNoteRedeem(PlayerInteractEvent event) {

        if (event.useItemInHand() == Event.Result.DENY) {
            return;
        }

        Player player = event.getPlayer();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();

        if (itemInMainHand.getType() != Material.PAPER) {
            return;
        }

        Double tag = NBT.get(itemInMainHand, nbt -> {
            if (!nbt.hasTag("moneyamount")) {
                return null;
            }
            return nbt.getDouble("moneyamount");
        });

        if (tag == null) {
            return;
        }

        String formattedAmount = String.format("%,.0f", tag);
        itemInMainHand.setAmount(itemInMainHand.getAmount() - 1);
        Economy.depositPlayer(player, tag);
        player.playSound(player, Sound.BLOCK_AMETHYST_BLOCK_STEP, 1L, 1L);
        player.sendRichMessage(PayCommand.prefix + "You have redeemed <green>" + formattedAmount + "$");

    }

}

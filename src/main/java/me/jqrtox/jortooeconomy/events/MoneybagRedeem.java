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
import org.bukkit.inventory.ItemStack;
import poa.poalib.Economy.Economy;
import poa.poalib.shaded.NBT;

import java.util.Random;

public class MoneybagRedeem implements Listener {

    @EventHandler
    public void moneyBagRedeem(PlayerInteractEvent event) {

        if (event.useItemInHand() == Event.Result.DENY) return;

        Player player = event.getPlayer();
        ItemStack bag = player.getInventory().getItemInMainHand();

        if (bag.getType() != Material.PLAYER_HEAD) {

        }

        Double minAmount = NBT.get(bag, nbt ->{
            if (!nbt.hasTag("minamount")) {
                return null;
            }
            return nbt.getDouble("minamount");
        });
        Double maxAmount = NBT.get(bag, nbt ->{
            if (!nbt.hasTag("maxamount")) {
                return null;
            }
            return nbt.getDouble("maxamount");
        });

        if (minAmount == null) {
            return;
        }

        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        itemInMainHand.setAmount(itemInMainHand.getAmount() - 1);
        double randomInt = Math.random() * ((maxAmount - minAmount + 1)) + minAmount;
        String formattedRandomInt = String.format("%,.0f", randomInt);
        Economy.depositPlayer(player, randomInt);
        player.playSound(player, Sound.BLOCK_AMETHYST_BLOCK_STEP, 1L, 1L);
        player.sendRichMessage(PayCommand.prefix + "You have redeemed a money bag and recieved: <green>" + formattedRandomInt + "$");

    }

}

package me.jqrtox.jortooeconomy;

import me.jqrtox.jortooeconomy.commands.CreateMoneyBag;
import me.jqrtox.jortooeconomy.commands.PayCommand;
import me.jqrtox.jortooeconomy.commands.Withdraw;
import me.jqrtox.jortooeconomy.data.Placeholders;
import me.jqrtox.jortooeconomy.events.BanknoteRedeem;
import me.jqrtox.jortooeconomy.events.MoneybagRedeem;
import org.bukkit.plugin.java.JavaPlugin;

public final class JortooEconomy extends JavaPlugin {

    public static JortooEconomy plugin;

    @Override
    public void onEnable() {

        plugin = this;

        saveDefaultConfig();

        getCommand("pay").setExecutor(new PayCommand());
        getCommand("withdraw").setExecutor(new Withdraw());
        getCommand("moneybag").setExecutor(new CreateMoneyBag());

        getServer().getPluginManager().registerEvents(new BanknoteRedeem(), this);
        getServer().getPluginManager().registerEvents(new MoneybagRedeem(), this);

        new Placeholders().register();
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

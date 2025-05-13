package me.jqrtox.jortooeconomy;

import me.jqrtox.jortooeconomy.commands.CreateMoneyBag;
import me.jqrtox.jortooeconomy.commands.PayCommand;
import me.jqrtox.jortooeconomy.commands.Withdraw;
import me.jqrtox.jortooeconomy.data.Placeholders;
import me.jqrtox.jortooeconomy.events.BanknoteRedeem;
import me.jqrtox.jortooeconomy.events.MoneybagRedeem;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class JortooEconomy extends JavaPlugin {

    public static JortooEconomy plugin;
    public static Economy economy;
    public static Permission perms;

    @Override
    public void onEnable() {

        plugin = this;
        setupEconomy();
        setupPermissions();
        saveDefaultConfig();

        getCommand("pay").setExecutor(new PayCommand());
        getCommand("withdraw").setExecutor(new Withdraw());
        getCommand("moneybag").setExecutor(new CreateMoneyBag());

        getServer().getPluginManager().registerEvents(new BanknoteRedeem(), this);
        getServer().getPluginManager().registerEvents(new MoneybagRedeem(), this);

        new Placeholders().register();
    }


    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            plugin.getLogger().log(Level.SEVERE, "NO VAULT");
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (rsp == null) {
            plugin.getLogger().log(Level.SEVERE, "NO RSP FOR VAULT");
            return false;
        }
        economy = rsp.getProvider();
        return true;
    }
    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return true;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

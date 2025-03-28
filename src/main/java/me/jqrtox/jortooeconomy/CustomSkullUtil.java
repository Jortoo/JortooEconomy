package me.jqrtox.jortooeconomy;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class CustomSkullUtil {

    public static ItemStack getCustomSkull(String base64, String name) {

        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        if (skullMeta != null) {
            PlayerProfile profile = org.bukkit.Bukkit.createProfile(UUID.randomUUID());
            profile.setProperty(new ProfileProperty("textures", base64));
            skullMeta.setPlayerProfile(profile);
            MiniMessage mm = MiniMessage.miniMessage();
            skullMeta.displayName(mm.deserialize(name));
            skull.setItemMeta(skullMeta);
        }

        return skull;
    }

}

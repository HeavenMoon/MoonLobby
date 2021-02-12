package fr.heavenmoon.lobby.listeners;

import fr.heavenmoon.core.bukkit.mod.PlayerModEvent;
import fr.heavenmoon.core.bukkit.mod.PlayerModToolsEvent;
import fr.heavenmoon.core.common.utils.builders.items.ItemBuilder;
import fr.heavenmoon.lobby.MoonLobby;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerModListener implements Listener
{

    private final MoonLobby plugin;

    public PlayerModListener(MoonLobby plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMod(PlayerModEvent event) {
        if (!event.getCustomPlayer().getModerationData().isEnable())
        {
            event.getPlayer().getInventory().clear();
            event.getPlayer().getInventory().setItem(2, new ItemBuilder().setMaterial(Material.COMPASS)
                                                              .setDisplayName(ChatColor.RED + "Navigation " + ChatColor.DARK_GRAY + "(" + ChatColor.GRAY + "clic droit" + ChatColor.DARK_GRAY + ")").toItemStack());
            event.getPlayer().getInventory().setItem(6, new ItemBuilder().setMaterial(Material.GOLD_INGOT)
                                                              .setDisplayName(ChatColor.GOLD + "Boutique " + ChatColor.DARK_GRAY + "(" + ChatColor.GRAY + "clic droit" + ChatColor.DARK_GRAY + ")").toItemStack());
    
        }
    }
    
    @EventHandler
    public void onMod(PlayerModToolsEvent event) {
        if (!event.getCustomPlayer().getModerationData().isTools())
        {
            event.getPlayer().getInventory().clear();
            event.getPlayer().getInventory().setItem(2, new ItemBuilder().setMaterial(Material.COMPASS)
                                                              .setDisplayName(
                                                                      ChatColor.RED + "Navigation " + ChatColor.DARK_GRAY + "(" + ChatColor.GRAY + "clic droit" + ChatColor.DARK_GRAY + ")").toItemStack());
            event.getPlayer().getInventory().setItem(6, new ItemBuilder().setMaterial(Material.GOLD_INGOT)
                                                              .setDisplayName(ChatColor.GOLD + "Boutique " + ChatColor.DARK_GRAY + "(" + ChatColor.GRAY + "clic droit" + ChatColor.DARK_GRAY + ")").toItemStack());
    
        }
    }
}
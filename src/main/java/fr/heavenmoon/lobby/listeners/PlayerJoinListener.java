package fr.heavenmoon.lobby.listeners;

import fr.heavenmoon.core.bukkit.MoonBukkitCore;
import fr.heavenmoon.core.common.utils.builders.items.ItemBuilder;
import fr.heavenmoon.lobby.MoonLobby;
import fr.heavenmoon.persistanceapi.customs.player.CustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final MoonBukkitCore core;
    private MoonLobby plugin;

    public PlayerJoinListener(MoonBukkitCore core, MoonLobby plugin) {
        this.core = core;
        this.plugin = plugin;
    }

    @EventHandler
    public void on(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setMaxHealth(2.00F);
        player.setHealth(2.00F);
        player.setExhaustion(20.0F);
        player.setFoodLevel(20);
        player.setExp(0.0F);
        player.setLevel(0);

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            Location spawn = new Location(Bukkit.getWorld("lobby"), 283.5, 74, -216.5, 0.0F, 0.0F);
            event.getPlayer().teleport(spawn);
        }, 1L);

        CustomPlayer customPlayer = plugin.getPersistanceManager().getPlayerManager().getCustomPlayer(player.getUniqueId());
        if (customPlayer.getModerationData().isEnable()) return;

        player.getInventory().clear();
        player.getInventory().setItem(2, new ItemBuilder().setMaterial(Material.COMPASS)
                .setDisplayName(ChatColor.RED + "Navigation " + ChatColor.DARK_GRAY + "(" + ChatColor.GRAY + "clic droit" + ChatColor.DARK_GRAY + ")").toItemStack());
        player.getInventory().setItem(6, new ItemBuilder().setMaterial(Material.GOLD_INGOT)
                .setDisplayName(ChatColor.GOLD + "Boutique " + ChatColor.DARK_GRAY + "(" + ChatColor.GRAY + "clic droit" + ChatColor.DARK_GRAY + ")").toItemStack());

    }
}

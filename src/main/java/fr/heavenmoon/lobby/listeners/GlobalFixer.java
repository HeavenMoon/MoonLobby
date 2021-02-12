package fr.heavenmoon.lobby.listeners;

import fr.heavenmoon.core.bukkit.MoonBukkitCore;
import fr.heavenmoon.lobby.MoonLobby;
import fr.heavenmoon.persistanceapi.customs.player.CustomPlayer;
import fr.heavenmoon.persistanceapi.customs.player.data.RankList;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class GlobalFixer implements Listener {

    private final MoonLobby plugin;

    public GlobalFixer(MoonLobby plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void on(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void on(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void on(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void on(EntitySpawnEvent event) {
        if (event.getEntity() instanceof Monster || event.getEntity() instanceof Animals) event.setCancelled(true);
    }

    @EventHandler
    public void on(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void on(PlayerDeathEvent event) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> event.getEntity().spigot().respawn(), 1L);
    }

    @EventHandler
    public void on(BlockBreakEvent event) {
        Player player = event.getPlayer();
        CustomPlayer customPlayer = plugin.getPersistanceManager().getPlayerManager().getCustomPlayer(player.getUniqueId());
        if (customPlayer.getModerationData().isBypass()) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void on(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        CustomPlayer customPlayer = plugin.getPersistanceManager().getPlayerManager().getCustomPlayer(player.getUniqueId());
        if (customPlayer.getModerationData().isBypass()) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void on(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getLocation().getY() <= 43) {
            CustomPlayer customPlayer = plugin.getPersistanceManager().getPlayerManager().getCustomPlayer(player.getUniqueId());
            if (customPlayer.getModerationData().isBypass()) return;
            Location spawn = new Location(Bukkit.getWorld("lobby"), 283.5, 74, -216.5, 0.0F, 0.0F);
            event.getPlayer().teleport(spawn);
        }
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getView().getPlayer();
        CustomPlayer customPlayer = plugin.getPersistanceManager().getPlayerManager().getCustomPlayer(player.getUniqueId());
        if (customPlayer.getModerationData().isBypass() && (!event.getView().getTitle().equals("Navigation") || !event.getView().getTitle().equals("Boutique")))
            return;
        event.setCancelled(true);
    }

    @EventHandler
    public void on(EntityChangeBlockEvent event) {
        event.setCancelled(true);
    }

}

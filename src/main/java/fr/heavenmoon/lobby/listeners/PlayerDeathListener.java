package fr.heavenmoon.lobby.listeners;

import fr.heavenmoon.lobby.MoonLobby;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    private final MoonLobby moonLobby;

    public PlayerDeathListener(MoonLobby moonLobby) {
        this.moonLobby = moonLobby;
    }

    @EventHandler
    public void on(PlayerDeathEvent event) {
        event.getEntity().spigot().respawn();
        event.setKeepInventory(true);
    }
}

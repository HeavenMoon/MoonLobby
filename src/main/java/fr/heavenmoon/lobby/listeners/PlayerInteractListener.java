package fr.heavenmoon.lobby.listeners;

import fr.heavenmoon.core.bukkit.MoonBukkitCore;
import fr.heavenmoon.lobby.MoonLobby;
import fr.heavenmoon.lobby.gui.NavigationGui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener
{
	
	private MoonLobby plugin;
	
	public PlayerInteractListener(MoonLobby plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void on(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		if (player.getInventory().getItemInHand() == null || player.getInventory().getItemInHand().getType() == Material.AIR)
			return;
		if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) return;
		if (event.getAction() == Action.RIGHT_CLICK_AIR || (event.getAction() == Action.RIGHT_CLICK_BLOCK))
		{
			if (player.getInventory().getItemInHand().getType() == Material.COMPASS)
			{
                plugin.getCore().getGuiManager().openGui(player, new NavigationGui(MoonBukkitCore.get()));
			}
			if (player.getInventory().getItemInHand().getType() == Material.GOLD_INGOT)
			{
			
			}
		}
	}
}

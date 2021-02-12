package fr.heavenmoon.lobby.gui;


import fr.heavenmoon.core.bukkit.MoonBukkitCore;
import fr.heavenmoon.core.bukkit.gui.AbstractGui;
import fr.heavenmoon.core.common.utils.builders.items.ItemBuilder;
import fr.heavenmoon.persistanceapi.customs.redis.RedisPublisher;
import fr.heavenmoon.persistanceapi.customs.redis.RedisTarget;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class NavigationGui extends AbstractGui
{

    public NavigationGui(MoonBukkitCore plugin) {
        super(plugin);
    }
    
	@Override
    public void display(Player player) {
        this.inventory = this.plugin.getServer().createInventory(null, 9 * 3, "Navigation");

        int[] slotsDeco = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
        for (int i : slotsDeco)
            this.setSlotData(ChatColor.BOLD + "Deco", new ItemBuilder().setMaterial(Material.STAINED_GLASS_PANE).setData((byte) new Random().nextInt(8)).toItemStack(), i, null, null);

        this.setSlotData(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Faction", new ItemBuilder(Material.DIAMOND_SWORD).toItemStack(), 13, null, "ConnectFaction");

        this.plugin.getServer().getScheduler().runTask(this.plugin, () -> player.openInventory(this.inventory));
    }

    @Override
    public void onClick(Player player, ItemStack stack, String action, ClickType clickType) {
        if (action.equalsIgnoreCase("close")) {
            this.plugin.getGuiManager().closeGui(player);
        }
        if (action.equalsIgnoreCase("ConnectFaction")) {
            new RedisPublisher(plugin.getCommons().getPersistanceManager(), "Connect").setArguments(player.getName(), "faction1").publish(new RedisTarget(RedisTarget.RedisTargetType.PROXY));
        }
    }
}

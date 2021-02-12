package fr.heavenmoon.lobby;

import fr.heavenmoon.lobby.listeners.*;
import fr.heavenmoon.core.bukkit.MoonBukkitCore;
import fr.heavenmoon.lobby.commands.SpawnCommand;
import fr.heavenmoon.lobby.utils.DCommand;
import org.bukkit.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.Collections;

public class Action {

    private MoonLobby plugin;

    public Action(MoonLobby plugin) {
        this.plugin = plugin;
    
        World world = Bukkit.createWorld(new WorldCreator("lobby"));
        world.setTime(6500);
        world.setGameRuleValue("doDaylightCycle", "false");
        world.setGameRuleValue("doFireTick", "false");
        world.setStorm(false);
        
        world.getWorldBorder().setCenter(new Location(world, 283,73,-201));
        world.getWorldBorder().setSize(100);

        registerEvents();
        loadCommands();
        registerCraft();
    }

    private void register(Listener listener) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

    public void registerEvents() {
        register(new GlobalFixer(plugin));
        register(new PlayerJoinListener(MoonBukkitCore.get(), plugin));
        register(new PlayerInteractListener(plugin));
        register(new PlayerDeathListener(plugin));
        register(new PlayerModListener(plugin));
    }

    public void loadCommands() {
        new DCommand("spawn", "/spawn", "teleport to spawn", null, Collections.singletonList(""), new SpawnCommand(plugin), plugin);
    }

    public void registerCraft() {
        ItemStack exmpleItem = new ItemStack(Material.LEATHER_BOOTS);

        ShapedRecipe exmpleItemShape = new ShapedRecipe(exmpleItem);
        exmpleItemShape.shape("LDL","L L");
        
    }

}

package fr.heavenmoon.lobby.commands;

import fr.heavenmoon.lobby.MoonLobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private MoonLobby plugin;

    public SpawnCommand(MoonLobby plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location spawn = new Location(Bukkit.getWorld("lobby"), 283.5, 74, -216.5, 0.0F, 0.0F);
            player.teleport(spawn);
        }
        return false;
    }
}

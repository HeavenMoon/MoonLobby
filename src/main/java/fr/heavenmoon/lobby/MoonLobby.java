package fr.heavenmoon.lobby;

import fr.heavenmoon.core.bukkit.MoonBukkitCore;
import fr.heavenmoon.persistanceapi.PersistanceManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MoonLobby extends JavaPlugin {
    
    private static MoonLobby instance;
    private MoonBukkitCore core;
    private PersistanceManager persistanceManager;

    @Override
    public void onEnable() {
        instance = this;
        this.core = MoonBukkitCore.get();
        this.persistanceManager = new PersistanceManager(core.getCommons().getConfig().getServerName(),
                core.getCommons().getDatabaseConfig(), core.getCommons().getRedisConfig());
        
        new Action(this);

        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
    
    public static MoonLobby get()
    {
        return instance;
    }
    
    public MoonBukkitCore getCore()
    {
        return core;
    }
    
    public PersistanceManager getPersistanceManager()
    {
        return persistanceManager;
    }
}

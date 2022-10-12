package space.commandf1.fasterlib.api.listener;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import space.commandf1.fasterlib.api.plugin.IPlugin;

@SuppressWarnings("unused")
public final class ListenerManager {
    private ListenerManager() {}

    /**
     * 注册单个Listener
     *
     * */
    public static void registerListener(Listener listener, Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

    /**
     * 注册单个Listener
     *
     * */
    public static void registerListener(Listener listener, IPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin.getPlugin());
    }

    /**
     * 注册多个Listener
     *
     * */
    public static void registerListeners(Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    /**
     * 注册多个Listener
     *
     * */
    public static void registerListeners(IPlugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            plugin.getServer().getPluginManager().registerEvents(listener, plugin.getPlugin());
        }
    }
}

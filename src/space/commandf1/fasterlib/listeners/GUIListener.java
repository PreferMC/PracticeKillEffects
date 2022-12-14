package space.commandf1.fasterlib.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import space.commandf1.fasterlib.api.inventory.action.ActionType;
import space.commandf1.fasterlib.api.inventory.action.GUIAction;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class GUIListener implements Listener {
    public static Map<GUIAction, Inventory> actions = new HashMap<>();

    @EventHandler
    public void onGUIClick(InventoryClickEvent event) {
        if (actions.size() <= 0 || event.getCurrentItem() == null) {
            return;
        }
        for (GUIAction action : actions.keySet()) {
            if (!(event.getInventory().getTitle().equals(actions.get(action).getTitle()))) {
                continue;
            }
            if (action.getActionType().equals(ActionType.NUM)) {
                if (event.getRawSlot() == action.getI() && action.getClickType() == null) {
                    action.getAction().action(event);
                } else if (event.getRawSlot() == action.getI() && action.getClickType().equals(event.getClick())) {
                    action.getAction().action(event);
                }
            } else if (action.getActionType().equals(ActionType.ITEM)) {
                if (event.getCurrentItem().equals(action.getItemStack()) && action.getClickType() == null) {
                    action.getAction().action(event);
                } else if (event.getCurrentItem().equals(action.getItemStack()) && action.getClickType().equals(event.getClick())) {
                    action.getAction().action(event);
                }
            }
        }
    }

    /**
     * 删除所有Action
     *
     * */
    public static void clearActions() {
        actions.clear();
    }

    /**
     * 序列化所有Action
     *
     * */
    public static void saveActions(File path) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(path.toPath()));
        for (GUIAction action : actions.keySet()) {
            objectOutputStream.writeObject(action);
        }
        objectOutputStream.close();
    }
}

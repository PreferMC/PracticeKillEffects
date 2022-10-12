package cc.prefermc.killeffects.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import cc.prefermc.killeffects.playerdata.PlayerDataManager;

import static cc.prefermc.killeffects.utils.ConfigUtils.Types.*;

@Deprecated
public class PotionTask extends BukkitRunnable {
    @Override
    public void run() {
        if (!AUTO_DROP_EMPTY_BOTTLE_ENABLE) {
            return;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!PlayerDataManager.getPlayerData(player).isAutoDropEmptyItem()) {
                continue;
            }
            Inventory inventory = player.getInventory();
            ItemStack[] itemStacks = inventory.getContents();
            for (int i = 0; i < itemStacks.length; i++) {
                if (itemStacks[i] == null) {
                    continue;
                }
                if (itemStacks[i].getType().equals(Material.GLASS_BOTTLE)) {
                    inventory.clear(i);
                }
            }
        }
    }
}

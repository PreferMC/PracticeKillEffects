package cc.prefermc.killeffects.listeners;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import space.commandf1.fasterlib.api.player.IPlayer;
import cc.prefermc.killeffects.PracticeKillEffects;
import cc.prefermc.killeffects.playerdata.PlayerData;
import cc.prefermc.killeffects.playerdata.PlayerDataManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static cc.prefermc.killeffects.utils.ConfigUtils.Types.*;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            PlayerDataManager.writePlayerData(new PlayerData(player.getName(), false, false, false, false, false, false));
        }
    }

    @EventHandler
    public void onPlayerItemConsumeToAutoDropEmptyBottle(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        if (!AUTO_DROP_EMPTY_BOTTLE_ENABLE || !PlayerDataManager.getPlayerData(player).isAutoDropEmptyItem()) {
            return;
        }
        Inventory inventory = player.getInventory();
        new BukkitRunnable() {

            @Override
            public void run() {
                HashMap<Integer, ? extends ItemStack> bottles = inventory.all(Material.GLASS_BOTTLE);
                for (Integer integer : bottles.keySet()) {
                    inventory.clear(integer);
                }
            }
        }.runTaskLater(PracticeKillEffects.getInstance(), 1);
    }

    @EventHandler
    public void onPlayerDeathToKillSlowly(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        if (!KILL_SLOWLY_ENABLE || killer == null || !PlayerDataManager.getPlayerData(killer).isKillSlowly()) {
            return;
        }
        IPlayer player = IPlayer.toIPlayer(event.getEntity());
        if (player.getKiller() == null) {
            return;
        }
        IPlayer damage = IPlayer.toIPlayer(player.getKiller());
        int time = KILL_SLOWLY_CONTINUE_TIME;
        damage.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, time, 255));
        damage.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, time, 100));
        damage.setHealth(damage.getMaxHealth());
    }

    @EventHandler
    public void onPlayerDeathToL(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        if (!AUTO_L_ENABLE || killer == null || !PlayerDataManager.getPlayerData(killer).isAutoL()) {
            return;
        }
        IPlayer player = IPlayer.toIPlayer(event.getEntity());
        if (event.getEntity() == null) {
            return;
        }
        IPlayer damage = IPlayer.toIPlayer(player.getKiller());
        damage.chat(AUTO_L_MESSAGE.replaceAll("%player%", damage.getName()).replaceAll("%damage%", player.getName()));
    }

    @EventHandler
    public void onPlayerDeathToNoDeathMessage(PlayerDeathEvent event) {
        if (SETTINGS_NO_DEATH_MESSAGE) {
            event.setDeathMessage("");
        }
    }

    @EventHandler
    public void onPlayerKickToNoBadKicks(PlayerKickEvent event) {
        if (!SETTINGS_NO_BAD_KICK) {
            return;
        }
        String reason = event.getReason();
        if (reason.equalsIgnoreCase("Kicked for spamming") || reason.equalsIgnoreCase("disconnect.spam")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDeathToTypeDrop(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        if (!TYPE_ITEM_DROP_ENABLE || killer == null || !PlayerDataManager.getPlayerData(killer).isTypeItemDrop()) {
            return;
        }
        if (event.getDrops() == null || event.getDrops().size() <= 0) {
            return;
        }
        List<Material> list = this.stringListToMaterial(TYPE_ITEM_DROP_ITEMS);
        for (ItemStack drop : event.getDrops()) {
            if (list.contains(drop.getType())) {
                continue;
            }
            drop.setType(Material.AIR);
        }
        event.setDroppedExp(0);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onPlayerDamageToBlood(EntityDamageByEntityEvent event) {
        if (!event.getDamager().getType().equals(EntityType.PLAYER)) {
            return;
        }
        if (!SPAWN_BLOOD_ON_DAMAGE_ENABLE || !PlayerDataManager.getPlayerData((Player) event.getDamager()).isSpawnBloodOnDamage()) {
            return;
        }
        if (!event.getEntityType().equals(EntityType.PLAYER) && SPAWN_BLOOD_ON_DAMAGE_PLAYER_ONLY) {
            return;
        }
        World world = event.getEntity().getWorld();
        Location location = event.getEntity().getLocation();
        location.setY(location.getY() + 1D);
        location.setPitch(event.getDamager().getLocation().getPitch());
        location.setYaw(event.getDamager().getLocation().getYaw());
        for (int i = 0; i < SPAWN_BLOOD_ON_DAMAGE_SIZE; i++) {
            world.playEffect(location, Effect.STEP_SOUND, 152, 5);
        }
    }

    @EventHandler
    public void onPlayerDeathToSuperSkill(PlayerDeathEvent event) {
        if (!SKULL_BLOOD_SPAWN_ENABLE) {
            return;
        }
        Player killer = event.getEntity().getKiller();
        if (!PlayerDataManager.getPlayerData(killer).isSkullBloodSpawn()) {
            return;
        }
        Player player = event.getEntity();
        Location location = player.getLocation();
        location.setY(location.getY() + 1D);
        Block block = location.getBlock();
        block.setType(Material.SKULL);
        new BukkitRunnable() {
            private int times = 10;
            @Override
            public void run() {
                if (times <= 0) {
                    block.setType(Material.AIR);
                    this.cancel();
                    return;
                }
                for (int i = 0; i < 5; i++) {
                    location.getWorld().playEffect(location, Effect.STEP_SOUND, 152, 5);
                }
                times --;
            }
        }.runTaskTimer(PracticeKillEffects.getInstance(), 0, 5);
    }

    public List<Material> stringListToMaterial(List<String> list) {
        List<Material> materials = new ArrayList<>();
        for (String s : list) {
            materials.add(Material.getMaterial(s.toUpperCase()));
        }
        return materials;
    }
}

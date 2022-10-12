package cc.prefermc.killeffects.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import space.commandf1.fasterlib.api.command.Command;
import space.commandf1.fasterlib.api.inventory.GUI;
import space.commandf1.fasterlib.api.inventory.action.GUIAction;
import space.commandf1.fasterlib.api.item.IItemStack;
import space.commandf1.fasterlib.api.player.IPlayer;
import cc.prefermc.killeffects.playerdata.PlayerData;
import cc.prefermc.killeffects.playerdata.PlayerDataManager;

import java.util.ArrayList;
import java.util.List;

import static cc.prefermc.killeffects.utils.MessageUtils.Types.*;
import static cc.prefermc.killeffects.utils.ConfigUtils.Types.*;
import static cc.prefermc.killeffects.utils.MessageUtils.UI.*;

public class EffectSettingsCommand extends Command {
    private static final List<String> registeredPlayers = new ArrayList<>();

    public EffectSettingsCommand() {
        super("settings", "PlayerSettings Command", false, null, null, true, NOT_A_PLAYER_MESSAGE);
    }

    @Override
    public boolean onExecute(CommandSender commandSender, org.bukkit.command.Command cmd, String label, String[] args) {
        IPlayer player = IPlayer.toIPlayer(commandSender);
        GUI gui = new GUI(6 * 9, player.getName());
        PlayerData playerData = PlayerDataManager.isPlayerDataExists(player.getPlayer())
                ? PlayerDataManager.getPlayerData(player.getPlayer()) :
                new PlayerData(player.getPlayer().getName(), false, false,
                        false, false, false, false);
        IItemStack glass = new IItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15).setItemName("§c").setGlow(true);
        gui.fullItem(glass.getItemStack());
        gui.addListener(new GUIAction(event -> event.setCancelled(true), glass.getItemStack()));
        IItemStack watch = new IItemStack(KILL_SLOWLY_ENABLE ? Material.WATCH : Material.BARRIER, 1, (short) 0).setItemName("§e§l" + KILL_SLOWLY + ": " + getBooleanString(playerData.isKillSlowly())).setItemLore(KILL_SLOWLY_LORE).setGlow(true);
        // 19
        gui.setItem(19, watch.getItemStack());


        IItemStack potion = new IItemStack(AUTO_DROP_EMPTY_BOTTLE_ENABLE ? Material.POTION : Material.BARRIER, 1, (short) 0).setItemName("§e§l" + AUTO_DROP_EMPTY_BOTTLE + ": " + getBooleanString(playerData.isAutoDropEmptyItem())).setItemLore(KILL_SLOWLY_LORE).setGlow(true);
        // 22
        gui.setItem(22, potion.getItemStack());

        IItemStack stick = new IItemStack(AUTO_L_ENABLE ? Material.STICK : Material.BARRIER, 1, (short) 0).setItemName("§e§l" + AUTO_L + ": " + getBooleanString(playerData.isAutoL())).setItemLore(AUTO_L_LORE).setGlow(true);
        // 25
        gui.setItem(25, stick.getItemStack());

        IItemStack skull = new IItemStack(SKULL_BLOOD_SPAWN_ENABLE ? Material.SKULL_ITEM : Material.BARRIER, 1, (short) 0).setItemName("§e§l" + SKULL_BLOOD_SPAWN + ": " + getBooleanString(playerData.isSkullBloodSpawn())).setItemLore(SPAWN_BLOOD_ON_DAMAGE_LORE).setGlow(true);
        // 37
        gui.setItem(37, skull.getItemStack());

        IItemStack gold = new IItemStack(TYPE_ITEM_DROP_ENABLE ? Material.GOLD_INGOT : Material.BARRIER, 1, (short) 0).setItemName("§e§l" + TYPE_ITEM_DROP + ": " + getBooleanString(playerData.isTypeItemDrop())).setItemLore(TYPE_ITEM_DROP_LORE).setGlow(true);
        // 40
        gui.setItem(40, gold.getItemStack());

        IItemStack redstone = new IItemStack(SPAWN_BLOOD_ON_DAMAGE_ENABLE ? Material.REDSTONE : Material.BARRIER, 1, (short) 0).setItemName("§e§l" + SPAWN_BLOOD_ON_DAMAGE + ": " + getBooleanString(playerData.isSpawnBloodOnDamage())).setItemLore(SPAWN_BLOOD_ON_DAMAGE_LORE).setGlow(true);
        // 43
        gui.setItem(43, redstone.getItemStack());

        if (!registeredPlayers.contains(player.getName())) {

            gui.addListener(new GUIAction(event -> {
                if (!KILL_SLOWLY_ENABLE) {
                    event.getWhoClicked().closeInventory();
                    event.getWhoClicked().sendMessage(DISABLED_EFFECT_USE);
                    event.setCancelled(true);
                    return;
                }
                playerData.setKillSlowly(!event.getCurrentItem().getItemMeta().getDisplayName().contains(TRUE));
                PlayerDataManager.writePlayerData(playerData);
                event.getWhoClicked().sendMessage(CHOOSE_MESSAGE.replaceAll("%choice%", getBooleanString(playerData.isKillSlowly())));
                event.getWhoClicked().closeInventory();
                event.setCancelled(true);
            }, 19));
            gui.addListener(new GUIAction(event -> {
                if (!AUTO_DROP_EMPTY_BOTTLE_ENABLE) {
                    event.getWhoClicked().closeInventory();
                    event.getWhoClicked().sendMessage(DISABLED_EFFECT_USE);
                    event.setCancelled(true);
                    return;
                }
                playerData.setAutoDropEmptyItem(!event.getCurrentItem().getItemMeta().getDisplayName().contains(TRUE));
                PlayerDataManager.writePlayerData(playerData);
                event.getWhoClicked().sendMessage(CHOOSE_MESSAGE.replaceAll("%choice%", getBooleanString(playerData.isAutoDropEmptyItem())));
                event.getWhoClicked().closeInventory();
                event.setCancelled(true);
            }, 22));
            gui.addListener(new GUIAction(event -> {
                if (!AUTO_L_ENABLE) {
                    event.getWhoClicked().closeInventory();
                    event.getWhoClicked().sendMessage(DISABLED_EFFECT_USE);
                    event.setCancelled(true);
                    return;
                }
                playerData.setAutoL(!event.getCurrentItem().getItemMeta().getDisplayName().contains(TRUE));
                PlayerDataManager.writePlayerData(playerData);
                event.getWhoClicked().sendMessage(CHOOSE_MESSAGE.replaceAll("%choice%", getBooleanString(playerData.isAutoL())));
                event.getWhoClicked().closeInventory();
                event.setCancelled(true);
            }, 25));

            gui.addListener(new GUIAction(event -> {
                if (!SKULL_BLOOD_SPAWN_ENABLE) {
                    event.getWhoClicked().closeInventory();
                    event.getWhoClicked().sendMessage(DISABLED_EFFECT_USE);
                    event.setCancelled(true);
                    return;
                }
                playerData.setSkullBloodSpawn(!event.getCurrentItem().getItemMeta().getDisplayName().contains(TRUE));
                PlayerDataManager.writePlayerData(playerData);
                event.getWhoClicked().sendMessage(CHOOSE_MESSAGE.replaceAll("%choice%", getBooleanString(playerData.isSkullBloodSpawn())));
                event.getWhoClicked().closeInventory();
                event.setCancelled(true);
            }, 37));

            gui.addListener(new GUIAction(event -> {
                if (!TYPE_ITEM_DROP_ENABLE) {
                    event.getWhoClicked().closeInventory();
                    event.getWhoClicked().sendMessage(DISABLED_EFFECT_USE);
                    event.setCancelled(true);
                    return;
                }
                playerData.setTypeItemDrop(!event.getCurrentItem().getItemMeta().getDisplayName().contains(TRUE));
                PlayerDataManager.writePlayerData(playerData);
                event.getWhoClicked().sendMessage(CHOOSE_MESSAGE.replaceAll("%choice%", getBooleanString(playerData.isTypeItemDrop())));
                event.getWhoClicked().closeInventory();
                event.setCancelled(true);
            }, 40));

            gui.addListener(new GUIAction(event -> {
                if (!SPAWN_BLOOD_ON_DAMAGE_ENABLE) {
                    event.getWhoClicked().closeInventory();
                    event.getWhoClicked().sendMessage(DISABLED_EFFECT_USE);
                    event.setCancelled(true);
                    return;
                }
                playerData.setSpawnBloodOnDamage(!event.getCurrentItem().getItemMeta().getDisplayName().contains(TRUE));
                PlayerDataManager.writePlayerData(playerData);
                event.getWhoClicked().sendMessage(CHOOSE_MESSAGE.replaceAll("%choice%", getBooleanString(playerData.isSpawnBloodOnDamage())));
                event.getWhoClicked().closeInventory();
                event.setCancelled(true);
            }, 43));
            registeredPlayers.add(player.getName());
        }
        player.openInventory(gui.getInventory());
        return true;
    }

    public static String getBooleanString(boolean b) {
        return b ? TRUE : FALSE;
    }

}

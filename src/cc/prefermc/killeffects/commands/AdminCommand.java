package cc.prefermc.killeffects.commands;

import cc.prefermc.killeffects.utils.ConfigUtils;
import cc.prefermc.killeffects.utils.MessageUtils;
import cc.prefermc.killeffects.utils.PermissionUtils;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import space.commandf1.fasterlib.api.command.Command;
import space.commandf1.fasterlib.api.inventory.GUI;
import space.commandf1.fasterlib.api.inventory.action.GUIAction;
import space.commandf1.fasterlib.api.item.IItemStack;
import space.commandf1.fasterlib.api.player.IPlayer;

public class AdminCommand extends Command {
    private static final GUI gui;

    public AdminCommand() {
        super("manageEffects", "Effect Manager", true, PermissionUtils.Types.ADMIN_COMMAND_PERMISSION, MessageUtils.Types.NO_PERMISSION_MESSAGE, false, null);
    }

    @Override
    public boolean onExecute(CommandSender commandSender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (commandSender instanceof Player) {
            IPlayer player = IPlayer.toIPlayer(commandSender);
            player.openInventory(gui.getInventory());
            return true;
        }
        if (args.length <= 0) {
            commandSender.sendMessage(new String[] {
                    "§7=======================",
                    "§a/manageEffects reload - reload configs",
                    "§7======================="
            });
            return true;
        }
        if ("reload".equalsIgnoreCase(args[0])) {
            ConfigUtils.reloadConfigs();
            commandSender.sendMessage(new String[]{
                    "§7=======================",
                    "§aConfigs has been reloaded!",
                    "§e",
                    "§eMade By commandf1",
                    "§7======================="
            });
        } else {
            commandSender.sendMessage(MessageUtils.Types.NO_SUB_COMMAND_MESSAGE);
        }
        return true;
    }

    static {
        gui = new GUI(3 * 9, "§cAdmin Manage Table");
        IItemStack glass = new IItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15).setItemName("§c");
        IItemStack watch = new IItemStack(Material.WATCH, 1, (short) 0).setItemName("§a§lReload Configs");
        gui.fullItem(glass);
        gui.addListener(new GUIAction(event -> event.setCancelled(true), glass.getItemStack()));
        gui.setItem(13, watch);
        gui.addListener(new GUIAction(event -> {
            ConfigUtils.reloadConfigs();
            HumanEntity player = event.getWhoClicked();
            player.closeInventory();
            player.sendMessage(new String[] {
                    "§7=======================",
                    "§aConfigs has been reloaded!",
                    "§e",
                    "§eMade By commandf1",
                    "§7======================="
            });
            event.setCancelled(true);
        }, watch.getItemStack()));
    }
}

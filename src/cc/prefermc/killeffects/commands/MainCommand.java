package cc.prefermc.killeffects.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.commandf1.fasterlib.api.command.Command;
import space.commandf1.fasterlib.api.inventory.GUI;
import space.commandf1.fasterlib.api.inventory.action.GUIAction;
import space.commandf1.fasterlib.api.item.IItemStack;
import cc.prefermc.killeffects.PracticeKillEffects;

import static cc.prefermc.killeffects.utils.MessageUtils.Types.*;

public class MainCommand extends Command {
    private static final GUI gui;

    public MainCommand() {
        super("PracticeKillEffects", "Main command", false, null, null, true, NOT_A_PLAYER_MESSAGE);
    }

    @Override
    public boolean onExecute(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        player.openInventory(gui.getInventory());

        return true;
    }

    static {
        gui = new GUI(3 * 9, "§cPractice Kill Effects");
        IItemStack glass = new IItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15).setItemName("§c");
        IItemStack sapling = new IItemStack(Material.SAPLING, 1, (short) 0).setItemName("§aPracticeKillEffects§7(" + PracticeKillEffects.getInstance().getDescription().getVersion() + ")")
                .setItemLore("\n§bMade By §a§l" + "commandf1" + "\n" + "§aThis plugin can not be shared,\n§cThis plugin is a private plugin!");
        gui.fullItem(glass);
        gui.setItem(13, sapling);
        gui.addListener(new GUIAction(event -> event.setCancelled(true), glass.getItemStack()));
        gui.addListener(new GUIAction(event -> event.setCancelled(true), sapling.getItemStack()));
    }
}

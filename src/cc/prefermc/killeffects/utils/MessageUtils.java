package cc.prefermc.killeffects.utils;

import java.util.List;

import static cc.prefermc.killeffects.utils.MessageUtils.Types.*;
import static space.commandf1.fasterlib.api.color.Color.*;
import static cc.prefermc.killeffects.utils.ConfigUtils.*;
import static cc.prefermc.killeffects.utils.MessageUtils.UI.*;

public class MessageUtils {
    public static class UI {
        public static String KILL_SLOWLY;
        public static List<String> KILL_SLOWLY_LORE;
        public static String AUTO_DROP_EMPTY_BOTTLE;
        public static List<String> AUTO_DROP_EMPTY_BOTTLE_LORE;
        public static String AUTO_L;
        public static List<String> AUTO_L_LORE;
        public static String TYPE_ITEM_DROP;
        public static List<String> TYPE_ITEM_DROP_LORE;
        public static String SPAWN_BLOOD_ON_DAMAGE;
        public static List<String> SPAWN_BLOOD_ON_DAMAGE_LORE;
        public static String SKULL_BLOOD_SPAWN;
        public static List<String> SKULL_BLOOD_SPAWN_LORE;
        public static String TRUE;
        public static String FALSE;
    }
    public static class Types {
        public static String NOT_A_PLAYER_MESSAGE;
        public static String NO_PERMISSION_MESSAGE;
        public static String NO_SUB_COMMAND_MESSAGE;
        public static String DISABLED_EFFECT_USE;
        public static String CHOOSE_MESSAGE;
    }

    public static void load() {
        NOT_A_PLAYER_MESSAGE = toMinecraftColor(getMessage().getString("not-a-player-message"));
        NO_PERMISSION_MESSAGE = toMinecraftColor(getMessage().getString("no-permission-message"));
        NO_SUB_COMMAND_MESSAGE = toMinecraftColor(getMessage().getString("no-sub-command-message"));
        DISABLED_EFFECT_USE = toMinecraftColor(getMessage().getString("disabled-effect-use"));
        CHOOSE_MESSAGE = toMinecraftColor(getMessage().getString("choose-message"));

        KILL_SLOWLY = toMinecraftColor(getMessage().getString("ui.kill-slowly"));
        KILL_SLOWLY_LORE = toMinecraftColor(getMessage().getStringList("ui.kill-slowly-lore"));
        AUTO_DROP_EMPTY_BOTTLE = toMinecraftColor(getMessage().getString("ui.auto-drop-empty-bottle"));
        AUTO_DROP_EMPTY_BOTTLE_LORE = toMinecraftColor(getMessage().getStringList("ui.auto-drop-empty-bottle-lore"));
        AUTO_L = toMinecraftColor(getMessage().getString("ui.auto-l"));
        AUTO_L_LORE = toMinecraftColor(getMessage().getStringList("ui.auto-l-lore"));
        TYPE_ITEM_DROP = toMinecraftColor(getMessage().getString("ui.type-item-drop"));
        TYPE_ITEM_DROP_LORE = toMinecraftColor(getMessage().getStringList("ui.type-item-drop-lore"));
        SPAWN_BLOOD_ON_DAMAGE = toMinecraftColor(getMessage().getString("ui.spawn-blood-on-damage"));
        SPAWN_BLOOD_ON_DAMAGE_LORE = toMinecraftColor(getMessage().getStringList("ui.spawn-blood-on-damage-lore"));
        SKULL_BLOOD_SPAWN = toMinecraftColor(getMessage().getString("ui.skull-blood-spawn"));
        SKULL_BLOOD_SPAWN_LORE = toMinecraftColor(getMessage().getStringList("ui.skull-blood-spawn-lore"));
        TRUE = toMinecraftColor(getMessage().getString("ui.true"));
        FALSE = toMinecraftColor(getMessage().getString("ui.false"));
    }
}

package cc.prefermc.killeffects.utils;

import cc.prefermc.killeffects.PracticeKillEffects;
import org.bukkit.configuration.file.YamlConfiguration;
import space.commandf1.fasterlib.api.config.Config;

import java.util.List;

import static cc.prefermc.killeffects.utils.ConfigUtils.Types.*;

public class ConfigUtils {
    public static class Types {
        public static boolean SETTINGS_NO_DEATH_MESSAGE;
        public static boolean SETTINGS_NO_BAD_KICK;
        public static boolean KILL_SLOWLY_ENABLE;
        public static int KILL_SLOWLY_CONTINUE_TIME;
        public static boolean AUTO_DROP_EMPTY_BOTTLE_ENABLE;
        public static boolean AUTO_L_ENABLE;
        public static String AUTO_L_MESSAGE;
        public static boolean TYPE_ITEM_DROP_ENABLE;
        public static List<String> TYPE_ITEM_DROP_ITEMS;
        public static boolean SPAWN_BLOOD_ON_DAMAGE_ENABLE;
        public static int SPAWN_BLOOD_ON_DAMAGE_SIZE;
        public static boolean SPAWN_BLOOD_ON_DAMAGE_PLAYER_ONLY;
        public static boolean SKULL_BLOOD_SPAWN_ENABLE;
        public static int SKULL_BLOOD_SPAWN_TIME;
    }
    private static final Config settings = PracticeKillEffects.getSettings();
    private static final Config data = PracticeKillEffects.getData();
    private static final Config message = PracticeKillEffects.getMessage();

    public static void reloadConfigs() {
        settings.reloadConfig();
        data.reloadConfig();
        message.reloadConfig();
        MessageUtils.load();
        load();
    }

    public static YamlConfiguration getSettings() {
        return settings.getConfig();
    }

    public static YamlConfiguration getData() {
        return data.getConfig();
    }

    public static YamlConfiguration getMessage() {
        return message.getConfig();
    }

    public static void load() {
        SETTINGS_NO_DEATH_MESSAGE = getSettings().getBoolean("settings.no-death-message");
        SETTINGS_NO_BAD_KICK = getSettings().getBoolean("settings.no-bad-kick");
        KILL_SLOWLY_ENABLE = getSettings().getBoolean("kill-slowly.enable");
        KILL_SLOWLY_CONTINUE_TIME = getSettings().getInt("kill-slowly.continue-time");
        AUTO_DROP_EMPTY_BOTTLE_ENABLE = getSettings().getBoolean("auto-drop-empty-bottle.enable");
        AUTO_L_ENABLE = getSettings().getBoolean("auto-L.enable");
        AUTO_L_MESSAGE = getSettings().getString("auto-L.message");
        TYPE_ITEM_DROP_ENABLE = getSettings().getBoolean("type-item-drop.enable");
        TYPE_ITEM_DROP_ITEMS = getSettings().getStringList("type-item-drop.items");
        SPAWN_BLOOD_ON_DAMAGE_ENABLE = getSettings().getBoolean("spawn-blood-on-damage.enable");
        SPAWN_BLOOD_ON_DAMAGE_SIZE = getSettings().getInt("spawn-blood-on-damage.size");
        SPAWN_BLOOD_ON_DAMAGE_PLAYER_ONLY = getSettings().getBoolean("spawn-blood-on-damage.player-only");
        SKULL_BLOOD_SPAWN_ENABLE = getSettings().getBoolean("skull-blood-spawn.enable");
        SKULL_BLOOD_SPAWN_TIME = getSettings().getInt("skull-blood-spawn.time");
    }
}

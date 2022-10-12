package cc.prefermc.killeffects.playerdata;

import cc.prefermc.killeffects.PracticeKillEffects;
import cc.prefermc.killeffects.utils.ConfigUtils;
import org.bukkit.entity.Player;

public class PlayerDataManager {

    public PlayerDataManager() {}

    public static PlayerData getPlayerData(Player player) {
        PracticeKillEffects.getData().reloadConfig();
        if (ConfigUtils.getData().getString(player.getName()) == null) {
            PlayerData data = new PlayerData(player.getName(), false, false, false, false, false, false);
            writePlayerData(data);
            return data;
        }
        String[] strings = ConfigUtils.getData().getString(player.getName()).split(",");
        boolean killSlowly = getBoolean(strings[0]), autoDropEmptyBottle =
                getBoolean(strings[1]), autoL = getBoolean(strings[2]), typeItemDrop = getBoolean(strings[3]), spawnBloodOnDamage = getBoolean(strings[4]), skullBloodSpawn = getBoolean(strings[5]);
        return new PlayerData(player.getName(), killSlowly, autoDropEmptyBottle, autoL, typeItemDrop, spawnBloodOnDamage, skullBloodSpawn);
    }

    public static void writePlayerData(PlayerData playerData) {
        PracticeKillEffects.getData().setWithoutException(playerData.getPlayer(),
                getIntToString(playerData.isKillSlowly()) + ","
                + getIntToString(playerData.isAutoDropEmptyItem()) +
                        "," + getIntToString(playerData.isAutoL()) +
                        "," + getIntToString(playerData.isTypeItemDrop()) +
                        "," + getIntToString(playerData.isSpawnBloodOnDamage()) +
                        "," + getIntToString(playerData.isSkullBloodSpawn()));
        PracticeKillEffects.getData().reloadConfig();
    }

    public static boolean isPlayerDataExists(Player player) {
        return ConfigUtils.getData().getString(player.getName()) != null;
    }

    public static boolean getBoolean(int i) {
        return i != 0;
    }

    public static boolean getBoolean(String i) {
        return getBoolean(Integer.parseInt(i));
    }

    public static int getInt(boolean b) {
        return b ? 1 : 0;
    }

    public static String getIntToString(boolean b) {
        return String.valueOf(getInt(b));
    }
}

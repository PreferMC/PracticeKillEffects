package cc.prefermc.killeffects.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import space.commandf1.fasterlib.api.util.utils.WebUtils;
import cc.prefermc.killeffects.PracticeKillEffects;

import java.security.MessageDigest;
import java.util.logging.Logger;

@Deprecated
public class VerificationUtils extends JavaPlugin {
    public VerificationUtils() { // first one
        if (verify(getHWID())) {
            return;
        }
        getLogger().severe("Can not verify your hwid, please try again later.");
        getLogger().severe("Make sure that you have been bought the plugin.");
        getLogger().severe("HWID: " + getHWID());
        Bukkit.getPluginManager().disablePlugin(this);
    }

    public static final String VERIFICATION_LINK = "null";

    public static String getHWID() {
        try {
            String toEncrypt = System.getenv("CompUTERNAME".toUpperCase()) + System.getProperty("user.name") +
                    System.getenv("PROCESSOR_IDENTIFIER") +
                    System.getenv("PROCESSOR_LEVEL");
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(toEncrypt.getBytes());
            StringBuilder hexString = new StringBuilder();
            byte[] byteData = md.digest();
            for (byte aByteData : byteData) {
                String hex = Integer.toHexString(0xff & aByteData);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ignored) {
            return null;
        }
    }

    public static boolean verify(String hwidCode) {
        String[] hwid = WebUtils.getHtml(VERIFICATION_LINK).split("\\|");
        for (String s : hwid) {
            if (hwidCode.equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

    public static void check() {
        if (verify(getHWID())) {
            return;
        }
        Logger logger = PracticeKillEffects.getInstanceLogger();
        logger.severe("Can not verify your hwid, please try again later.");
        logger.severe("Make sure that you have been bought the plugin.");
        logger.severe("HWID: " + getHWID());
        PracticeKillEffects.getInstance().onDisable();
        Bukkit.getPluginManager().disablePlugin(PracticeKillEffects.getInstance());
    }
}

package space.commandf1.fasterlib.api.util.utils;

import space.commandf1.fasterlib.api.util.Util;

@SuppressWarnings("unused")
public final class SystemUtils extends Util {
    public SystemUtils() {
        super("SystemUtils", true, false);
    }

    /**
     * 判断主机是否为Linux系统
     *
     */
    public static boolean isLinux() {
        return System.getProperty("os.name").toLowerCase().startsWith("linux");
    }
}

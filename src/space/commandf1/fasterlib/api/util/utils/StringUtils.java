package space.commandf1.fasterlib.api.util.utils;

import space.commandf1.fasterlib.api.util.Util;

@SuppressWarnings("unused")
public final class StringUtils extends Util {
    public StringUtils() {
        super("StringUtils", true, false);
    }

    /**
     * 获取指定字符串后面的字符串
     *
     * */
    public static String getAfterString(String str, String afterStr) {
        int index = str.indexOf(afterStr);
        if (index == -1) {
            return null;
        }
        return str.substring(index + afterStr.length());
    }
}

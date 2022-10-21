package cc.prefermc.killeffects;

import cc.prefermc.killeffects.commands.EffectSettingsCommand;
import cc.prefermc.killeffects.commands.MainCommand;
import cc.prefermc.killeffects.listeners.PlayerListener;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.message.Message;
import org.bukkit.plugin.java.JavaPlugin;
import space.commandf1.fasterlib.api.command.CommandManager;
import space.commandf1.fasterlib.api.config.Config;
import space.commandf1.fasterlib.api.listener.ListenerManager;
import space.commandf1.fasterlib.api.util.utils.LoggerUtils;
import space.commandf1.fasterlib.listeners.GUIListener;
import cc.prefermc.killeffects.commands.AdminCommand;
import cc.prefermc.killeffects.utils.ConfigUtils;
import cc.prefermc.killeffects.utils.MessageUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.logging.Logger;

public class PracticeKillEffects extends JavaPlugin {
    private static PracticeKillEffects instance;
    private static Logger logger;
    private static Config config;
    private static Config data;
    private static Config message;

    public static Config getData() {
        return data;
    }

    public static Config getMessage() {
        return message;
    }

    @Override
    public void onEnable() {
        // set things
        instance = this;
        logger = this.getLogger();

        /*
        // verify
        if (!verify(getHWID())) {
            logger.severe("Can not verify your hwid, please try again later.");
            logger.severe("Make sure that you have been bought the plugin.");
            logger.severe("HWID: " + getHWID());
            this.onDisable();
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        } // second one
        check(); // third one

        logger.info("Verify successfully, version: " + getDescription().getVersion());

         // no need
         */
        // setup lib
        ((org.apache.logging.log4j.core.Logger) LogManager.getRootLogger()).addFilter(new Filter() {
            public Result filter(LogEvent event) {
                if (LoggerUtils.getStrings().contains(event.getMessage().toString())) {
                    return Result.DENY;
                }
                return null;
            }

            @Override
            public Result getOnMismatch() {
                return null;
            }

            @Override
            public Result getOnMatch() {
                return null;
            }

            @Override
            public Result filter(org.apache.logging.log4j.core.Logger logger, Level level, Marker marker, String s, Object... objects) {
                return null;
            }

            @Override
            public Result filter(org.apache.logging.log4j.core.Logger logger, Level level, Marker marker, Object o, Throwable throwable) {
                return null;
            }

            @Override
            public Result filter(org.apache.logging.log4j.core.Logger logger, Level level, Marker marker, Message message, Throwable throwable) {
                return null;
            }
        });
        saveResource("config.yml", false);
        saveResource("data.yml", false);
        saveResource("message.yml", false);
        config = new Config("config.yml", this);
        data = new Config("data.yml", this);
        message = new Config("message.yml", this);
        ConfigUtils.load();
        MessageUtils.load();

        // setup plugin
        ListenerManager.registerListeners(this, new PlayerListener(), new GUIListener());
        CommandManager.registerCommandsWithoutRegisterOnPluginYmlFile(this,
                new MainCommand(), new AdminCommand(), new EffectSettingsCommand());
    }

    @Override
    public void onDisable() {
        instance = null;
        logger = null;
        config = null;
    }

    public static PracticeKillEffects getInstance() {
        return instance;
    }

    public static Logger getInstanceLogger() {
        return logger;
    }

    public static Config getSettings() {
        return config;
    }

    @Override
    public void saveResource(String resourcePath, boolean replace) {
        resourcePath = resourcePath.replace('\\', '/');
        InputStream in = this.getResource(resourcePath);
        if (in == null) {
            throw new IllegalArgumentException("插件配置文件损坏，请重新下载插件!" + '\n' + "The plugin file may have been broken, please re-download the plugin.");
        }
        File outFile = new File(this.getDataFolder(), resourcePath);
        int lastIndex = resourcePath.lastIndexOf(47);
        File outDir = new File(this.getDataFolder(), resourcePath.substring(0, Math.max(lastIndex, 0)));
        if (!outDir.exists() && !outDir.mkdirs()) {
            throw new IllegalArgumentException("无法创建目录" + "\n" + "Can not create the dir.");
        }
        try {
            if (!(outFile.exists() && !replace)) {
                OutputStream out = Files.newOutputStream(outFile.toPath());
                byte[] buf = new byte[1024];

                int len;
                while((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

                out.close();
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

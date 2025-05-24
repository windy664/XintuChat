package org.windy.xintuChat;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

public class XintuChat extends JavaPlugin {

    private static XintuChat instance;
    private static SensitiveFilter sensitiveFilter;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        sensitiveFilter = SensitiveFilter.fromConfig(getConfig());
        instance = this;
        Bukkit.getPluginManager().registerEvents(new GuildMessageListener(), this);
        this.getCommand("reply").setExecutor(new ReplyCommand());

        getLogger().info("XintuChat 插件已启用");
        sensitiveFilter.reloadCloudWords();
    }


    public static XintuChat getInstance() {
        return instance;
    }
    public static SensitiveFilter getSensitiveFilter() {
        return sensitiveFilter;
    }
}

package com.example.socialtexting;
import org.bukkit.plugin.java.JavaPlugin;

public class SocialTextingPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Social Texting Plugin включён!");
        getCommand("st").setExecutor(new STCommand(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("Social Texting Plugin выключен!");
    }
}

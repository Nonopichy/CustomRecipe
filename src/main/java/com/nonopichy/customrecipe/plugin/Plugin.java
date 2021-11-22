package com.nonopichy.customrecipe.plugin;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Plugin extends JavaPlugin {
    @Getter private static JavaPlugin instance;
    @Getter private static String[] art = new String[]
            {       "   _____          _                  _____           _            ",
                    "  / ____|        | |                |  __ \\         (_)           ",
                    " | |    _   _ ___| |_ ___  _ __ ___ | |__) |___  ___ _ _ __   ___",
                    " | |   | | | / __| __/ _ \\| '_ ` _ \\|  _  // _ \\/ __| | '_ \\ / _ \\",
                    " | |___| |_| \\__ \\ || (_) | | | | | | | \\ \\  __/ (__| | |_) |  __/",
                    "  \\_____\\__,_|___/\\__\\___/|_| |_| |_|_|  \\_\\___|\\___|_| .__/ \\___|",
                    "                                                      | |         ",
                    "                                                      |_|         "};
    public void onEnable(){
        instance = this;
        printArt();
    }
    public void printArt(){
        Logger log = Bukkit.getLogger();
        for(int i = 0 ; i < art.length ; i++)
            log.info(art[i]);
    }
}

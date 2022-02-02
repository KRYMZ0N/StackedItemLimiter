package me.krymz0n.stackeditemlimiter.event;

import me.krymz0n.stackeditemlimiter.Main;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class FiveSecondEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Main plugin;
    private final Logger logger;

    public FiveSecondEvent(Main plugin, Logger log) {
        this.plugin = plugin;
        this.logger = log;
    }

    public Logger getLogger() {
        return logger;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public Main getPlugin() {
        return plugin;
    }
}

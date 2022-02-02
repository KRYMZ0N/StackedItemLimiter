package me.krymz0n.stackeditemlimiter.listener;

import me.krymz0n.stackeditemlimiter.Main;
import me.krymz0n.stackeditemlimiter.event.FiveSecondEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Collection;

public class StackedItemListener implements Listener {
    private final Main plugin;

    public StackedItemListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFiveSecondEvent(FiveSecondEvent evt) {
        System.out.println("Should be called!");
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.isValid() && !p.isDead()) {
                plugin.revertStacks(p.getInventory());
            }
        }
    }
}

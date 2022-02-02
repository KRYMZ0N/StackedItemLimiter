package me.krymz0n.stackeditemlimiter;

import me.krymz0n.stackeditemlimiter.event.FiveSecondEvent;
import me.krymz0n.stackeditemlimiter.listener.StackedItemListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class Main extends JavaPlugin implements Listener {
    PluginManager pm = getServer().getPluginManager();
    FiveSecondEvent event = new FiveSecondEvent(this, getLogger());


    @Override
    public void onEnable() {
        saveDefaultConfig();
        pm.registerEvents(new StackedItemListener(this), this);
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
        //service.scheduleAtFixedRate(() -> pm.callEvent(event), 1, 5, TimeUnit.SECONDS);

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            pm.callEvent(event);

        }, 0L, 100L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
   // --Reverting stacks from list in config--
    public void revertStacks(Inventory inventory) {
        System.out.println("Called");
        for (ItemStack i : inventory.getContents()) { // iterate through inventory to see if it is an illegal stack
            if (i != null && this.getConfig().getStringList("IllegalStack").contains(i.getType().toString())) {
                System.out.println("Inv contains!");
                if (i.getAmount() > 1) {
                    i.setAmount(1);
                }
            }

            if (i.getType().equals(Material.SPLASH_POTION) || i.getType().equals(Material.POTION) || i.getType().equals(Material.LINGERING_POTION)) {
                PotionMeta pm = (PotionMeta) i.getItemMeta();

                if (pm.hasCustomEffect(PotionEffectType.DAMAGE_RESISTANCE) || pm.hasCustomEffect(PotionEffectType.SLOW)) {
                    if (i.getAmount() > 1) {
                        i.setAmount(1);
                    }
                }
            }
        }
    }
}

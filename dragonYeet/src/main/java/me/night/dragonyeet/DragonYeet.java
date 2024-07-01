package me.night.dragonyeet;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public final class DragonYeet extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getDamager().getType() == EntityType.ENDER_DRAGON) {
                Vector knockback = player.getLocation().toVector()
                        .subtract(event.getDamager().getLocation().toVector())
                        .normalize()
                        .multiply(25); // Adjust multiplier for knockback strength
                knockback.setY(1000); // Adjust Y velocity for upward knockback
                knockback.setZ(-500); // Adjust z velocity for backwards knockback
                player.setVelocity(knockback);


                // Modify player attributes
                AttributeInstance movementSpeed = player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
                if (movementSpeed != null) {
                    movementSpeed.setBaseValue(2); // Increase movement speed temporarily
                }

                AttributeInstance knockbackResistance = player.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
                if (knockbackResistance != null) {
                    knockbackResistance.setBaseValue(0); // Ensure no knockback resistance
                }

                // Schedule a task to reset the attributes after 5 seconds (100 ticks)
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (movementSpeed != null) {
                            movementSpeed.setBaseValue(0.1); // Reset to default value
                        }
                    }
                }.runTaskLater(this, 100); // 100 ticks = 5 seconds
            }
        }
    }
}

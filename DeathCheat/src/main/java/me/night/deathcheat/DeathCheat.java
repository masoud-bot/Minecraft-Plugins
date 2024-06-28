package me.night.deathcheat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class DeathCheat extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("MobHealthDisplay has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("MobHealthDisplay has been disabled!");
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity) {
            updateHealthDisplay((LivingEntity) entity);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            double health = livingEntity.getHealth();
            double damage = event.getFinalDamage();

            // If the entity has more than 2 hearts and would be killed by this damage
            if (health >= 5.5 && health - damage <= -2) {
                event.setDamage( 0);
                ((LivingEntity) entity).setHealth(1);
                // Send a message to all players in chat
                String message = String.format("§c§l%s §fwas saved from death and is now at §c§lhalf a heart§f!", livingEntity.getType().name());
                Bukkit.getServer().broadcastMessage(message);           new BukkitRunnable() {
                    @Override
                    public void run() {
                        updateHealthDisplay(livingEntity);
                    }
                }.runTaskLater(this, 1L);
            } else {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        updateHealthDisplay(livingEntity);
                    }
                }.runTaskLater(this, 1L);
            }
        }
    }

    private void updateHealthDisplay(LivingEntity entity) {
        double health = entity.getHealth();
        double maxHealth = entity.getMaxHealth();
        String displayName = String.format("§c❤ %.1f/%.1f", health, maxHealth);
        entity.setCustomName(displayName);
        entity.setCustomNameVisible(true);
    }
}

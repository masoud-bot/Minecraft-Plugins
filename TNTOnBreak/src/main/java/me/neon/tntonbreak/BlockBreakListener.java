package me.neon.tntonbreak;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        // Get the block's location
        Location blockLocation = event.getBlock().getLocation();

        // Spawn a TNTPrimed entity at the block's location
        TNTPrimed tnt = (TNTPrimed) blockLocation.getWorld().spawnEntity(blockLocation, EntityType.PRIMED_TNT);

        // Set the fuse ticks (20 ticks = 1 second)
        tnt.setFuseTicks(40); // The TNT will explode after 2 seconds
    }

}

package xyz.tehbrian.buildersutilities.option;

import com.google.inject.Inject;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Slab;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.checkerframework.checker.nullness.qual.NonNull;
import xyz.tehbrian.buildersutilities.Constants;
import xyz.tehbrian.buildersutilities.user.UserService;

@SuppressWarnings("unused")
public final class DoubleSlabListener implements Listener {

    private final UserService userService;

    @Inject
    public DoubleSlabListener(
            final @NonNull UserService userService
    ) {
        this.userService = userService;
    }

    @EventHandler(ignoreCancelled = true)
    public void onDoubleSlabBreak(final BlockBreakEvent event) {
        final Player player = event.getPlayer();

        if (!this.userService.getUser(player).doubleSlabBreakEnabled()
                || !player.hasPermission(Constants.Permissions.DOUBLE_SLAB_BREAK)
                || !Tag.SLABS.isTagged(player.getInventory().getItemInMainHand().getType())
                || player.getGameMode() != GameMode.CREATIVE
                || !Tag.SLABS.isTagged(event.getBlock().getType())) {
            return;
        }

        final Slab blockData = (Slab) event.getBlock().getBlockData();
        if (blockData.getType() != Slab.Type.DOUBLE) {
            return;
        }

        if (this.isTop(player, event.getBlock())) {
            blockData.setType(Slab.Type.BOTTOM);
        } else {
            blockData.setType(Slab.Type.TOP);
        }

        event.getBlock().setBlockData(blockData, true);
        event.setCancelled(true);
    }

    private boolean isTop(final Player player, final Block block) {
        final Location start = player.getEyeLocation().clone();
        while (!start.getBlock().equals(block) && start.distance(player.getEyeLocation()) < 6.0D) {
            start.add(player.getEyeLocation().getDirection().multiply(0.05D));
        }
        return start.getY() % 1.0D > 0.5D;
    }

}

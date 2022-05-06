package xyz.tehbrian.buildersutilities.ability;

import com.google.inject.Inject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.spongepowered.configurate.NodePath;
import xyz.tehbrian.buildersutilities.Constants;
import xyz.tehbrian.buildersutilities.config.LangConfig;
import xyz.tehbrian.buildersutilities.user.User;
import xyz.tehbrian.buildersutilities.user.UserService;

import java.util.Objects;

@SuppressWarnings("unused")
public final class AbilityInventoryListener implements Listener {

    private final UserService userService;
    private final AbilityInventoryProvider abilityInventoryProvider;
    private final LangConfig langConfig;

    @Inject
    public AbilityInventoryListener(
            final @NonNull UserService userService,
            final @NonNull AbilityInventoryProvider abilityInventoryProvider,
            final @NonNull LangConfig langConfig
    ) {
        this.userService = userService;
        this.abilityInventoryProvider = abilityInventoryProvider;
        this.langConfig = langConfig;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClick(final InventoryClickEvent event) {
        if (!Objects.equals(event.getClickedInventory(), event.getView().getTopInventory())
                || !event.getView().title().equals(this.langConfig.c(NodePath.path("inventories", "ability", "inventory-name")))
                || !(event.getWhoClicked() instanceof Player player)) {
            return;
        }

        final int slot = event.getRawSlot();

        event.setCancelled(true);

        final User user = this.userService.getUser(player);
        switch (slot) {
            case 1, 10, 19 -> {
                if (player.hasPermission(Constants.Permissions.IRON_DOOR_TOGGLE)) {
                    user.toggleIronDoorToggleEnabled();
                }
            }
            case 2, 11, 20 -> {
                if (player.hasPermission(Constants.Permissions.DOUBLE_SLAB_BREAK)) {
                    user.toggleDoubleSlabBreakEnabled();
                }
            }
            case 3, 12, 21 -> {
                if (player.hasPermission(Constants.Permissions.GLAZED_TERRACOTTA_ROTATE)) {
                    user.toggleGlazedTerracottaRotateEnabled();
                }
            }
            case 5, 14, 23 -> {
                if (player.hasPermission(Constants.Permissions.NIGHT_VISION)) {
                    user.toggleNightVisionEnabled();
                }
            }
            case 6, 15, 24 -> {
                if (player.hasPermission(Constants.Permissions.NIGHT_VISION)) {
                    user.toggleNoClipEnabled();
                }
            }
            case 7, 16, 25 -> {
                if (player.hasPermission(Constants.Permissions.ADVANCED_FLY)) {
                    user.toggleAdvancedFlyEnabled();
                }
            }
            default -> {
            }
        }

        this.abilityInventoryProvider.update(event.getView().getTopInventory(), user);
    }

}
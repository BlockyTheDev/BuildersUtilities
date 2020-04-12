package com.buildersrefuge.utilities.listeners;

import com.buildersrefuge.utilities.Main;
import com.buildersrefuge.utilities.object.NoClipManager;
import com.buildersrefuge.utilities.util.ToggleGUI;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ToggleInventoryListener implements Listener {
    public Main plugin;

    public ToggleInventoryListener(Main main) {
        plugin = main;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        int slot;
        String name;
        try {
            slot = e.getRawSlot();
            name = e.getClickedInventory().getName();
        } catch (Exception exc) {
            return;
        }
        ToggleGUI gui = new ToggleGUI();
        if (name.equals("§1Builders Utilities")) {
            e.setCancelled(true);
            switch (slot) {
                case 1:
                case 10:
                case 19:
                    if (Main.ironTrapdoorNames.contains(p.getName())) {
                        Main.ironTrapdoorNames.remove(p.getName());
                    } else {
                        Main.ironTrapdoorNames.add(p.getName());
                    }
                    break;
                case 2:
                case 11:
                case 20:
                    if (Main.slabNames.contains(p.getName())) {
                        Main.slabNames.remove(p.getName());
                    } else {
                        Main.slabNames.add(p.getName());
                    }
                    break;
                case 3:
                case 12:
                case 21:
                    if (Main.version.contains("v1_12")) {
                        if (Main.terracottaNames.contains(p.getName())) {
                            Main.terracottaNames.remove(p.getName());
                        } else {
                            Main.terracottaNames.add(p.getName());
                        }
                    }
                    break;
                case 5:
                case 14:
                case 23:
                    if (p.hasPermission("builders.util.nightvision")) {
                        if (p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                            p.removePotionEffect(PotionEffectType.NIGHT_VISION);
                        } else {
                            p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, true, false));
                        }
                    }
                    break;
                case 6:
                case 15:
                case 24:
                    if (p.hasPermission("builders.util.noclip")) {
                        if (NoClipManager.noClipPlayerNames.contains(p.getName())) {
                            NoClipManager.noClipPlayerNames.remove(p.getName());
                            if (p.getGameMode() == GameMode.SPECTATOR) {
                                p.setGameMode(GameMode.CREATIVE);
                            }
                        } else {
                            NoClipManager.noClipPlayerNames.add(p.getName());
                        }
                    }
                    break;
                case 7:
                case 16:
                case 25:
                    if (p.hasPermission("builders.util.advancedfly")) {
                        PlayerMoveListener.togglePlayer(p);
                    }
                    break;
            }
            gui.updateInv(e.getClickedInventory(), p);
        }
    }
}


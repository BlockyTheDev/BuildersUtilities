package xyz.tehbrian.buildersutilities.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.tehbrian.buildersutilities.inventories.ArmorColorInventoryProvider;

public final class ArmorColorCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            player.openInventory(ArmorColorInventoryProvider.generate());
        }
        return true;
    }
}

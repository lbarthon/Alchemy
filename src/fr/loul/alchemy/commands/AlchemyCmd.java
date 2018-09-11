package fr.loul.alchemy.commands;

import fr.loul.alchemy.*;
import fr.loul.alchemy.edit.*;
import fr.loul.alchemy.use.Check;
import fr.loul.alchemy.use.GUI;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AlchemyCmd implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if (!(sender instanceof Player)) {
			Alchemy.sendConsoleMessage(Alchemy.getCfgStr("ConsoleCantSend"));
			return true;
		}
		Player p = (Player) sender;
		if (args.length == 0) {
			if (Check.canUse(p))	GUI.open(p);
			else					p.sendMessage(Alchemy.getCfgStr("NoItem"));
			return true;
		}
		else if (args.length == 1) {
			if (p.isOp() && args[0].equalsIgnoreCase("give")) {
				ItemStack item = new ItemStack(Material.INK_SACK, 1, (short) 7);
				ItemMeta meta = item.getItemMeta();
				meta.setLore(Arrays.asList("§8apotheios:alchemy_clay"));
				item.setItemMeta(meta);
				p.getInventory().addItem(item);
			}
		}
		else if (args.length == 2) {
			if (args[0].equalsIgnoreCase("edit")) {
				if (Integer.valueOf(args[1]) >= 1 && Integer.valueOf(args[1]) <= 5) {
					if (p.isOp())		EditGui.openGui(p, Integer.valueOf(args[1]));
					else				p.sendMessage(Alchemy.getCfgStr("PlayerNotOP"));
					return true;
				}else {
					p.sendMessage(Alchemy.getCfgStr("OPUsage"));
					return true;
				}
			}
		}
		else {
			if (!p.isOp())
				p.sendMessage(Alchemy.getCfgStr("PlayerUsage"));
			else
				p.sendMessage(Alchemy.getCfgStr("OPUsage"));
			return true;
		}
		return true;
	}
}

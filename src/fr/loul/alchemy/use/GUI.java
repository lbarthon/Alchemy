package fr.loul.alchemy.use;

import fr.loul.alchemy.Alchemy;
import fr.loul.alchemy.utils.*;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUI {

	public static void open(Player p) {
		Inventory inv = Bukkit.createInventory(null, 54, "Alchemy");
		int i = 0, j, n = 0;
		while (i < 5) {
			for (j = 0; j < 9; j++) {
				if (Alchemy.rewards.get(i) != null) {
					if (Alchemy.rewards.get(i).getItem(j) != null) {
						if (Alchemy.rewards.get(i).getItem(j).getType() != Material.AIR)
							inv.setItem(n, Alchemy.rewards.get(i).getItem(j));
					}
				}
				n++;
			}
			i++;
		}
		ItemStack white = ItemStacks.createItemStack(Material.STAINED_GLASS_PANE, null, "§f");
		ItemStack scroll = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
		scroll = ItemStacks.createItemStack(scroll, null, "§aLancer");
		ItemStack exit = ItemStacks.createItemStack(Material.BARRIER, null, "§cQuitter");
		inv.setItem(45, white);
		inv.setItem(46, white);
		inv.setItem(47, scroll);
		inv.setItem(48, white);
		inv.setItem(49, white);
		inv.setItem(50, white);
		inv.setItem(51, exit);
		inv.setItem(52, white);
		inv.setItem(53, white);
		p.openInventory(inv);
	}
}

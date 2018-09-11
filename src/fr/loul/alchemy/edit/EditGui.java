package fr.loul.alchemy.edit;

import fr.loul.alchemy.*;
import fr.loul.alchemy.yml.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class EditGui implements Listener {
	
	public static void openGui(Player p, int i) {
		Inventory inv = Bukkit.createInventory(null, 9, "Alchemy - Edit " + String.valueOf(i));
		for (int n = 0; n < 9; n++) {
			if (Alchemy.rewards.get(i - 1) != null) {
				if (Alchemy.rewards.get(i - 1).getItem(n) != null) {
					if (Alchemy.rewards.get(i - 1).getItem(n).getType() != Material.AIR) {
						inv.setItem(n, Alchemy.rewards.get(i - 1).getItem(n));
					}
				}
			}
		}
		p.openInventory(inv);
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (e.getPlayer() instanceof Player) {
			if (e.getInventory().getName().startsWith("Alchemy - Edit")) {
				int nb = Integer.valueOf(e.getInventory().getName().replace("Alchemy - Edit ", ""));
				Rewards.saveInv(e.getInventory(), nb);
			}
		}
	}
}

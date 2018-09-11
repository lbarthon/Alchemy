package fr.loul.alchemy.use;

import fr.loul.alchemy.use.AlchemyUse;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickInventory implements Listener {

	public static ArrayList<Player> used = new ArrayList<Player>();
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			if (e.getInventory().getName().equals("Alchemy")) {
				e.setCancelled(true);
				Player p = (Player) e.getWhoClicked();
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cQuitter")) {
					if (used.contains(p)) return;
					AlchemyUse.quit(p);
					p.closeInventory();
				}
				else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aLancer")) {
					if (used.contains(p)) return;
					used.add(p);
					AlchemyUse.use(p);
				}
			}
		}
	}
}

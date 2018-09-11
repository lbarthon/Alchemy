package fr.loul.alchemy.use;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class CloseInventory implements Listener {

	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (e.getPlayer() instanceof Player) {
			if (e.getInventory().getName().equals("Alchemy")) {
				Player p = (Player) e.getPlayer();
				AlchemyUse.quit(p);
				ClickInventory.used.remove(p);
			}
		}
	}
}

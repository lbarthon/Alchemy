package fr.loul.alchemy.use;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Check {

	public static boolean canUse(Player p) {		
		for (ItemStack itemstack : p.getInventory().getContents()) {
			if (itemstack == null) continue;
			if (itemstack.getItemMeta() == null) continue;
			if (itemstack.getItemMeta().getLore() == null) continue;
			if (itemstack.getItemMeta().getLore().contains("§8apotheios:alchemy_clay")) {
				return true;
			}
		}
		return false;
	}
	
	public static void use(Player p) {
		for (ItemStack itemstack : p.getInventory().getContents()) {
			if (itemstack == null) continue;
			if (itemstack.getItemMeta() == null) continue;
			if (itemstack.getItemMeta().getLore() == null) continue;
			if (itemstack.getItemMeta().getLore().contains("§8apotheios:alchemy_clay")) {
				itemstack.setAmount(itemstack.getAmount() - 1);
				p.updateInventory();
				break;
			}
		}
	}
}

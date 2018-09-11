package fr.loul.alchemy.use;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.loul.alchemy.Alchemy;
import fr.loul.alchemy.utils.ItemStacks;
import fr.loul.alchemy.utils.RandomInt;

public class AlchemyUse {
	
	private static HashMap<Player, ItemStack> map = new HashMap<Player, ItemStack>();
	private static HashMap<Player, Integer> inv = new HashMap<Player, Integer>();
	private static HashMap<Player, Boolean> close = new HashMap<Player, Boolean>();
	private static HashMap<Player, Boolean> last = new HashMap<Player, Boolean>();
	
	public static void use(Player p) {
		if (!inv.containsKey(p)) {
			inv.put(p, 0);
			Check.use(p);
		}else {
			inv.replace(p, inv.get(p) + 1);
		}
		if (inv.get(p) == 5 || (map.containsKey(p) &&
				(map.get(p).getType() == Material.BARRIER || map.get(p).getType() == Material.AIR))) {
			if (inv.get(p) != 5) map.replace(p, new ItemStack(Material.AIR));
			quit(p);
			p.closeInventory();
			return;
		}
		int won = RandomInt.get(9) + (inv.get(p) * 9);
		close.remove(p);
		scroll(p, won, inv.get(p));
	}
	
	public static void quit(Player p) {
		if (map.containsKey(p)) {
			close.put(p, true);
			if (p.getInventory().firstEmpty() != -1) {
				if (map.get(p) != null)
					p.getInventory().addItem(map.get(p));
			}else {
				if (map.get(p) != null)
					p.getLocation().getWorld().dropItem(p.getLocation(), map.get(p));
			}
			inv.remove(p);
			map.remove(p);
			if (last.containsKey(p)) {
				if (last.get(p)) {
					p.sendMessage(Alchemy.getCfgStr("LastLineSuccessfully").replace("%p", p.getName()));
					last.remove(p);
					return;
				}
			}
			p.sendMessage(Alchemy.getCfgStr("UsedSuccessfully"));
		}
	}

	private static void scroll(Player p, int slot, int line) {
		List<Inventory> invs = Alchemy.getRewardList();
		
		ItemStack reddd = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
		final ItemStack red = ItemStacks.createItemStack(reddd, null, "§f");
		
		for (int k = 0; k < line * 9; k++) {
			if (close.containsKey(p)) {
				if (close.get(p)) return;
			}
			p.getOpenInventory().setItem(k, red);
			p.updateInventory();
		}
		
		
		new BukkitRunnable() {
			@Override
			public void run() {
				if (close.containsKey(p)) {
					if (close.get(p)) return;
				}
				for (int j = 0; j < 9; j++) {
					p.getOpenInventory().setItem((line * 9) + j, red);
				}
				p.updateInventory();
			}
		}.runTaskLater(Alchemy.getPlugin(), 20);
		
		new BukkitRunnable() {
			@Override
			public void run() {
				if (close.containsKey(p)) {
					if (close.get(p)) return;
				}
				for (int j = 0; j < 9; j++) {
					if (invs.get(line) != null) {
						if (invs.get(line).getItem(j) != null) {
							p.getOpenInventory().setItem((line * 9) + j, invs.get(line).getItem(j));
						}else {
							p.getOpenInventory().setItem((line * 9) + j, null);
						}
					}else {
						p.getOpenInventory().setItem((line * 9) + j, null);
					}
				}
			p.updateInventory();
			}
		}.runTaskLater(Alchemy.getPlugin(), 40);
		
		new BukkitRunnable() {
			@Override
			public void run() {
				if (close.containsKey(p)) {
					if (close.get(p)) return;
				}
				for (int j = 0; j < 9; j++) {
					p.getOpenInventory().setItem((line * 9) + j, red);
				}
				p.updateInventory();
			}
		}.runTaskLater(Alchemy.getPlugin(), 60);
		
		new BukkitRunnable() {
			@Override
			public void run() {
				if (close.containsKey(p)) {
					if (close.get(p)) return;
				}
				for (int j = 0; j < 9; j++) {
					if (invs.get(line) != null) {
						if (invs.get(line).getItem(j) != null) {
							p.getOpenInventory().setItem((line * 9) + j, invs.get(line).getItem(j));
						}else {
							p.getOpenInventory().setItem((line * 9) + j, null);
						}
					}else {
						p.getOpenInventory().setItem((line * 9) + j, null);
					}
				}
			p.updateInventory();
			}
		}.runTaskLater(Alchemy.getPlugin(), 80);
		
		new BukkitRunnable() {
			@Override
			public void run() {
				if (close.containsKey(p)) {
					if (close.get(p)) return;
				}
				for (int i = 0; i < 9; i++) {
					if (((line * 9) + i) != slot) {
						p.getOpenInventory().setItem((line * 9) + i, red);
					}
				}
				p.updateInventory();
				ClickInventory.used.remove(p);
				if (inv.get(p) == 4) {
					if (p.getOpenInventory().getItem(slot).getType() != Material.AIR &&
							p.getOpenInventory().getItem(slot).getType() != Material.BARRIER) {
						last.put(p, true);
					}
				}
			}
		}.runTaskLater(Alchemy.getPlugin(), 98);
		
		map.remove(p);
		map.put(p, p.getOpenInventory().getItem(slot));
	}
}
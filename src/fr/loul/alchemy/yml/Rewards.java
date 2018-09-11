package fr.loul.alchemy.yml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.loul.alchemy.*;
import fr.loul.alchemy.utils.*;

public class Rewards {

	public static List<Inventory> load() {
		List<Inventory> list = new ArrayList<Inventory>();
		File f = new File(Alchemy.getPlugin().getDataFolder() + "/Rewards.yml");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				Alchemy.sendConsoleMessage("&cERROR ! Could not create Rewards.yml file.");
			}
		}
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		for (int i = 1; i <= 5; i++) {
			list.add(SerializeInventories.fromBase64(cfg.getString(String.valueOf(i))));
		}
		Inventory i = Bukkit.createInventory(null, 9);
		i.setItem(0, new ItemStack(Material.DIAMOND));
		while (list.size() < 5) {
			list.add(i);
		}
		return list;
	}
	
	public static void save() {
		File f = new File(Alchemy.getPlugin().getDataFolder() + "/Rewards.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		List<Inventory> rewards = Alchemy.getRewardList();
		for (int i = 1; i <= 5; i++) {
			cfg.set(String.valueOf(i), SerializeInventories.toBase64(rewards.get(i - 1)));
		}
	}
		
	public static void saveInv(Inventory i, int nb) {
		File f = new File(Alchemy.getPlugin().getDataFolder() + "/Rewards.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		cfg.set(String.valueOf(nb), SerializeInventories.toBase64(i));
		try {
			cfg.save(f);
		} catch (IOException e) {
			Alchemy.sendConsoleMessage("&cERROR ! Could not save Rewards.yml file.");
		}
		Alchemy.loadRewards();
	}
}

package fr.loul.alchemy;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.loul.alchemy.commands.AlchemyCmd;
import fr.loul.alchemy.edit.EditGui;
import fr.loul.alchemy.use.ClickInventory;
import fr.loul.alchemy.use.CloseInventory;
import fr.loul.alchemy.yml.Rewards;

public class Alchemy extends JavaPlugin {

	private static JavaPlugin Instance;
	private String prefix = "§7[§aAlchemy§7] ";
	private PluginManager pl = getServer().getPluginManager();
	
	public static List<Inventory> rewards = new ArrayList<Inventory>();
	
	@Override
	public void onEnable() {
		Instance = this;
		
		saveDefaultConfig();
		registerEvents();
		rewards = Rewards.load();
		sendConsoleMessage(prefix + "§8Launched succesfully!");
	}
	
	public void onDisable() {
		sendConsoleMessage(prefix + "§8Stopped succesfully!");
	}
	
	public void registerEvents() {
		getCommand("alchemy").setExecutor(new AlchemyCmd());
		pl.registerEvents(new EditGui(), this);
		pl.registerEvents(new ClickInventory(), this);
		pl.registerEvents(new CloseInventory(), this);
	}
	
	public static JavaPlugin getPlugin() {
		return Instance;
	}
	
	public static FileConfiguration getCfg() {
		return Instance.getConfig();
	}
	
	public static String getCfgStr(String str) {
		return Instance.getConfig().getString(str).replace("&", "§");
	}
	
	public static ConsoleCommandSender getConsole() {
		return Instance.getServer().getConsoleSender();
	}
	
	public static void sendConsoleMessage(String str) {
		getConsole().sendMessage(str.replace("&", "§"));
	}
	
	public static List<Inventory> getRewardList() {
		return rewards;
	}
	
	public static void loadRewards() {
		rewards.clear();
		rewards = Rewards.load();
	}
}

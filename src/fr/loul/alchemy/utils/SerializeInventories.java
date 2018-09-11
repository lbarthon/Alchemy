package fr.loul.alchemy.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public class SerializeInventories {

	public static String toBase64(Inventory inv) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeInt(inv.getSize());
            dataOutput.writeObject(inv.getContents());
            dataOutput.close();
            
            return Base64Coder.encodeLines(outputStream.toByteArray());

        } catch (Exception e) {
            throw new IllegalStateException("Unable to save inventories.", e);
        }
    }
	
	public static Inventory fromBase64(String s) {
        if (s == null) return null;
		try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(s));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

            Inventory inv = null;
            inv = Bukkit.createInventory(null, dataInput.readInt());

            inv.setContents((ItemStack[]) dataInput.readObject());

            dataInput.close();
            return inv;
        }
        catch (Exception e) {
            throw new IllegalStateException("Unable to load inventories.", e);
        }
	}
}

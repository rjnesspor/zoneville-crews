package me.itsry.zonecrews.menus;

import me.clip.placeholderapi.PlaceholderAPI;
import me.itsry.zonecrews.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.UUID;

public class SelectCrewMenu {

    public static void openMenu(Player p) {
        Inventory gui = Bukkit.createInventory(null, 27, "Select Crew");
        int[] fillerSlots = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
        for (int i = 0; i < fillerSlots.length; i++) {
            createItem(Material.STAINED_GLASS_PANE, 1, (short) 15, gui, fillerSlots[i], "&f", null);
        }
        ItemStack hercules = new ItemStack(Material.POTION);
        PotionMeta pmH = (PotionMeta) hercules.getItemMeta();
        pmH.setBasePotionData(new PotionData(PotionType.STRENGTH));
        hercules.setItemMeta(pmH);
        ItemMeta imH = hercules.getItemMeta();
        imH.setDisplayName(Util.chat("&c&lHERCULES &7Crew"));
        imH.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        ArrayList<String> loreHercules = new ArrayList<>();
        loreHercules.add(Util.chat("&8&m-----------------------------------"));
        loreHercules.add(Util.chat("&c&lHERCULES &7Crew"));
        loreHercules.add(Util.chat("&7&o(( Considered the \"God of Strength\" ))"));
        loreHercules.add(Util.chat("&f"));
        loreHercules.add(Util.chat("&c&l┃ PERKS"));
        loreHercules.add(Util.chat("&c&l┃ &cStrength 1"));
        loreHercules.add(Util.chat("&c&l┃ &cHercules &7Suffix"));
        loreHercules.add(Util.chat("&f"));
        loreHercules.add(Util.chat("&c&l┃ EXAMPLE"));
        String parsed = PlaceholderAPI.setPlaceholders(p, "%luckperms_prefix%%essentials_nickname%");
        loreHercules.add(Util.chat(parsed + " &cHercules"));
        loreHercules.add(Util.chat("&f"));
        loreHercules.add(Util.chat("&7&o(( Click to join the Hercules Crew ))"));
        loreHercules.add(Util.chat("&8&m-----------------------------------"));
        imH.setLore(loreHercules);
        hercules.setItemMeta(imH);

        ItemStack mercury = new ItemStack(Material.POTION);
        PotionMeta pmM = (PotionMeta) mercury.getItemMeta();
        pmM.setBasePotionData(new PotionData(PotionType.SPEED));
        mercury.setItemMeta(pmM);
        ItemMeta imM = mercury.getItemMeta();
        imM.setDisplayName(Util.chat("&b&lMERCURY &7Crew"));
        imM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        ArrayList<String> loreMercury = new ArrayList<>();
        loreMercury.add(Util.chat("&8&m-----------------------------------"));
        loreMercury.add(Util.chat("&b&lMERCURY &7Crew"));
        loreMercury.add(Util.chat("&7&o(( Considered the \"God of Speed\" ))"));
        loreMercury.add(Util.chat("&f"));
        loreMercury.add(Util.chat("&b&l┃ PERKS"));
        loreMercury.add(Util.chat("&b&l┃ &bSpeed 1"));
        loreMercury.add(Util.chat("&b&l┃ &bMercury &7Suffix"));
        loreMercury.add(Util.chat("&f"));
        loreMercury.add(Util.chat("&b&l┃ EXAMPLE"));
        loreMercury.add(Util.chat(parsed + " &bMercury"));
        loreMercury.add(Util.chat("&f"));
        loreMercury.add(Util.chat("&7&o(( Click to join the Mercury Crew ))"));
        loreMercury.add(Util.chat("&8&m-----------------------------------"));
        imM.setLore(loreMercury);
        mercury.setItemMeta(imM);

        gui.setItem(11, hercules);
        gui.setItem(15, mercury);

        ArrayList<String> loreInfo = new ArrayList<>();
        loreInfo.add(Util.chat("&4&lNOTE: &fOnce you make a selection, it will be &npermanent&r"));
        loreInfo.add(Util.chat("&funtil the season is over!"));
        loreInfo.add(Util.chat("&f"));
        loreInfo.add(Util.chat("&4&lCHOOSE WISELY"));

        createItem(Material.REDSTONE_TORCH_ON, 1, (short)1, gui, 13, "&4&l[!] WARNING", loreInfo);

        p.openInventory(gui);

    }

    public static void createItem(Material material, int amount, short data, Inventory inv, int slot, String name, ArrayList<String> lore) {
        ItemStack item = new ItemStack(material, amount, data);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Util.chat(name));
        if (lore != null)
            meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(slot, item);
    }


}

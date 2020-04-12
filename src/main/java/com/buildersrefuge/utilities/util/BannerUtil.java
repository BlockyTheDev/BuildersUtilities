package com.buildersrefuge.utilities.util;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BannerUtil {

    private List<DyeColor> allColors;
    private List<PatternType> allPatterns;

    public BannerUtil() {
        allColors = new ArrayList<>();
        allPatterns = new ArrayList<>();
        addColors();
        addPatterns();
    }

    private void addPatterns() {
        for (PatternType pt : PatternType.values()) {
            if (!pt.equals(PatternType.BASE)) {
                allPatterns.add(pt);
            }
        }
    }

    private void addColors() {
        allColors.add(DyeColor.BLACK);
        allColors.add(DyeColor.RED);
        allColors.add(DyeColor.GREEN);
        allColors.add(DyeColor.BROWN);
        allColors.add(DyeColor.BLUE);
        allColors.add(DyeColor.PURPLE);
        allColors.add(DyeColor.CYAN);
        allColors.add(DyeColor.SILVER);
        allColors.add(DyeColor.GRAY);
        allColors.add(DyeColor.PINK);
        allColors.add(DyeColor.LIME);
        allColors.add(DyeColor.YELLOW);
        allColors.add(DyeColor.LIGHT_BLUE);
        allColors.add(DyeColor.MAGENTA);
        allColors.add(DyeColor.ORANGE);
        allColors.add(DyeColor.WHITE);
    }

    @SuppressWarnings("deprecation")
    public ItemStack createBanner(String name, DyeColor base, String lore, List<Pattern> patterns) {
        Items i = new Items();
        ItemStack item = i.create(Material.BANNER, (short) 0, 1, name, "");
        BannerMeta meta = (BannerMeta) item.getItemMeta();
        if (com.buildersrefuge.utilities.Main.version.contains("v1_12") || com.buildersrefuge.utilities.Main.version.contains("v1_11")) {
            item.setDurability((short) allColors.indexOf(base));
        } else {
            meta.setBaseColor(base);
        }

        meta.setPatterns(patterns);
        if (!lore.equals("")) {
            String[] loreListArray = lore.split("__");
            List<String> loreList = new ArrayList<>();
            for (String s : loreListArray) {
                loreList.add(s.replace("&", "�"));
            }
            meta.setLore(loreList);
        }
        item.setItemMeta(meta);
        return item;
    }

    @SuppressWarnings("deprecation")
    ItemStack createBanner(String name, int amount, DyeColor base, String lore) {
        Items i = new Items();
        ItemStack item = i.create(Material.BANNER, (short) 0, 1, name, "");
        BannerMeta meta = (BannerMeta) item.getItemMeta();
        if (com.buildersrefuge.utilities.Main.version.contains("v1_12") || com.buildersrefuge.utilities.Main.version.contains("v1_11")) {
            item.setDurability((short) allColors.indexOf(base));
        } else {
            meta.setBaseColor(base);
        }
        if (!lore.equals("")) {
            String[] loreListArray = lore.split("__");
            List<String> loreList = new ArrayList<>();
            for (String s : loreListArray) {
                loreList.add(s.replace("&", "�"));
            }
            meta.setLore(loreList);
        }
        item.setItemMeta(meta);
        return item;
    }

    @SuppressWarnings("deprecation")
    ItemStack createBanner(String name, int amount, DyeColor base, String lore, Pattern pat) {
        Items i = new Items();
        ItemStack item = i.create(Material.BANNER, (short) 0, 1, name, "");
        BannerMeta meta = (BannerMeta) item.getItemMeta();
        if (com.buildersrefuge.utilities.Main.version.contains("v1_12") || com.buildersrefuge.utilities.Main.version.contains("v1_11")) {
            item.setDurability((short) allColors.indexOf(base));
        } else {
            meta.setBaseColor(base);
        }
        meta.addPattern(pat);
        if (!lore.equals("")) {
            String[] loreListArray = lore.split("__");
            List<String> loreList = new ArrayList<>();
            for (String s : loreListArray) {
                loreList.add(s.replace("&", "�"));
            }
            meta.setLore(loreList);
        }
        item.setItemMeta(meta);
        return item;
    }

    List<PatternType> getAllPatternTypes() {
        return allPatterns;
    }

    List<DyeColor> getAllColors() {
        return allColors;
    }

    public ItemStack addPattern(ItemStack i, Pattern pat) {
        if (i.getType().equals(Material.BANNER)) {
            BannerMeta meta = (BannerMeta) i.getItemMeta();
            List<Pattern> patterns = meta.getPatterns();
            patterns.add(pat);
            meta.setPatterns(patterns);
            i.setItemMeta(meta);
            return i;
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    DyeColor getBaseColor(ItemStack i) {
        if (i.getType().equals(Material.BANNER)) {
            BannerMeta meta = (BannerMeta) i.getItemMeta();
            if (com.buildersrefuge.utilities.Main.version.contains("v1_12") || com.buildersrefuge.utilities.Main.version.contains("v1_11")) {
                return allColors.get(i.getDurability());
            } else {
                return meta.getBaseColor();
            }

        }
        return null;
    }

    public PatternType getPatternType(ItemStack i) {
        if (i.getType().equals(Material.BANNER)) {
            BannerMeta meta = (BannerMeta) i.getItemMeta();
            return meta.getPattern(0).getPattern();
        }
        return null;
    }

    public DyeColor getColorFromBanner(ItemStack i) {
        if (i.getType().equals(Material.BANNER)) {
            BannerMeta meta = (BannerMeta) i.getItemMeta();
            return meta.getPattern(0).getColor();
        }
        return null;
    }

    public DyeColor getRandomDye() {
        Random r = new Random();
        return allColors.get(r.nextInt(allColors.size()));
    }

    public PatternType getRandomPattern() {
        Random r = new Random();
        return allPatterns.get(r.nextInt(allPatterns.size()));
    }

    DyeColor getDyeColor(ItemStack i) {
        if (i.getType().equals(Material.INK_SACK)) {
            return allColors.get(i.getDurability());
        } else {
            return null;
        }
    }


}

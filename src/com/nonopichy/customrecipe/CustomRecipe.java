package com.nonopichy.customrecipe;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class CustomRecipe implements Listener {

    /**
     * @author: https://github.com/Nonopichy
     * @frameworks: lombok, bukkit
     */

    private JavaPlugin j;
    public static HashMap<String,Recipe> recipes = new HashMap<>();

    public CustomRecipe(JavaPlugin j){
        j.getServer().getPluginManager().registerEvents(this, this.j = j);
    }

    public static char[] keys = new char[]{'A','B','C','D','E','F','G','H','I'};
    public void shapeBukkit(Recipe recipe){
        ShapedRecipe s = new ShapedRecipe(recipe.getResult());
        s.shape("ABC", "DEF", "GHI");
        for(MatrixItem r : recipe.getRecipe()){
            ItemStack i = r.getItem();
            s.setIngredient(keys[r.getMatrix()], i.getType(),i.getData().getData());
        }
        j.getServer().addRecipe(s);
    }

    public void addRecipe(String key, Recipe recipe){
        shapeBukkit(recipe);
        recipes.put(key,recipe);
    }
    public void getRecipe(String key){
        recipes.get(key);
    }

    @EventHandler
    public void onPrepareItemCraftEvent(PrepareItemCraftEvent e) {
        AsyncVerifyCraft(e);
    }
    public void AsyncVerifyCraft(PrepareItemCraftEvent e){
        new BukkitRunnable() {
            @Override
            public void run() {
                CraftingInventory v = e.getInventory(); // var local v
                ItemStack[] m = v.getMatrix(); // var local m
                if(v == null || m == null) // verify is not null
                    return;

                for(Recipe recipe : recipes.values()){
                    if(!recipe.getResult().isSimilar(v.getResult()))
                        continue;

                    MatrixItem[] l = recipe.getRecipe();

                    int a = 0;
                    int b = 0;

                    w:for (int i = 0; i < m.length; i++) {
                        ItemStack r = m[i];
                        if(r==null)
                            continue w;
                        a++;
                        for (MatrixItem c : l) {
                            if (recipe.isLoose() && isSimilar(c.getItem(), r, 0, 0)) {
                                b++;
                                continue w;
                            } else if (isSimilar(r, c.getItem(),  c.getMatrix(), i)){
                                b++;
                            }
                        }
                    }

                    if(a != b) {
                        v.setResult(null);
                        Player p = (Player) e.getView().getPlayer();
                        p.updateInventory();
                    }

                    return;
                }
            }
        }.runTaskAsynchronously(j);
    }

    public boolean isSimilar(ItemStack a, ItemStack b, int c, int d){
        if (!(a.getType() == b.getType() && a.getData().getData() == b.getData().getData() && c == d))
            return false;
        if(a.hasItemMeta() && b.hasItemMeta() && a.getItemMeta().getDisplayName().equals(b.getItemMeta().getDisplayName()) || !b.hasItemMeta())
            return true;
        return false;
    }
}

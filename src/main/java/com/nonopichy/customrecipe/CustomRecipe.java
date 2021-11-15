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

/**
 * @author https://github.com/Nonopichy
 */
public class CustomRecipe implements Listener {

    public static HashMap<String, Recipe> recipes = new HashMap<>();
    public static char[] keys = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
    private final JavaPlugin plugin;

    public CustomRecipe(JavaPlugin plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    public void shapeBukkit(Recipe recipe) {
        ShapedRecipe s = new ShapedRecipe(recipe.getResult());
        s.shape("ABC", "DEF", "GHI");

        for (MatrixItem r : recipe.getRecipe()) {
            if (r == null)
                continue;
            ItemStack i = r.getItem();
            s.setIngredient(keys[r.getMatrix()], i.getType(), i.getData().getData());
        }

        plugin.getServer().addRecipe(s);
    }

    public void addRecipe(String key, Recipe recipe) {
        shapeBukkit(recipe);
        recipes.put(key, recipe);
    }

    public void getRecipe(String key) {
        recipes.get(key);
    }

    @EventHandler
    public void onPrepareItemCraftEvent(PrepareItemCraftEvent e) {
        AsyncVerifyCraft(e);
    }

    public void AsyncVerifyCraft(PrepareItemCraftEvent e) {
        new BukkitRunnable() {
            @Override
            public void run() {
                final CraftingInventory craftingInventory = e.getInventory();
                final ItemStack[] matrix = craftingInventory.getMatrix();

                if (matrix == null) return;

                for (Recipe recipe : recipes.values()) {
                    if (!recipe.getResult().isSimilar(craftingInventory.getResult()))
                        continue;

                    MatrixItem[] l = recipe.getRecipe();

                    int a = 0;
                    int b = 0;

                    w:
                    for (int i = 0; i < matrix.length; i++) {
                        final ItemStack currentItem = matrix[i];

                        if (currentItem == null) continue;

                        a++;

                        for (MatrixItem c : l) {
                            if (c == null) continue;

                            if (recipe.isLoose() && isSimilar(c.getItem(), currentItem, 0, 0)) {
                                b++;
                                continue w;
                            } else if (isSimilar(currentItem, c.getItem(), c.getMatrix(), i)) b++;
                        }
                    }

                    if (a != b) {
                        craftingInventory.setResult(null);
                        Player p = (Player) e.getView().getPlayer();
                        p.updateInventory();
                    }

                    return;
                }
            }
        }.runTaskAsynchronously(this.plugin);
    }

    public boolean isSimilar(ItemStack a, ItemStack b, int c, int d) {
        if (!(a.getType() == b.getType() && a.getData().getData() == b.getData().getData() && c == d)) return false;

        return a.hasItemMeta()
            && b.hasItemMeta()
            && a.getItemMeta().getDisplayName().equals(b.getItemMeta().getDisplayName())
            || !b.hasItemMeta();
    }

}

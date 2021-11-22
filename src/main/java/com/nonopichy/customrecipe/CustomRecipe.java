package com.nonopichy.customrecipe;

import com.nonopichy.customrecipe.plugin.Metrics;
import com.nonopichy.customrecipe.plugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author https://github.com/Nonopichy
 */
public class CustomRecipe implements Listener {

    public static HashMap<String, Recipe> recipes = new HashMap<>();
    public static char[] keys = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
    private final JavaPlugin plugin;
    private boolean namespaced = false;

    public CustomRecipe(JavaPlugin plugin){
        JavaPlugin instance = Plugin.getInstance();
        if(instance != null) this.plugin = instance;
        else this.plugin = plugin;
        register();
    }

    private void register(){
        Metrics metrics = new Metrics(plugin, 13372);
        metrics.addCustomChart(new Metrics.MultiLineChart("players_and_servers", new Callable<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> call() throws Exception {
                Map<String, Integer> valueMap = new HashMap<>();
                valueMap.put("servers", 1);
                valueMap.put("players", Bukkit.getOnlinePlayers().size());
                return valueMap;
            }
        }));
        if(!checkVersion(Bukkit.getServer().getBukkitVersion().split("-")[0],
                "1.7","1.8", "1.9", "1.10", "1.11"))
            namespaced=true;
        plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    public boolean checkVersion(String check, String... versions){
        for(int i = 0 ; i < versions.length ; i++){
            if(check.contains(versions[i]))
                return true;
        }
        return false;
    }

    public void shapeBukkit(String key, Recipe recipe) {
        ItemStack item = recipe.getResult();
        ShapedRecipe s;

        if(namespaced)
            s = new ShapedRecipe(new NamespacedKey(plugin, key), item);
        else
            s = new ShapedRecipe(item);

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
        shapeBukkit(key, recipe);
        recipes.put(key, recipe);
    }

    public void getRecipe(String key) {
        recipes.get(key);
    }

    @EventHandler
    public void onPrepareItemCraftEvent(PrepareItemCraftEvent e) {
        final CraftingInventory craftingInventory = e.getInventory();
        final ItemStack[] matrix = craftingInventory.getMatrix();

        if (matrix == null) return;

        for (Recipe recipe : recipes.values()) {
            if (!recipe.getResult().isSimilar(craftingInventory.getResult()))
                continue;
            craftingInventory.setResult(null);

            MatrixItem[] l = recipe.getRecipe();

            int a = 0;
            int b = 0;

            w: for (int i = 0; i < matrix.length; i++) {
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

            if (a == b) {
                craftingInventory.setResult(e.getRecipe().getResult());
            }
        }
    }

    public boolean isSimilar(ItemStack a, ItemStack b, int c, int d) {
        if (!(a.getType() == b.getType() && a.getData().getData() == b.getData().getData() && c == d)) return false;

        return a.hasItemMeta()
                && b.hasItemMeta()
                && a.getItemMeta().getDisplayName().equals(b.getItemMeta().getDisplayName())
                || !b.hasItemMeta();
    }

}

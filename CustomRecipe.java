import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
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

    @AllArgsConstructor
    public static class MatrixItem {
        @Getter @Setter private ItemStack item;
        @Getter @Setter private int matrix;
    }
    @AllArgsConstructor
    public static class Recipe {
        @Getter private CustomRecipe.MatrixItem[] recipe;
        @Getter private ItemStack result;
        @Getter private boolean loose_shape;
    }

    private JavaPlugin plugin;

    public static HashMap<String, Recipe> recipes = new HashMap<>();
    public static char[] keys = new char[]{
            'A','B','C',
            'D','E','F',
            'G','H','I'};

    public void addRecipe(String key, Recipe recipe){
        registerInBukkit(recipe);
        recipes.put(key,recipe);
    }
    public void getRecipe(String key){ recipes.get(key); }
    public void registerInBukkit(Recipe recipe){
        ShapedRecipe s = new ShapedRecipe(recipe.getResult());
        s.shape("ABC", "DEF", "GHI");
        for(MatrixItem r : recipe.getRecipe()){
            ItemStack i = r.getItem();
            s.setIngredient(keys[r.getMatrix()], i.getType(),i.getData().getData());
        }
        Bukkit.getServer().addRecipe(s);
    }
    public CustomRecipe(JavaPlugin j){
        this.plugin = j;
        j.getServer().getPluginManager().registerEvents(this, j);
    }
    public void AsyncVerifyCraft(PrepareItemCraftEvent e){
        new BukkitRunnable() {
            @Override
            public void run() {
                CraftingInventory v = e.getInventory();
                ItemStack[] m = v.getMatrix();
                if(v == null || m == null)
                    return;

                for(Recipe recipe : recipes.values()){
                    if(!recipe.getResult().isSimilar(v.getResult()))
                        continue;

                    MatrixItem[] l = recipe.getRecipe();

                    int a = 0;
                    int b = 0;

                    if(recipe.isLoose_shape()){
                        w:for (int i = 0; i < m.length; i++) {
                            ItemStack r = m[i];
                            if(r==null)
                                continue w;
                            a++;
                            for (MatrixItem c : l) {
                                if (isSimilar(c.getItem(), r, 0, 0)) {
                                    b++;
                                    continue w;
                                }
                            }
                        }
                    } else {
                        y:for (int i = 0; i < m.length; i++) {
                            ItemStack r = m[i];
                            if (r == null)
                                continue y;
                            a++;
                            for (MatrixItem c : l) {
                                if (isSimilar(r, c.getItem(), c.getMatrix(), i))
                                    b++;
                            }
                        }
                    }

                    // a = to found
                    // b = found

                    if(a != b) {
                        v.setResult(null);
                        Player p = (Player) e.getView().getPlayer();
                        p.updateInventory();
                    }

                    return;
                }
            }
        }.runTaskAsynchronously(plugin);
    }
    public void onPrepareItemCraftEvent(PrepareItemCraftEvent e) {
        AsyncVerifyCraft(e);
    }
    public boolean isSimilar(ItemStack a, ItemStack b, int c, int d){
        if (!(a.getType() == b.getType() && a.getData().getData() == b.getData().getData() && c == d))
            return false;
        if(a.hasItemMeta() && b.hasItemMeta() && a.getItemMeta().getDisplayName().equals(b.getItemMeta().getDisplayName()) || !b.hasItemMeta())
            return true;
        return false;
    }
}

package com.nonopichy.customrecipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.inventory.ItemStack;

@Data
@AllArgsConstructor
public class Recipe {

    private MatrixItem[] recipe;
    private ItemStack result;
    private boolean loose;
    
    public void setRecipe(MatrixItem... m) {
        recipe = m;
    }

}

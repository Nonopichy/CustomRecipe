package com.nonopichy.customrecipe;

import lombok.Data;
import org.bukkit.inventory.ItemStack;

@Data
public class Recipe {

    private MatrixItem[] recipe;
    private ItemStack result;
    private boolean loose;

    public void setRecipe(MatrixItem... m) {
        recipe = m;
    }

}

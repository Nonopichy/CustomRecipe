package com.nonopichy.customrecipe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class Recipe {
    @Getter private MatrixItem[] recipe;
    @Setter @Getter private ItemStack result;
    @Setter @Getter private boolean loose;
    public Recipe(){}
    public void setRecipe(MatrixItem... m){ recipe = m; }
}
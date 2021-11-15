package com.nonopichy.customrecipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.inventory.ItemStack;

@Data
@AllArgsConstructor
public class MatrixItem {

    private ItemStack item;
    private int matrix;

}

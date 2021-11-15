package com.nonopichy.customrecipe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class MatrixItem {
    @Getter @Setter private ItemStack item;
    @Getter @Setter private int matrix;
}
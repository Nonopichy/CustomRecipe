[![download](https://img.shields.io/badge/download-java-green)](https://github.com/Nonopichy/CustomRecipe/releases/)
[![b](https://img.shields.io/badge/bstats-web%20-green)](https://bstats.org/plugin/bukkit/CustomRecipe/13372/)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/59c6e66b3ed44870b1077b7732e4d770)](https://www.codacy.com/gh/Nonopichy/CustomRecipe/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Nonopichy/CustomRecipe&amp;utm_campaign=Badge_Grade)
![a](https://user-images.githubusercontent.com/68911691/141825718-07df0c81-1de8-42c7-92fc-f13ba54bef96.png)

# FrameWork
-   Version Support: **1.13.2** Below
-   Frameworks: Bukkit, Lombok

## Craft with custom items
>   <img src="img/gif2.gif" width="320" height="170">

## Only specific items to craft
>   <img src="img/gif1.gif" width="320" height="170" >

## Tutorial
-   Add [CustomRecipe.jar](https://github.com/Nonopichy/CustomRecipe/releases/) in libraries (how you add bukkit)
-   In artifacts (without the CustomRecipe in folder plugins)
>   <img src="https://user-images.githubusercontent.com/68911691/141841508-275de82a-f33b-40a6-9cf3-517c554774c0.png" width="320" height="130" >
-   In artifacts (with the CustomRecipe in folder plugins)
>   <img src="https://user-images.githubusercontent.com/68911691/142939921-d50f860b-e164-4734-9904-beb795dc2f2d.png" width="320" height="100" >
-   In plugin.yml (with the CustomRecipe in folder plugins)
>   <img src="https://user-images.githubusercontent.com/68911691/142940218-e5b3d3b4-4761-4b58-bff4-fe675ae6e1d3.png" width="320" height="100" >
-   Create a new instance of 'CustomRecipe' in your JavaPlugin (Main).
-   Create a new instance of 'Recipe' add to 'CustomRecipe'.
-   Execute method 'addRecipe' with the arguments.
-   Compile your plugin with CustomRecipe.jar inside.
-   :) Finish! Example? BELOW!

## In Main

```java
public class Main extends JavaPlugin {
    public static CustomRecipe customRecipe;
    public void onEnable(){
        customRecipe = new CustomRecipe(this);
    }
}
```

## Example Short

-   To empty spaces, use ```null``` instead ```new MatrixItem(new ItemStack(Material.AIR),SLOT)```
```java
Recipe recipe = new Recipe();
recipe.setLoose(false);
recipe.setResult(new ItemStack(Material.DIAMOND));
        
final ItemStack REDSTONE = new ItemStack(Material.REDSTONE);
recipe.setRecipe(
     new MatrixItem(REDSTONE, 0),
     null,
     new MatrixItem(REDSTONE, 2),
     null,
     new MatrixItem(REDSTONE, 4)
);
     
customRecipe.addRecipe("REDSTONE_TO_DIAMOND", recipe);
```

>   <img src="img/matrix_example.png" width="426" height="213" >

## Example "Bigger"

```java
Recipe recipe = new Recipe();
recipe.setLoose(false);
recipe.setResult(result);
        
recipe.setRecipe(
     new MatrixItem(red, 0),
     new MatrixItem(red, 1),
     new MatrixItem(red, 2),
     new MatrixItem(red, 3),
     new MatrixItem(new ItemStack(Material.APPLE), 4),
     new MatrixItem(red, 5),
     new MatrixItem(red, 6),
     new MatrixItem(red, 7),
     new MatrixItem(red, 8)
);
     
customRecipe.addRecipe("APPLE_REDSTONE", recipe);
```

## Items used

```java
// Item Result
ItemStack result = new ItemStack(Material.APPLE);
ItemMeta itemMeta = result.getItemMeta();
itemMeta.setDisplayName("??cMa??a de Redstone");
result.addUnsafeEnchantment(Enchantment.LUCK,1);
itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
result.setItemMeta(itemMeta);

// Item Craft
ItemStack redstone = new ItemStack(Material.REDSTONE);
itemMeta = red.getItemMeta();
itemMeta.setDisplayName("??c??lRedstone");
redstone.addUnsafeEnchantment(Enchantment.LUCK,1);
itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
redstone.setItemMeta(itemMeta);
```

## Result

>   <img src="https://user-images.githubusercontent.com/68911691/141708122-952daa07-f8dc-4b80-92e6-335b915587bc.png" width="520" height="240">

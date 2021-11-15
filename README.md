[![download](https://img.shields.io/badge/download-java-green)](https://github.com/Nonopichy/CustomRecipe/releases/tag/CustomRecipe)
![a](https://user-images.githubusercontent.com/68911691/141825718-07df0c81-1de8-42c7-92fc-f13ba54bef96.png)

# FrameWork
* Version Support: **1.13.2** Below
* Frameworks: Bukkit, Lombok
* Async Event

### Craft with custom items
> <img src="img/gif2.gif" width="320" height="170">

### Only specific items to craft
> <img src="img/gif1.gif" width="320" height="170" >

# Tutorial:
- Add [CustomRecipe.jar](https://github.com/Nonopichy/CustomRecipe/releases/download/CustomRecipe/CustomRecipe.jar) in libraries (how you add bukkit)
- Create a new instance of 'CustomRecipe' in your JavaPlugin (Main).
- Create a new instance of 'Recipe' add to 'CustomRecipe'.
- Execute method 'addRecipe' with the arguments.
- Compile your plugin with CustomRecipe.jar inside.
- :) Finish! Example? BELOW!

### Example Short:

- To empty spaces, use ```null``` instead ```new MatrixItem(new ItemStack(Material.AIR),SLOT)```
```java
CustomRecipe c = new CustomRecipe(YourMainInstance);
Recipe r = new Recipe();
r.setLoose(false);
r.setResult(new ItemStack(Material.DIAMOND));
        
ItemStack REDSTONE = new ItemStack(Material.REDSTONE);
r.setRecipe(
     new MatrixItem(REDSTONE, 0),
     null,
     new MatrixItem(REDSTONE, 2),
     null,
     new MatrixItem(REDSTONE, 4)
     );
     
c.addRecipe("REDSTONE_TO_DIAMOND", r);
```

<img src="img/matrix_example.png" width="426" height="213" >

### Example "Bigger":

```java
CustomRecipe c = new CustomRecipe(YourMainInstance);
Recipe r = new Recipe();
r.setLoose(false);
r.setResult(result);
        
r.setRecipe(
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
     
c.addRecipe("APPLE_REDSTONE", r);
```

### Items used:

```java
// Item Result
ItemStack result = new ItemStack(Material.APPLE);
ItemMeta m = result.getItemMeta();
m.setDisplayName("§cMaça de Redstone");
result.addUnsafeEnchantment(Enchantment.LUCK,1);
m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
result.setItemMeta(m);

// Item Craft
ItemStack red = new ItemStack(Material.REDSTONE);
m = red.getItemMeta();
m.setDisplayName("§c§lRedstone");
red.addUnsafeEnchantment(Enchantment.LUCK,1);
m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
r.setItemMeta(m);
```

### Result:

<img src="https://user-images.githubusercontent.com/68911691/141708122-952daa07-f8dc-4b80-92e6-335b915587bc.png" width="520" height="240" >

